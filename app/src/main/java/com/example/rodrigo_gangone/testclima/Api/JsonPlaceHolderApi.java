package com.example.rodrigo_gangone.testclima.Api;

import com.example.rodrigo_gangone.testclima.Model.CurrentWeatherData;
import com.example.rodrigo_gangone.testclima.Model.FiveDaysWeatherDataDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
    @GET("group")
    Call<CurrentWeatherData> getClimaListaDeCiudades(
            @Query("id") String cityId,
            @Query("units") String units,
            @Query("appid") String apiId
    );

    @GET("forecast")
    Call<FiveDaysWeatherDataDetail> getClimaExtendido(
            @Query("id") Integer cityId,
            @Query("units")String units,
            @Query("appid") String apiId
    );
}
