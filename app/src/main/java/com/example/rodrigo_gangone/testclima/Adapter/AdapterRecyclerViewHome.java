package com.example.rodrigo_gangone.testclima.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rodrigo_gangone.testclima.Model.Ciudad;
import com.example.rodrigo_gangone.testclima.R;

import java.util.List;

public class AdapterRecyclerViewHome extends RecyclerView.Adapter<AdapterRecyclerViewHome.ClimaViewHolder> {
    private List<Ciudad> mCiudadList;
    private Context mContext;

    public AdapterRecyclerViewHome(Context context, List<Ciudad> ciudadList) {
        this.mCiudadList = ciudadList;
        this.mContext = context;
    }


    @Override
    public ClimaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ciudad_item, parent, false);
        return new ClimaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClimaViewHolder holder, int position) {
        final Ciudad ciudad = mCiudadList.get(position);
        String codeImageWeatherList = ciudad.getWeatherList().get(position).getIcon();
        //TODO: se supone que al ponerle la posicion me deberia devolver la info del clima actual en tvDescriptionDay
        holder.tvDescriptionDay.setText(ciudad.getWeatherList().get(position).getDescription());
        holder.tvDescriptionDayTemp.setText((int) ciudad.getMain().getTemp());
        holder.tvDescriptionDayTempMin.setText((int) ciudad.getMain().getTemp_min());
        holder.tvDescriptionDayTempMax.setText((int) ciudad.getMain().getTemp_max());
        holder.tvDescriptionDayHumidity.setText(ciudad.getMain().getHumidity());
        //TODO: Aca hacer un if o switch que dependiendo el string que venga en "icon" poner una foto o gif
        /*if (codeImageWeatherList.equals(XXXX)){
            holder.imageViewTempToday.setImageDrawable(XXXX);
        }else if (codeImageWeatherList.equals(ZZZZ)){
            holder.imageViewTempToday.setImageDrawable(ZZZZ);
        }*/
    }

    @Override
    public int getItemCount() {
        return mCiudadList.size();
    }


    public class ClimaViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDescriptionDay;
        public TextView tvDescriptionDayTemp;
        public TextView tvDescriptionDayTempMin;
        public TextView tvDescriptionDayTempMax;
        public TextView tvDescriptionDayHumidity;
        public ImageView imageViewTempToday;

        public ClimaViewHolder(View itemView) {
            super(itemView);

            tvDescriptionDay = itemView.findViewById(R.id.tvDescriptionDay);
            tvDescriptionDayTemp = itemView.findViewById(R.id.tvDescriptionDayTemp);
            tvDescriptionDayTempMin = itemView.findViewById(R.id.tvDescriptionDayTempMin);
            tvDescriptionDayTempMax = itemView.findViewById(R.id.tvDescriptionDayTempMax);
            tvDescriptionDayHumidity = itemView.findViewById(R.id.tvDescriptionDayHumidity);
            imageViewTempToday = itemView.findViewById(R.id.imageViewTempToday);
        }
    }
}
