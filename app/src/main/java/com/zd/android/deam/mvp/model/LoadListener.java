package com.zd.android.deam.mvp.model;


import com.zd.android.deam.mvp.bean.HistoryInfo;

import java.util.List;

/**
 * Created by suzy on 2017/4/5.
 **/

public interface LoadListener {

    void onSuccess(List<HistoryInfo.ResultBean> list);

    void onError(String msg);
}
