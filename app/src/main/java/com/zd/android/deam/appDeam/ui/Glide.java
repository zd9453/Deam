package com.zd.android.deam.appDeam.ui;

import android.content.Intent;
import android.os.Bundle;

import com.zd.android.deam.R;

public class Glide extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);

        waitting();
    }


    private void waitting() {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            finish();
                            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                            Intent i = new Intent(Glide.this, Main2Activity.class);
                            startActivity(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();
    }
}
