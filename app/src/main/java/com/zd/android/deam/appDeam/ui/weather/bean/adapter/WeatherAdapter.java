package com.zd.android.deam.appDeam.ui.weather.bean.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zd.android.deam.R;
import com.zd.android.deam.appDeam.ui.weather.bean.WeatherInfo;

import java.util.List;

/**
 * Created by suzy on 2017/3/23.
 **/

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherHolder> {


    private List<WeatherInfo.ResultBean.FutureBean> list;

    public WeatherAdapter(List<WeatherInfo.ResultBean.FutureBean> list) {
        this.list = list;
    }

    @Override
    public WeatherHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_weather_item, parent, false);
        return new WeatherHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherHolder holder, int position) {
        holder.day.setText(list.get(position).getDate() + "  " + list.get(position).getWeek());
        holder.daytime.setText("白天：" + list.get(position).getDayTime());
        holder.night.setText("晚上：" + list.get(position).getNight());
        holder.wendu.setText("温度：" + list.get(position).getTemperature());
        holder.feng.setText("风速：" + list.get(position).getWind());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class WeatherHolder extends RecyclerView.ViewHolder {

        private TextView day, daytime, night, wendu, feng;

        public WeatherHolder(View itemView) {
            super(itemView);
            day = (TextView) itemView.findViewById(R.id.day);
            daytime = (TextView) itemView.findViewById(R.id.daytime);
            night = (TextView) itemView.findViewById(R.id.night);
            wendu = (TextView) itemView.findViewById(R.id.wendu);
            feng = (TextView) itemView.findViewById(R.id.feng);
        }
    }
}
