package com.zd.android.deam.appDeam.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zd.android.deam.R;

/**
 * Created by suzy on 2017/3/22.
 **/


public class MyToolBar extends RelativeLayout{

    private ImageButton left_ib,right_ib;
    private TextView title;


    public MyToolBar(Context context) {
        super(context);
        addview(context);
    }

    public MyToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        addview(context);
    }

    public MyToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addview(context);
    }

    private void addview(Context context){

        View.inflate(context, R.layout.myactionbar, MyToolBar.this);

        left_ib = (ImageButton) this.findViewById(R.id.left_ib);
        title = (TextView) this.findViewById(R.id.m_title);
        right_ib = (ImageButton) this.findViewById(R.id.right_ib);

    }

    //设置标题
    public void setTitleText(String str){
        if (null != str && str.length() != 0) {
            title.setText(str);
        }
    }

    //绑定监听
    public void setIBClick(OnClickListener click){
        left_ib.setOnClickListener(click);
        right_ib.setOnClickListener(click);
    }

    /**
     * imagebutton设置背景图片
     * @param resouseId 资源id
     * @param isLeft true--设置左边，false--设置右边
     */
    public void setSrc(int resouseId, boolean isLeft){
        if (isLeft) {
            left_ib.setImageResource(resouseId);
        } else {
            right_ib.setImageResource(resouseId);
        }
    }

    /**
     * 设置imagebutton是否可见
     * @param visibility 是否可见
     * @param num button的位置，0-左，1-右
     */
    public void setImageButtonVisibility(int visibility,int num){
        switch (num) {
            case 0:
                left_ib.setVisibility(visibility);
                break;
            case 1:
                right_ib.setVisibility(visibility);
                break;
            default:
                break;
        }
    }

}
