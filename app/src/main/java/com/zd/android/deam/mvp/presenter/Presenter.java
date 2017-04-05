package com.zd.android.deam.mvp.presenter;

import com.zd.android.deam.mvp.bean.HistoryInfo;
import com.zd.android.deam.mvp.bean.HistoryInterface;
import com.zd.android.deam.mvp.model.HistoryBiz;
import com.zd.android.deam.mvp.model.LoadListener;

import java.util.List;

/**
 * Created by suzy on 2017/4/5.
 **/

public class Presenter implements HistoryInterface.presenter {

    private HistoryBiz historyBiz;
    private HistoryInterface.View viewMvp;

    public Presenter(HistoryInterface.View viewMvp) {
        this.viewMvp = viewMvp;
        this.historyBiz = new HistoryBiz();
    }

    @Override
    public void start() {

    }

    @Override
    public void getInfo(String time) {
        historyBiz.getData(time, new LoadListener() {
            @Override
            public void onSuccess(List<HistoryInfo.ResultBean> list) {
                viewMvp.showHis(list);
            }

            @Override
            public void onError(String msg) {
                viewMvp.showError(msg);
            }
        });
    }
}
