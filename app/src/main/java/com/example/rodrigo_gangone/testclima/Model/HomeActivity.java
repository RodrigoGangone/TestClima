package com.example.rodrigo_gangone.testclima.Model;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.rodrigo_gangone.testclima.Adapter.AdapterRecyclerViewHome;
import com.example.rodrigo_gangone.testclima.Api.JsonPlaceHolderApi;
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

public class HomeActivity extends AppCompatActivity implements Callback<GetCiudadesBodyResponseBean> {

    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private static final String API_ID = "358a65a06e0160a3309fa14cb5548e2f";
    private List<Ciudad> mCiudadArrayList;
    private AdapterRecyclerViewHome mAdapterRecyclerViewHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerBuild();

        loadClimaActual();
    }

    public void recyclerBuild(){
        mCiudadArrayList = new ArrayList<>();

        final RecyclerView recyclerView = findViewById(R.id.recyclerViewHomeActivity);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapterRecyclerViewHome = new AdapterRecyclerViewHome(this, mCiudadArrayList);

        mAdapterRecyclerViewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, mCiudadArrayList.get(recyclerView.getChildAdapterPosition(view)).name, Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(mAdapterRecyclerViewHome);
    }


    public void loadClimaActual() {
        String sIdCiudades = "6559994,524901,703448,2643743,5128638";
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<GetCiudadesBodyResponseBean> call = jsonPlaceHolderApi.getClimaListaDeCiudades(sIdCiudades, "metric", API_ID);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<GetCiudadesBodyResponseBean> call, Response<GetCiudadesBodyResponseBean> response) {
        if (response.isSuccessful()) {
            GetCiudadesBodyResponseBean ciudadesList = response.body();
            mCiudadArrayList.addAll(ciudadesList.list);
            mAdapterRecyclerViewHome.notifyDataSetChanged();
        } else {
            System.out.print(response.errorBody().toString());
        }
    }

    @Override
    public void onFailure(Call<GetCiudadesBodyResponseBean> call, Throwable t) {
        t.printStackTrace();
    }


}
