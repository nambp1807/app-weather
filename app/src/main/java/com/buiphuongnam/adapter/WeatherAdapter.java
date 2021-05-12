package com.buiphuongnam.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.buiphuongnam.R;
import com.buiphuongnam.model.Item;
import com.buiphuongnam.model.Temperature;
import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<Item> listItem;

    public WeatherAdapter(Activity activity, List<Item> listItem) {
        this.activity = activity;
        this.listItem = listItem;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = activity.getLayoutInflater().inflate(R.layout.item, parent, false);
        HourHolder hd = new HourHolder(view);
        return hd;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HourHolder hh = (HourHolder) holder;
        Item item = listItem.get(position);
        hh.tvTime.setText(convertTime(item.getDateTime()));
        hh.tvItems.setText(item.getTemperature().getValue());
        String url = "";
        if (item.getWeatherIcon() < 10) {
            url = "https://developer.accuweather.com/sites/default/files/0" + item.getWeatherIcon() + "-s.png";
        } else {
            url = "https://developer.accuweather.com/sites/default/files/" + item.getWeatherIcon() + "-s.png";
        }
        Glide.with(activity).load(url).into(hh.ivIcon);

    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }


    public class HourHolder extends RecyclerView.ViewHolder {

        private TextView tvTime;
        private ImageView ivIcon;
        private TextView tvItems;


        public HourHolder(@NonNull View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvItems = itemView.findViewById(R.id.tvItems);
            ivIcon = itemView.findViewById(R.id.ivIcon);


        }
    }

    public String convertTime(String inputTime) {
        SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = inFormat.parse(inputTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat outFormat = new SimpleDateFormat("ha");
        String goal = outFormat.format(date);
        return goal;
    }

}
