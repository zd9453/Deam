package com.zd.android.deam.appDeam.ui.weather.bean.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.zd.android.deam.R;
import com.zd.android.deam.appDeam.ui.api.ApiBase;
import com.zd.android.deam.appDeam.ui.api.Apiserver;
import com.zd.android.deam.appDeam.ui.weather.bean.HistoryInfo;
import com.zd.android.deam.appDeam.ui.weather.bean.adapter.HistoryAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * create by name 2017.03.27
 */
public class HistoryFragment extends Fragment implements View.OnClickListener {

    private RecyclerView rvHistory;
    private Button btChose;
    private TextView textChose;
    private TimePickerView pvTime;
    private List<HistoryInfo.ResultBean> lists = new ArrayList<>();
    private HistoryAdapter hisAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_history, container, false);
        btChose = (Button) view.findViewById(R.id.bt_chose);
        textChose = (TextView) view.findViewById(R.id.text_chose);
        btChose.setOnClickListener(this);
        rvHistory = (RecyclerView) view.findViewById(R.id.rv_history);
        rvHistory.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        hisAdapter = new HistoryAdapter(lists);
        rvHistory.setAdapter(hisAdapter);
        return  view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_chose:
                choseTime();
                break;
            default:
                break;
        }

    }


    private void choseTime(){
        Calendar startDate = Calendar.getInstance();
        startDate.set(2017,0,01);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2017,11,31);

        pvTime = new TimePickerView
                .Builder(getContext(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                long time = date.getTime();
                String s = format.format(time);
                textChose.setText("查询时间："+s);

                textChose.setTextColor(Color.rgb(10,20,30));

                if (null != s && s.length() != 0){
                    String[] split = s.split("-");
                    StringBuilder cTime = new StringBuilder();
                    cTime.append(split[1]);
                    cTime.append(split[2]);
                    Call<HistoryInfo> history = Apiserver.Factory.getApiserver().getHistory(ApiBase.APP_KEY, cTime.toString());

                    HttpUrl url = history.request().url();
                    Log.d("MY_INFO", "onClick: =="+url);

                    history.enqueue(new Callback<HistoryInfo>() {
                        @Override
                        public void onResponse(Call<HistoryInfo> call, Response<HistoryInfo> response) {
                            Log.d("MY_INFO", "onResponse: "+response.body().toString());

                            String msg = response.body().getMsg();
                            if (msg.equals("success")) {
                                lists.clear();
                                lists.addAll(response.body().getResult());
                                hisAdapter.notifyDataSetChanged();
                            } else {
                                Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<HistoryInfo> call, Throwable t) {
                            Toast.makeText(getContext(),"网络异常",Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        }).setType(TimePickerView.Type.YEAR_MONTH_DAY)//默认全部显示
//                    .setCancelText("Cancel")//取消按钮文字
//                    .setSubmitText("Sure")//确认按钮文字
                .setContentSize(16)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleText("选择查询时间")//标题文字
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(false)//是否循环滚动
//                    .setTitleColor(Color.BLACK)//标题文字颜色
//                    .setSubmitColor(Color.BLUE)//确定按钮文字颜色
//                    .setCancelColor(Color.BLUE)//取消按钮文字颜色
//                    .setTitleBgColor(0xFF666666)//标题背景颜色 Night mode
//                    .setBgColor(0xFF333333)//滚轮背景颜色 Night mode
//                    .setRange(selectedDate.get(Calendar.YEAR) ,0)//默认是1900-2100年
//                    .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate,endDate)//起始终止年月日设定
//                    .setLabel("年","月","日","时","分","秒")
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                    .isDialog(true)//是否显示为对话框样式
                .build();
        pvTime.setDate(Calendar.getInstance());
        pvTime.show();
    }
}
