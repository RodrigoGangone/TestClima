package com.example.rodrigo_gangone.testclima.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rodrigo_gangone.testclima.Model.City;
import com.example.rodrigo_gangone.testclima.Model.CityDaysDetail;
import com.example.rodrigo_gangone.testclima.Model.FiveDaysWeatherDataDetail;
import com.example.rodrigo_gangone.testclima.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class AdapterRecyclerViewDetail extends RecyclerView.Adapter<AdapterRecyclerViewDetail.ClimaDetailViewHolder> {
    private FiveDaysWeatherDataDetail mFiveDaysWeatherDataDetail;
    private List<CityDaysDetail> mCityDaysDetailList;
    private Context mContext;


    public AdapterRecyclerViewDetail(Context context, List<CityDaysDetail> cityDaysDetailList, FiveDaysWeatherDataDetail fiveDaysWeatherDataDetail) {
        this.mCityDaysDetailList = cityDaysDetailList;
        this.mContext = context;
        this.mFiveDaysWeatherDataDetail = fiveDaysWeatherDataDetail;
    }

    @Override
    public ClimaDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item_detail, parent, false);
        return new ClimaDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClimaDetailViewHolder holder, int position) {
        final CityDaysDetail cityDaysDetail = mCityDaysDetailList.get(position);
        holder.tvCityNameDetail.setText(mFiveDaysWeatherDataDetail.city.name);
        holder.tvDateDetail.setText(cityDaysDetail.dt_txt);
        holder.tvDescriptionDayDetail.setText(cityDaysDetail.weather.get(0).description);
        holder.tvDescriptionDayTempDetail.setText(String.valueOf(cityDaysDetail.main.temp));
        holder.tvDescriptionDayTempMinDetail.setText(String.valueOf(cityDaysDetail.main.temp_min));
        holder.tvDescriptionDayTempMaxDetail.setText(String.valueOf(cityDaysDetail.main.temp_max));
        //todo if dependiendo el getWatherDetail.icon poner un icono determinado
    }

    @Override
    public int getItemCount() {
        return mCityDaysDetailList.size();
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

    public void ParseDateTime(String dateTime) {
        String dtStart = dateTime;
        SimpleDateFormat format = new SimpleDateFormat("YYYY/mm/dd HH:mm:ss");
        try {
            date = format.parse(dtStart);
            System.out.println("Date ->" + date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
