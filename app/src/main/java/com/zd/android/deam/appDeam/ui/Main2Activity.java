package com.zd.android.deam.appDeam.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zd.android.deam.R;
import com.zd.android.deam.appDeam.ui.api.ApiBase;
import com.zd.android.deam.appDeam.ui.api.Apiserver;
import com.zd.android.deam.appDeam.ui.view.MyToolBar;
import com.zd.android.deam.appDeam.ui.weather.bean.fragment.HistoryFragment;
import com.zd.android.deam.appDeam.ui.weather.bean.fragment.WeatherFragment;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{


    private MyToolBar myBar;
    private SlidingPaneLayout outLayout;
    private TextView weatherText;
    private FrameLayout contentView;
    private FragmentManager mFragmentManager;
    private WeatherFragment weatherFragment;
    private HistoryFragment historyFragment;
    private RelativeLayout weatherLayout;
    private RelativeLayout null_img;
    private RelativeLayout clearLayout;
    private RelativeLayout historyLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        findView();
        init();
    }

    private void findView(){
        null_img = (RelativeLayout) findViewById(R.id.null_img);
        weatherText = (TextView) findViewById(R.id.te_weather);
        myBar = (MyToolBar) findViewById(R.id.Mybar);
        outLayout = (SlidingPaneLayout) findViewById(R.id.sliding_layout);
        contentView = (FrameLayout) findViewById(R.id.replace_content);
        weatherLayout = (RelativeLayout) findViewById(R.id.function_weather);
        historyLayout = (RelativeLayout) findViewById(R.id.function_history);
        clearLayout = (RelativeLayout) findViewById(R.id.clear);
    }


    private void init(){
        weatherLayout.setOnClickListener(this);
        historyLayout.setOnClickListener(this);
        clearLayout.setOnClickListener(this);
        myBar.setSrc(R.mipmap.icon_lines2x,true);
        myBar.setSrc(R.mipmap.icon_return_right2x,false);
        myBar.setImageButtonVisibility(View.GONE,1);
        myBar.setIBClick(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.left_ib:
                check();
                break;
            case R.id.right_ib:
                Toast.makeText(Main2Activity.this,"右3拳",Toast.LENGTH_SHORT).show();
                break;
            case R.id.function_weather: //天气查询
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
                builder.setContentTitle("天气查询")
                        .setContentText("你选择了定制的天气查询功能")//<span style="font-family: Arial;">/设置通知栏显示内容</span>
                        .setTicker("告诉你一个小秘密") //通知首次出现在通知栏，带上升动画效果的
                        .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
                        .setPriority(Notification.PRIORITY_DEFAULT) //设置该通知优先级
                        .setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
                        .setOngoing(false)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
//                        .setDefaults(Notification.DEFAULT_VIBRATE)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
//                        Notification.DEFAULT_ALL  Notification.DEFAULT_SOUND 添加声音 // requires VIBRATE permission
                        .setContentIntent(PendingIntent.getActivity(this, 0, new Intent(), 0))
                        .setSmallIcon(R.mipmap.ic_launcher);//设置通知小ICON
                manager.notify(110,builder.build());

                myBar.setTitleText(weatherText.getText().length() == 0 ? "错误" : weatherText.getText().toString());
                myBar.setImageButtonVisibility(View.VISIBLE,1);
                null_img.setVisibility(View.GONE);
                showW();
                check();
                break;
            case R.id.function_history:
                myBar.setTitleText("历史上的今天");
                myBar.setImageButtonVisibility(View.VISIBLE,1);
                null_img.setVisibility(View.GONE);
                showH();
                check();
                break;
            case R.id.clear:
                null_img.setVisibility(View.VISIBLE);
                myBar.setTitleText("welcome");
                myBar.setImageButtonVisibility(View.GONE,1);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                hideFragment(transaction);
                transaction.commit();
                check();
            default:
                break;
        }
    }

    private void showW(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (weatherFragment == null) {
            weatherFragment = new WeatherFragment();
            fragmentTransaction.add(R.id.replace_content,weatherFragment);
        }
        hideFragment(fragmentTransaction);
        fragmentTransaction.show(weatherFragment);
        fragmentTransaction.commit();
    }

    private void showH(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (historyFragment == null) {
            historyFragment = new HistoryFragment();
            fragmentTransaction.add(R.id.replace_content,historyFragment);
        }
        hideFragment(fragmentTransaction);
        fragmentTransaction.show(historyFragment);
        fragmentTransaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction){
        if (weatherFragment != null ) {
            transaction.hide(weatherFragment);
        }
        if (historyFragment != null ) {
            transaction.hide(historyFragment);
        }
    }


    /**
     * 检查左边是否滑出
     */
    private void check(){
        if (outLayout.isOpen()) {
            outLayout.closePane();
        } else {
            outLayout.openPane();
        }
    }


    /**
     * 返回键
     */
    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000)  //System.currentTimeMillis()无论何时调用，肯定大于2000
            {
                Toast.makeText(Main2Activity.this, "你要忙别的去了呀*_*", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
