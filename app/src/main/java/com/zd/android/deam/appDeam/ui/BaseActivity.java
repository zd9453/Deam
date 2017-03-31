package com.zd.android.deam.appDeam.ui;

import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

/**
 * Created by suzy on 2017/3/22.
 **/

public class BaseActivity extends AppCompatActivity {

    int height, width;
    String TAG = "MY_INFO";

    @Override
    protected void onStart() {
        super.onStart();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

//        height = getWindowManager().getDefaultDisplay().getHeight();
    }


}
