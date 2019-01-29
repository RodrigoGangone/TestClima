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

public class HomeActivity extends AppCompatActivity implements Callback<com.example.rodrigo_gangone.testclima.Model.List> {

    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private static final String API_ID = "358a65a06e0160a3309fa14cb5548e2f";
    private List<com.example.rodrigo_gangone.testclima.Model.List> mCiudadArrayList;
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
        recyclerView.setAdapter(mAdapterRecyclerViewHome);

        //loadFakeClimaActual();
        loadClimaActual();


    }

/*    public void loadFakeClimaActual(){
        for (int i = 0 ; i < 5 ; i++ ){
            Ciudad ciudad = new Ciudad();
            ciudad.setName("Buenos aires");
            mCiudadArrayList.add(ciudad);
        }
        mAdapterRecyclerViewHome.notifyDataSetChanged();
    }*/


    public void loadClimaActual() {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create(gson)).
                build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<com.example.rodrigo_gangone.testclima.Model.List> call = jsonPlaceHolderApi.
                getClimaListaDeCiudades(new Integer[]{6559994, 524901, 703448, 2643743, 5128638},
                        "metric", API_ID);
        call.enqueue(this);
    }

    //Posibles respuestas del callBack al llamar a la API
   /* @Override
    public void onResponse(Call<List<com.example.rodrigo_gangone.testclima.Model.List>> call, Response<List<com.example.rodrigo_gangone.testclima.Model.List>> response) {
        if (response.isSuccessful()) {
            List<com.example.rodrigo_gangone.testclima.Model.List> ciudadList = response.body();
            for (com.example.rodrigo_gangone.testclima.Model.List ciudad : ciudadList) {
                mCiudadArrayList.add(ciudad);
            }
            mAdapterRecyclerViewHome.notifyDataSetChanged();
        } else {
            System.out.print(response.errorBody().toString());
        }
    }

    @Override
    public void onFailure(Call<List<com.example.rodrigo_gangone.testclima.Model.List>> call, Throwable t) {
        t.printStackTrace();
    }*/

    @Override
    public void onResponse(Call<com.example.rodrigo_gangone.testclima.Model.List> call, Response<com.example.rodrigo_gangone.testclima.Model.List> response) {
        if (response.isSuccessful()) {
            com.example.rodrigo_gangone.testclima.Model.List ciudadList = response.body();
            mCiudadArrayList.add(ciudadList);
            mAdapterRecyclerViewHome.notifyDataSetChanged();
        } else {
            System.out.print(response.errorBody().toString());
        }
    }

    @Override
    public void onFailure(Call<com.example.rodrigo_gangone.testclima.Model.List> call, Throwable t) {
        t.printStackTrace();
    }
}
