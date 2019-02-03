package com.example.rodrigo_gangone.testclima.Model.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.rodrigo_gangone.testclima.Adapter.AdapterRecyclerViewHome;
import com.example.rodrigo_gangone.testclima.Api.JsonPlaceHolderApi;
import com.example.rodrigo_gangone.testclima.Model.CityCurrentData;
import com.example.rodrigo_gangone.testclima.Model.CurrentWeatherData;
import com.example.rodrigo_gangone.testclima.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity implements Callback<CurrentWeatherData> {

    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    public static final String ICON_URL = "http://openweathermap.org/img/w/";
    public static final String ICON_URL_PNG = ".png";
    public static final String API_ID = "358a65a06e0160a3309fa14cb5548e2f";
    public static final String ID_CITY_KEY = "id_city_key";

    private List<CityCurrentData> mCityCurrentDataArrayList;
    private AdapterRecyclerViewHome mAdapterRecyclerViewHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerBuilder();
        loadClimaActual();
    }

    public void recyclerBuilder(){
        mCityCurrentDataArrayList = new ArrayList<>();

        final RecyclerView recyclerView = findViewById(R.id.recyclerViewHomeActivity);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapterRecyclerViewHome = new AdapterRecyclerViewHome(this, mCityCurrentDataArrayList);

        mAdapterRecyclerViewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this , DetailActivity.class);
                intent.putExtra(ID_CITY_KEY , mCityCurrentDataArrayList.get(recyclerView.getChildAdapterPosition(view)).id);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(mAdapterRecyclerViewHome);
    }


    public void loadClimaActual() {
        String sIdCiudades = "6559994,524901,703448,2643743,5128638";
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<CurrentWeatherData> call = jsonPlaceHolderApi.getClimaListaDeCiudades(sIdCiudades, "metric", API_ID);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<CurrentWeatherData> call, Response<CurrentWeatherData> response) {
        if (response.isSuccessful()) {
            CurrentWeatherData ciudadesList = response.body();
            mCityCurrentDataArrayList.addAll(ciudadesList.list);
            mAdapterRecyclerViewHome.notifyDataSetChanged();
        } else {
            System.out.print(response.errorBody().toString());
        }
    }

    @Override
    public void onFailure(Call<CurrentWeatherData> call, Throwable t) {
        t.printStackTrace();
    }

}
