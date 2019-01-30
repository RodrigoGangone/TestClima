package com.example.rodrigo_gangone.testclima.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rodrigo_gangone.testclima.Model.Ciudad;
import com.example.rodrigo_gangone.testclima.R;

import java.util.List;

public class AdapterRecyclerViewHome extends RecyclerView.Adapter<AdapterRecyclerViewHome.ClimaViewHolder>  implements View.OnClickListener {
    private List<Ciudad> mCiudadList;
    private Context mContext;
    private View.OnClickListener listener;

    public AdapterRecyclerViewHome(Context context, List<Ciudad> ciudadList) {
        this.mCiudadList = ciudadList;
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
        final Ciudad ciudad = mCiudadList.get(position);

        //String codeImageWeatherList = ciudad.weather.get(position).icon;

        holder.tvCiudadName.setText(ciudad.name);
        holder.tvDescriptionDay.setText(ciudad.weather.get(0).description);
        holder.tvDescriptionDayTemp.setText(String.valueOf(ciudad.main.temp));
        holder.tvDescriptionDayTempMin.setText(String.valueOf(ciudad.main.tempMin));
        holder.tvDescriptionDayTempMax.setText(String.valueOf(ciudad.main.tempMax));
        holder.tvDescriptionDayHumidity.setText(String.valueOf(ciudad.main.humidity));

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
    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null){
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
