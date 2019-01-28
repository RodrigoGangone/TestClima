package com.example.rodrigo_gangone.testclima;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.rodrigo_gangone.testclima.Adapter.AdapterRecyclerViewHome;
import com.example.rodrigo_gangone.testclima.Api.JsonPlaceHolderApi;
import com.example.rodrigo_gangone.testclima.Model.Ciudad;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity implements Callback<List<Ciudad>> {

    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private static final String BASE_URL_PRUEBA = "http://api.openweathermap.org/data/2.5/group?id=524901,703448,2643743,6559994&units=metric&appid=358a65a06e0160a3309fa14cb5548e2f";
    private List<Ciudad> mCiudadArrayList;
    private AdapterRecyclerViewHome mAdapterRecyclerViewHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mCiudadArrayList = new ArrayList<>();


        RecyclerView recyclerView = findViewById(R.id.recyclerViewHomeActivity);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapterRecyclerViewHome = new AdapterRecyclerViewHome(this, mCiudadArrayList);

        loadClimaActual();

    }

    public void loadClimaActual() {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(BASE_URL_PRUEBA).
                addConverterFactory(GsonConverterFactory.create(gson)).
                build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Ciudad>> call = jsonPlaceHolderApi.getListaDeCiudades();
        call.enqueue(this);
    }

    //Posibles respuestas del callBack al llamar a la API
    @Override
    public void onResponse(Call<List<Ciudad>> call, Response<List<Ciudad>> response) {
        if (response.isSuccessful()) {
            List<Ciudad> ciudadList = response.body();
            for (Ciudad ciudad : ciudadList) {
                mCiudadArrayList.add(ciudad);
            }
            mAdapterRecyclerViewHome.notifyDataSetChanged();
        }else{
            System.out.print(response.errorBody().toString());
        }
    }

    @Override
    public void onFailure(Call<List<Ciudad>> call, Throwable t) {
        t.printStackTrace();
    }
}
