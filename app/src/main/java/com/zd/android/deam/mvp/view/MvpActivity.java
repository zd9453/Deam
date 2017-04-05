package com.zd.android.deam.mvp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zd.android.deam.R;
import com.zd.android.deam.mvp.bean.HistoryInfo;
import com.zd.android.deam.mvp.bean.HistoryInterface;
import com.zd.android.deam.mvp.presenter.MyAdapterMvp;
import com.zd.android.deam.mvp.presenter.Presenter;

import java.util.ArrayList;
import java.util.List;

public class MvpActivity extends AppCompatActivity implements HistoryInterface.View, View.OnClickListener {

    private RecyclerView recyclerview;
    private Button button;
    private List<HistoryInfo.ResultBean> data = new ArrayList<>();
    private MyAdapterMvp myAdapterMvp;
    private Presenter mpresenter = new Presenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        initViews();
    }

    private void initViews() {
        recyclerview = (RecyclerView) findViewById(R.id.mvp_recycler);
        button = (Button) findViewById(R.id.bt_do);
        button.setOnClickListener(this);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        myAdapterMvp = new MyAdapterMvp(data);
        recyclerview.setAdapter(myAdapterMvp);
    }

    @Override
    public void onClick(View v) {
        mpresenter.getInfo("0101");
    }

    @Override
    public void getDo(String time) {

    }

    @Override
    public void showHis(List<HistoryInfo.ResultBean> list) {
        data.addAll(list);
        myAdapterMvp.notifyDataSetChanged();
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void setPresenter(HistoryInterface.presenter presenter) {
        this.mpresenter = new Presenter(this);
    }
}
