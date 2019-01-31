package com.example.rodrigo_gangone.testclima.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rodrigo_gangone.testclima.Model.CityDetail;
import com.example.rodrigo_gangone.testclima.R;

import java.util.List;

public class AdapterRecyclerViewDetail extends RecyclerView.Adapter<AdapterRecyclerViewDetail.ClimaDetailViewHolder> {
    private List<CityDetail> cityDetailList;
    private Context context;


    public AdapterRecyclerViewDetail(Context context, List<CityDetail> cityDetailList) {
        this.cityDetailList = cityDetailList;
        this.context = context;
    }

    @Override
    public ClimaDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item_detail, parent, false);
        return new ClimaDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClimaDetailViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return cityDetailList.size();
    }

    public class ClimaDetailViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout rlItemClimaCitydDetail;
        public TextView tvCityNameDetail;
        public TextView tvDateDetail;
        public TextView tvDescriptionDayDetail;
        public TextView tvDescriptionDayTempDetail;
        public TextView tvDescriptionDayTempMinDetail;
        public TextView tvDescriptionDayTempMaxDetail;
        public ImageView imageViewTempTodayDetail;

        public ClimaDetailViewHolder(View itemView) {
            super(itemView);
            rlItemClimaCitydDetail = itemView.findViewById(R.id.rlItemClimaCitydDetail);
            tvCityNameDetail = itemView.findViewById(R.id.tvCityNameDetail);
            tvDateDetail = itemView.findViewById(R.id.tvDateDetail);
            tvDescriptionDayDetail = itemView.findViewById(R.id.tvDescriptionDayDetail);
            tvDescriptionDayTempDetail = itemView.findViewById(R.id.tvDescriptionDayTempDetail);
            tvDescriptionDayTempMinDetail = itemView.findViewById(R.id.tvDescriptionDayTempMinDetail);
            tvDescriptionDayTempMaxDetail = itemView.findViewById(R.id.tvDescriptionDayTempMaxDetail);
            imageViewTempTodayDetail = itemView.findViewById(R.id.imageViewTempTodayDetail);
        }
    }

}
