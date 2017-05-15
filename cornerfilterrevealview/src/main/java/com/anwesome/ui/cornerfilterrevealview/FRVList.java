package com.anwesome.ui.cornerfilterrevealview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ScrollView;

/**
 * Created by anweshmishra on 15/05/17.
 */
public class FRVList {
    private Activity activity;
    private CornerLayout cornerLayout;
    private ScrollView scrollView;
    private boolean isShown = false;
    public FRVList(Activity activity) {
        this.activity = activity;
        cornerLayout = new CornerLayout(activity);
        scrollView = new ScrollView(activity);
        scrollView.addView(cornerLayout,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
    public void addImage(Bitmap bitmap,int...colors) {
        if(!isShown) {
            cornerLayout.addImage(bitmap, colors);
            isShown = false;
        }
    }
    public void show() {
        if(!isShown) {
            activity.setContentView(scrollView);
            isShown = true;
        }
    }
}
