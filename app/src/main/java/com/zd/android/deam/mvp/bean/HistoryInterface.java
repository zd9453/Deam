package com.zd.android.deam.mvp.bean;

import java.util.List;

/**
 * Created by suzy on 2017/4/5.
 **/

public interface HistoryInterface {
    interface presenter extends BasePresenter{
        void getInfo(String time);
    }

    interface View extends IBaseView<presenter>{

        void getDo(String time);

        void showHis(List<HistoryInfo.ResultBean> list);

        void showError(String msg);
    }
}
