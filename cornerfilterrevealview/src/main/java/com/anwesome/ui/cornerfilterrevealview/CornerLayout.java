package com.anwesome.ui.cornerfilterrevealview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anweshmishra on 15/05/17.
 */
public class CornerLayout extends ViewGroup {
    private int w,h,gap;
    public void onMeasure(int wspec,int hspec) {
        int hNew = h/10;
        for(int i=0;i<getChildCount();i++) {
            View child = getChildAt(i);
            measureChild(child,wspec,hspec);
            if(i%3 == 1) {
                hNew += child.getMeasuredHeight()+h/20;
            }
        }
        setMeasuredDimension(w,Math.max(hNew,h));
    }
    public void addImage(Bitmap bitmap,int...colors) {
        FilterRevealView filterRevealView = new FilterRevealView(getContext(),bitmap);
        if(colors.length == 1) {
            filterRevealView.setColor(colors[0]);
        }
        addView(filterRevealView,new LayoutParams(gap,gap));
        requestLayout();
    }
    public void onLayout(boolean reloaded,int a,int b,int w,int h) {
        int xs[] = {w/20,w/20+w/2,w/20},y = w/20;
        for(int i=0;i<getChildCount();i++) {
            View child = getChildAt(i);
            child.layout(xs[i%3],y,xs[i%3]+child.getMeasuredWidth(),y+child.getMeasuredHeight());
            if(i%3 == 1) {
                y+=gap;
            }
        }
    }
    public void initDimension(Context context) {
        DisplayManager displayManager = (DisplayManager)context.getSystemService(Context.DISPLAY_SERVICE);
        Display display = displayManager.getDisplay(0);
        if(display != null) {
            Point size = new Point();
            display.getRealSize(size);
            w = size.x;
            h = size.y;
            gap = Math.min(w,h)/3;
        }
    }
    public CornerLayout(Context context) {
        super(context);
        initDimension(context);
    }
}
