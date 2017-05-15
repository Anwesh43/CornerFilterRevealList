package com.anwesome.ui.conerfilterrevalviewdemo;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.anwesome.ui.cornerfilterrevealview.FilterRevealView;

public class MainActivity extends AppCompatActivity {
    private FilterRevealView filterRevealView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        filterRevealView = new FilterRevealView(this,BitmapFactory.decodeResource(getResources(),R.drawable.forest1));
        setContentView(R.layout.activity_main);
        addContentView(filterRevealView,new ViewGroup.LayoutParams(300,300));
    }
}
