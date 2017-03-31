package com.zd.android.deam.appDeam.ui.weather.bean.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zd.android.deam.R;
import com.zd.android.deam.appDeam.ui.weather.bean.HistoryInfo;

import java.util.List;

/**
 * Created by suzy on 2017/3/28.
 **/

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HisHolder>{

    private List<HistoryInfo.ResultBean> date;

    public HistoryAdapter(List<HistoryInfo.ResultBean> date) {
        this.date = date;
    }

    @Override
    public HisHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HisHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_history_item,parent,false));
    }

    @Override
    public void onBindViewHolder(HisHolder holder, int position) {

        String date = this.date.get(position).getDate();
        int length = date.length();
        String day = date.substring(length - 2, length);
        String M = date.substring(length-4,length-2);
        String y = date.substring(0,length-4);

        String hTime = y+" - "+M+" - "+day;

        holder.his_time.setText(hTime);

        holder.his_content.setText(this.date.get(position).getEvent());
    }

    @Override
    public int getItemCount() {
        return date.size();
    }

    static class HisHolder extends RecyclerView.ViewHolder {
        private TextView his_time,his_content;
        public HisHolder(View itemView) {
            super(itemView);
            his_time = (TextView) itemView.findViewById(R.id.his_time);
            his_content = (TextView) itemView.findViewById(R.id.his_content);
        }
    }
}
