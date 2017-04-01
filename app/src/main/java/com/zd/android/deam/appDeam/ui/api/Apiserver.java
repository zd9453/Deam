package com.zd.android.deam.appDeam.ui.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.zd.android.deam.appDeam.ui.weather.bean.HistoryInfo;
import com.zd.android.deam.appDeam.ui.weather.bean.WeatherInfo;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by suzy on 2017/3/22.
 **/

public interface Apiserver {

    class Factory{
        public static Apiserver getApiserver(){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiBase.WEATHER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            return retrofit.create(Apiserver.class);
        }
    }

    /**
     * 获取天气数据
     * @param key appkey
     * @param city 查询城市
     * @param province 城市的省份
     * @return 获取的天气数据
     */
    @GET("v1/weather/query?")
    Call<WeatherInfo> getWeather(@Query("key") String key,@Query("city") String city,@Query("province") String province);

    /**
     * 历史上的今天事件
     * @param key appkey
     * @param day   日期
     * @return  历史事件
     */
    @GET("appstore/history/query?")
    Call<HistoryInfo> getHistory(@Query("key") String key, @Query("day") String day);


    @GET("appstore/history/query?")
    Observable<HistoryInfo> gethis(@Query("key") String key, @Query("day") String day);
}
