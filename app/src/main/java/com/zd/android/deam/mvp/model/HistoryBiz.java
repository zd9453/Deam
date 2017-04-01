package com.zd.android.deam.mvp.model;

import com.zd.android.deam.appDeam.ui.api.ApiBase;
import com.zd.android.deam.appDeam.ui.api.Apiserver;
import com.zd.android.deam.mvp.bean.HistoryInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by suzy on 2017/3/31.
 **/

public class HistoryBiz implements HistoryModel {

    @Override
    public void showInfo() {
        Call<com.zd.android.deam.appDeam.ui.weather.bean.HistoryInfo> history = Apiserver.Factory.getApiserver().getHistory(ApiBase.APP_KEY, "0101");

        history.enqueue(new Callback<com.zd.android.deam.appDeam.ui.weather.bean.HistoryInfo>() {
            @Override
            public void onResponse(Call<com.zd.android.deam.appDeam.ui.weather.bean.HistoryInfo> call, Response<com.zd.android.deam.appDeam.ui.weather.bean.HistoryInfo> response) {

            }

            @Override
            public void onFailure(Call<com.zd.android.deam.appDeam.ui.weather.bean.HistoryInfo> call, Throwable t) {

            }
        });
    }
}
