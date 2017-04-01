package com.zd.android.deam;

import android.content.Intent;
import android.icu.text.LocaleDisplayNames;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.zd.android.deam.ActivityTest.ActivityTestRv;
import com.zd.android.deam.appDeam.ui.Glide;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MY_INFO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void go1(View view) {
        Intent intent = new Intent(this, Glide.class);
        startActivity(intent);
    }

    public void go2(View view) {
        Intent intent = new Intent(this, ActivityTestRv.class);
        startActivity(intent);
    }

    public void go3(View view) {
        new ShareAction(MainActivity.this).withText("hello")
                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        Log.d(TAG, "onStart: ============" + share_media);
                    }

                    @Override
                    public void onResult(SHARE_MEDIA share_media) {
                        Log.d(TAG, "onResult: =============");
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                        Log.d(TAG, "onError: ==================" + throwable);
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {
                        Log.d(TAG, "onCancel: =====================");
                    }
                }).open();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: ==============");
    }

    public void go4(View view) {
        Intent intent = new Intent(this, HtmlActivity.class);
        startActivity(intent);
    }

}
