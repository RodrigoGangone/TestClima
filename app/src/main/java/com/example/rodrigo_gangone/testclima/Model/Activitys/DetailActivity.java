package com.example.rodrigo_gangone.testclima.Model.Activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.rodrigo_gangone.testclima.Adapter.AdapterRecyclerViewDetail;
import com.example.rodrigo_gangone.testclima.Api.JsonPlaceHolderApi;
import com.example.rodrigo_gangone.testclima.Model.CityDaysDetail;
import com.example.rodrigo_gangone.testclima.Model.FiveDaysWeatherDataDetail;
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

import static com.example.rodrigo_gangone.testclima.Model.Activitys.HomeActivity.API_ID;
import static com.example.rodrigo_gangone.testclima.Model.Activitys.HomeActivity.LANG;
import static com.example.rodrigo_gangone.testclima.Model.Activitys.HomeActivity.UNITS;

public class DetailActivity extends AppCompatActivity implements Callback<FiveDaysWeatherDataDetail> {
    private List<CityDaysDetail> mCiudadDetailArrayList;
    private AdapterRecyclerViewDetail mAdapterRecyclerViewDetail;
    private LinearLayout llErrorConnection;
    public RecyclerView recyclerView;
    private ProgressBar loadProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather5_days);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        llErrorConnection = findViewById(R.id.llErrorConnection);
        loadProgress = findViewById(R.id.progressBar);
        recyclerBuilder();

        Intent intent = getIntent();
        if (intent != null) {
            Integer id = intent.getIntExtra(HomeActivity.ID_CITY_KEY, 0);
            getDetailOfWeatherCity(id);
        }
    }

    public void recyclerBuilder() {
        mCiudadDetailArrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerViewDetailActivity);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void getDetailOfWeatherCity(Integer id) {
        if (id == 0) {
            return;
        }

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(HomeActivity.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<FiveDaysWeatherDataDetail> call = jsonPlaceHolderApi.getClimaExtendido(id, LANG, UNITS, API_ID);

        loadProgress.setVisibility(View.VISIBLE);

        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<FiveDaysWeatherDataDetail> call, Response<FiveDaysWeatherDataDetail> response) {
        loadProgress.setVisibility(View.GONE);
        if (response.isSuccessful()) {
            FiveDaysWeatherDataDetail climaExtendido = response.body();
            mCiudadDetailArrayList.addAll(climaExtendido.list);

            mAdapterRecyclerViewDetail = new AdapterRecyclerViewDetail(DetailActivity.this, mCiudadDetailArrayList, climaExtendido);
            recyclerView.setAdapter(mAdapterRecyclerViewDetail);

            mAdapterRecyclerViewDetail.notifyDataSetChanged();
        } else {

        }
    }

    @Override
    public void onFailure(Call<FiveDaysWeatherDataDetail> call, Throwable t) {
        loadProgress.setVisibility(View.GONE);
        llErrorConnection.setVisibility(View.VISIBLE);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
