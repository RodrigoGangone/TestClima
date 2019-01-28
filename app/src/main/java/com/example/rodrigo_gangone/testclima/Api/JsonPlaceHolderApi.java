package com.example.rodrigo_gangone.testclima.Api;

import com.example.rodrigo_gangone.testclima.Model.Ciudad;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
    @GET("group")
    Call<List<Ciudad>> getListaDeCiudades();
}
