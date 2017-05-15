package com.anwesome.ui.cornerfilterrevealview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 15/05/17.
 */
public class FilterRevealView extends View{
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap bitmap;
    private int render = 0,w,h;
    private int color = Color.WHITE;
    private RevealButton revealButton;
    private ColorFilterRect colorFilterRect;
    private AnimationHandler animationHandler;
    public FilterRevealView(Context context, Bitmap bitmap) {
        super(context);
        this.bitmap = bitmap;
    }
    public void setColor(int color) {
        this.color = color;
    }
    public void onDraw(Canvas canvas) {
        if(render == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            bitmap = Bitmap.createScaledBitmap(bitmap,w,h,true);
            revealButton = new RevealButton();
            colorFilterRect = new ColorFilterRect();
            animationHandler = new AnimationHandler();
        }
        canvas.drawBitmap(bitmap,0,0,paint);
        colorFilterRect.draw(canvas);
        revealButton.draw(canvas);
        render++;
    }
    public void update(float factor) {
        colorFilterRect.update(factor);
        revealButton.update(factor);
        postInvalidate();
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            animationHandler.start();
        }
        return true;
    }
    private class RevealButton {
        private float x,y,size,maxW,deg = 45;
        public RevealButton() {
            size = w/10;
            x = w/5+size;
            y = w/5+size;
            maxW = 7*w/10;
        }
        public void update(float factor) {
            deg = 45+180*factor;
            x = w/10+(8*w/10)*factor;
            y = w/10+(8*w/10)*factor;
        }
        public void draw(Canvas canvas) {
            canvas.save();
            canvas.translate(x,y);
            canvas.rotate(deg);
            paint.setColor(Color.WHITE);
            paint.setStrokeWidth(size/6);
            paint.setStrokeJoin(Paint.Join.ROUND);
            for(int i=0;i<2;i++) {
                canvas.save();
                canvas.translate(0,0);
                canvas.rotate(45*(2*i-1));
                canvas.drawLine(-size,0,0,0,paint);
                canvas.restore();
            }
            canvas.restore();
        }
    }
    private class ColorFilterRect {
        private float rw = 0,rh = 0;
        public void draw(Canvas canvas) {
            paint.setColor(Color.argb(150,Color.red(color),Color.green(color),Color.blue(color)));
            canvas.drawRect(0,0,rw,rh,paint);
        }
        public void update(float factor) {
            rw = w*factor;
            rh = h*factor;
        }
    }
    private class AnimationHandler extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener{
        private ValueAnimator upAnim = ValueAnimator.ofFloat(0,1),downAnim = ValueAnimator.ofFloat(1,0);
        private int dir = 0;
        private boolean isAnimating = false;
        public AnimationHandler() {
            downAnim.addUpdateListener(this);
            upAnim.addUpdateListener(this);
            downAnim.addListener(this);
            upAnim.addListener(this);
            downAnim.setDuration(500);
            upAnim.setDuration(500);
        }
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            update((float)valueAnimator.getAnimatedValue());
        }
        public void onAnimationEnd(Animator animator) {
            if(isAnimating) {
                isAnimating = false;
            }
        }
        public void start() {
            if(!isAnimating) {
                if (dir == 0) {
                    upAnim.start();
                } else {
                    downAnim.start();
                }
                dir = dir == 0 ? 1 : 0;
                isAnimating = true;
            }
        }
    }
}
