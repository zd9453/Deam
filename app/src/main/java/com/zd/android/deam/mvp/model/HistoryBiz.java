package com.zd.android.deam.mvp.model;

import android.util.Log;

import com.zd.android.deam.appDeam.ui.api.ApiBase;
import com.zd.android.deam.appDeam.ui.api.Apiserver;
import com.zd.android.deam.mvp.bean.HistoryInfo;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by suzy on 2017/3/31.
 **/

public class HistoryBiz implements HistoryModel {

    @Override
    public void getData(String date, final LoadListener listener) {
        Observable<HistoryInfo> gethis = Apiserver.Factory.getApiserver().gethis(ApiBase.APP_KEY, date);

        gethis.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HistoryInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HistoryInfo historyInfo) {
                        listener.onSuccess(historyInfo.getResult());
                        Log.d("MY_INFO", "onNext: ====="+historyInfo.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("MY_INFO", "onComplete: ====加载完成");
                    }
                });

    }


}
