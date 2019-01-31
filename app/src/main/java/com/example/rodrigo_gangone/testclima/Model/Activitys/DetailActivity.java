package com.example.rodrigo_gangone.testclima.Model.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.rodrigo_gangone.testclima.Adapter.AdapterRecyclerViewDetail;
import com.example.rodrigo_gangone.testclima.Model.CityDetail;
import com.example.rodrigo_gangone.testclima.R;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private List<CityDetail> mCiudadDetailArrayList;
    private AdapterRecyclerViewDetail mAdapterRecyclerViewDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather5_days);

        recyclerBuilder();
    }

    public void recyclerBuilder(){
        mCiudadDetailArrayList = new ArrayList<>();

        final RecyclerView recyclerView = findViewById(R.id.recyclerViewDetailActivity);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapterRecyclerViewDetail = new AdapterRecyclerViewDetail(DetailActivity.this, mCiudadDetailArrayList);

        /*mAdapterRecyclerViewDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, mCiudadDetailArrayList.get(recyclerView.getChildAdapterPosition(view)).name, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomeActivity.this , DetailActivity.class);
                startActivity(intent);
            }
        });*/
        recyclerView.setAdapter(mAdapterRecyclerViewDetail);
    }



}
