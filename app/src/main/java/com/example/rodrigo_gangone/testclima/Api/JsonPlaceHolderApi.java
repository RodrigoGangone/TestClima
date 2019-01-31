package com.example.rodrigo_gangone.testclima.Api;

import com.example.rodrigo_gangone.testclima.Model.GetCiudadesClima;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
    @GET("group")
    Call<GetCiudadesClima> getClimaListaDeCiudades(
            @Query("id") String cityId,
            @Query("units") String units,
            @Query("appid") String apiId
    );

    //@GET("forecast")
    //Call<>
}
