package com.anwesome.ui.conerfilterrevalviewdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.anwesome.ui.cornerfilterrevealview.FRVList;
import com.anwesome.ui.cornerfilterrevealview.FilterRevealView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.forest1);
        FRVList frvList = new FRVList(this);
        for(int i=0;i<12;i++) {
            frvList.addImage(bitmap);
        }
        frvList.show();
    }
}
