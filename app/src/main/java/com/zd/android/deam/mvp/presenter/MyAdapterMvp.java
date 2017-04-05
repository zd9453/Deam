package com.zd.android.deam.mvp.presenter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zd.android.deam.R;
import com.zd.android.deam.mvp.bean.HistoryInfo;

import java.util.List;

/**
 * Created by suzy on 2017/4/5.
 */

public class MyAdapterMvp extends RecyclerView.Adapter<MyAdapterMvp.Holder> {

    private List<HistoryInfo.ResultBean> list;

    public MyAdapterMvp(List<HistoryInfo.ResultBean> list) {
        this.list = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.testitem, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.textView.setText(list.get(position).getEvent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        private TextView textView;

        public Holder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_text);
        }
    }
}
