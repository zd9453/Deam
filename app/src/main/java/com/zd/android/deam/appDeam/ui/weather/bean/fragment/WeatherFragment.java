package com.zd.android.deam.appDeam.ui.weather.bean.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zd.android.deam.R;
import com.zd.android.deam.appDeam.ui.api.ApiBase;
import com.zd.android.deam.appDeam.ui.api.Apiserver;
import com.zd.android.deam.appDeam.ui.weather.bean.WeatherInfo;
import com.zd.android.deam.appDeam.ui.weather.bean.adapter.WeatherAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by suzy on 2017/3/23.
 **/

public class WeatherFragment extends Fragment implements View.OnClickListener{

    private RecyclerView recyclerWeather;
    private String inputCity;
    private Button buttonGet;
    private String TAG = "MY_INFO";
    private EditText input_city;
    private WeatherInfo data = new WeatherInfo();
    private List<WeatherInfo.ResultBean.FutureBean> lists = new ArrayList<>();
    private WeatherAdapter adapter;
    private TextView address;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_weather, container, false);
        input_city= (EditText) view.findViewById(R.id.input_city);
        address = (TextView) view.findViewById(R.id.address);
        address.setVisibility(View.GONE);
        recyclerWeather = (RecyclerView) view.findViewById(R.id.recycler_weather);
        recyclerWeather.setLayoutManager(new GridLayoutManager(getActivity(),2));
        adapter = new WeatherAdapter(lists);
        recyclerWeather.setAdapter(adapter);
        buttonGet = (Button) view.findViewById(R.id.bt_get);
        buttonGet.setOnClickListener(this);
        input_city.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.input_city:
                input_city.clearFocus();
                input_city.setFocusable(true);
                input_city.setFocusableInTouchMode(true);
                input_city.requestFocus();
                input_city.findFocus();
//                Toast.makeText(getContext(),"111",Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_get:
                input_city.clearFocus();
                input_city.setFocusable(false);
                InputMethodManager inputMethodManager =
                        (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(input_city.getWindowToken(), 0);
                inputCity = input_city.getText().toString();
                if (TextUtils.isEmpty(inputCity)) {
                    Toast.makeText(getActivity(),"No Information",Toast.LENGTH_SHORT).show();
                    break;
                }

                Apiserver apiserver = Apiserver.Factory.getApiserver();

                Call<WeatherInfo> call = apiserver.getWeather(ApiBase.APP_KEY, inputCity, null);
                String url = String.valueOf(call.request().url());
                Log.d(TAG, "onClick: ======"+url);
                call.enqueue(new Callback<WeatherInfo>() {
                    @Override
                    public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
                        Log.d(TAG, "onResponse: =======成功="+response.body().toString());
                        String msg = response.body().getMsg();
                        if (msg.equals("success")){
                            data = response.body();
                            lists.clear();
                            lists.addAll(data.getResult().get(0).getFuture());
                            adapter.notifyDataSetChanged();
                            address.setVisibility(View.VISIBLE);
                            recyclerWeather.setVisibility(View.VISIBLE);
                            address.setText(data.getResult().get(0).getProvince()+" - "+data.getResult().get(0).getCity());
                        } else {
                            Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
                            address.setVisibility(View.GONE);
                            recyclerWeather.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherInfo> call, Throwable t) {
                        Toast.makeText(getActivity(),"网络异常",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            default:
                break;
        }
    }
}

//    private View contentView = null;
//    @Overridepublic
//    View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
//        if(contentView==null){
//            contentView = inflater.inflate(R.layout.testlayout, container,false);
//        }
//        if(contentView!=null){
//            return contentView;
//        }
//        return super.onCreateView(inflater, container, savedInstanceState);
//    }
//    @Override
//    public void onDestroyView()	{
//        //移除当前视图，防止重复加载相同视图使得程序闪退
//    // ((ViewGroup)contentView.getParent()).removeView(contentView);
//    // super.onDestroyView();
//    }