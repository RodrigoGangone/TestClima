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
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.rodrigo_gangone.testclima.Model.Activitys.HomeActivity.ICON_URL;
import static com.example.rodrigo_gangone.testclima.Model.Activitys.HomeActivity.ICON_URL_PNG;

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
        String codeImageWeatherList = cityDaysDetail.weather.get(0).icon;
        String weatherDescription = cityDaysDetail.weather.get(0).description;
        String wDFormated = weatherDescription.substring(0, 1).toUpperCase() + weatherDescription.substring(1);

        Date date = null;
        String myDateFormat = null;

        String dateString = cityDaysDetail.dt_txt;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
        SimpleDateFormat myFormat = new SimpleDateFormat("E dd/MM hh:mm a", Locale.getDefault());
        try {
            date = format.parse(dateString);
            myDateFormat = myFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.tvCityNameDetail.setText(mFiveDaysWeatherDataDetail.city.name);
        holder.tvDateDetail.setText(myDateFormat);
        holder.tvDescriptionDayDetail.setText(wDFormated);
        holder.tvDescriptionDayTempDetail.setText(String.valueOf(cityDaysDetail.main.temp).replaceAll(".$", "").concat(mContext.getString(R.string.celsius)));
        holder.tvDescriptionDayTempMinDetail.setText(String.valueOf(cityDaysDetail.main.temp_min).replaceAll(".$", "").concat(mContext.getString(R.string.celsius)));
        holder.tvDescriptionDayTempMaxDetail.setText(String.valueOf(cityDaysDetail.main.temp_max).replaceAll(".$", "").concat(mContext.getString(R.string.celsius)));
        holder.tvDescriptionPressureDetail.setText(mContext.getString(R.string.presion).concat(String.valueOf(cityDaysDetail.main.pressure)).replaceAll(".$", "").concat(mContext.getString(R.string.hPa)));
        holder.tvDescriptionDayHumidityDetail.setText(mContext.getString(R.string.humidity).concat(String.valueOf(cityDaysDetail.main.humidity).concat(mContext.getString(R.string.percentage))));

        Picasso.get().load(ICON_URL + codeImageWeatherList + ICON_URL_PNG).
                placeholder(R.drawable.ic_image_in_progress_24dp).
                error(R.drawable.ic_broken_image_24dp).
                into(holder.imageViewTempTodayDetail);

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
        public TextView tvDescriptionPressureDetail;
        public TextView tvDescriptionDayHumidityDetail;
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
            tvDescriptionPressureDetail = itemView.findViewById(R.id.tvDescriptionPressureDetail);
            tvDescriptionDayHumidityDetail = itemView.findViewById(R.id.tvDescriptionDayHumidityDetail);
            imageViewTempTodayDetail = itemView.findViewById(R.id.imageViewTempTodayDetail);
        }
    }


}
