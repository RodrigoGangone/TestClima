package com.example.rodrigo_gangone.testclima.Api;

import com.example.rodrigo_gangone.testclima.Model.Ciudad;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
    @GET("group")
    Call<com.example.rodrigo_gangone.testclima.Model.List> getClimaListaDeCiudades(
            @Query("id") Integer[] cityId,
            @Query("units") String units,
            @Query("appid") String apiId
    );
}
