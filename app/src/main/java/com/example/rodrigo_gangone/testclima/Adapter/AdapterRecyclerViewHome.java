package com.example.rodrigo_gangone.testclima.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rodrigo_gangone.testclima.Model.CityCurrentData;
import com.example.rodrigo_gangone.testclima.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.rodrigo_gangone.testclima.Model.Activitys.HomeActivity.ICON_URL;
import static com.example.rodrigo_gangone.testclima.Model.Activitys.HomeActivity.ICON_URL_PNG;

public class AdapterRecyclerViewHome extends RecyclerView.Adapter<AdapterRecyclerViewHome.ClimaViewHolder> implements View.OnClickListener {
    private List<CityCurrentData> mCityCurrentDataList;
    private Context mContext;
    private View.OnClickListener listener;

    public AdapterRecyclerViewHome(Context context, List<CityCurrentData> cityCurrentDataList) {
        this.mCityCurrentDataList = cityCurrentDataList;
        this.mContext = context;
    }

    @Override
    public ClimaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ciudad_item, parent, false);
        view.setOnClickListener(this);
        return new ClimaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClimaViewHolder holder, int position) {
        final CityCurrentData cityCurrentData = mCityCurrentDataList.get(position);

        String codeImageWeatherList = cityCurrentData.weather.get(0).icon;

        holder.tvCiudadName.setText(cityCurrentData.name);
        holder.tvDescriptionDay.setText(cityCurrentData.weather.get(0).description);
        holder.tvDescriptionDayTemp.setText(String.valueOf(cityCurrentData.main.temp).replaceAll(".$", "").concat(mContext.getString(R.string.celsius)));
        holder.tvDescriptionDayTempMin.setText(String.valueOf(cityCurrentData.main.temp_min).replaceAll(".$", "").concat(mContext.getString(R.string.celsius)));
        holder.tvDescriptionDayTempMax.setText(String.valueOf(cityCurrentData.main.temp_max).replaceAll(".$", "").concat(mContext.getString(R.string.celsius)));
        holder.tvDescriptionDayHumidity.setText(mContext.getString(R.string.humidity).concat(String.valueOf(cityCurrentData.main.humidity).concat(mContext.getString(R.string.percentage))));

        Picasso.get().load(ICON_URL + codeImageWeatherList + ICON_URL_PNG).
                placeholder(R.drawable.ic_refresh).
                error(R.drawable.ic_error_icon).resize(20, 20).
                into(holder.imageViewTempToday);

    }

    @Override
    public int getItemCount() {
        return mCityCurrentDataList.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }


    public class ClimaViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout rlItemClimaCiudad;
        public TextView tvCiudadName;
        public TextView tvDescriptionDay;
        public TextView tvDescriptionDayTemp;
        public TextView tvDescriptionDayTempMin;
        public TextView tvDescriptionDayTempMax;
        public TextView tvDescriptionDayHumidity;
        public ImageView imageViewTempToday;

        public ClimaViewHolder(View itemView) {
            super(itemView);
            rlItemClimaCiudad = itemView.findViewById(R.id.rlItemClimaCiudad);
            tvCiudadName = itemView.findViewById(R.id.tvCiudadName);
            tvDescriptionDay = itemView.findViewById(R.id.tvDescriptionDay);
            tvDescriptionDayTemp = itemView.findViewById(R.id.tvDescriptionDayTemp);
            tvDescriptionDayTempMin = itemView.findViewById(R.id.tvDescriptionDayTempMin);
            tvDescriptionDayTempMax = itemView.findViewById(R.id.tvDescriptionDayTempMax);
            tvDescriptionDayHumidity = itemView.findViewById(R.id.tvDescriptionDayHumidity);
            imageViewTempToday = itemView.findViewById(R.id.imageViewTempToday);
        }
    }
}
