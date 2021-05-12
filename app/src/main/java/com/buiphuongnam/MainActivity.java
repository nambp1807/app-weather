package com.buiphuongnam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.buiphuongnam.adapter.WeatherAdapter;
import com.buiphuongnam.model.Item;
import com.buiphuongnam.network.APIManagement;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//public class MainActivity extends AppCompatActivity {
//
//    private RecyclerView rvHour;
//    private TextView tvIcon;
//    private  TextView tvStatus;
//    private   WeatherAdapter adapter;





    public class MainActivity extends AppCompatActivity {

        private RecyclerView rvHour;
        private TextView tvTem;
        private TextView tvStatus;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            tvTem = (TextView)findViewById(R.id.tvIcon);
            tvStatus = (TextView)findViewById(R.id.idStatus);

            getHours();

            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

            rvHour = (RecyclerView) findViewById(R.id.rvHour);
            rvHour.setLayoutManager(layoutManager);
        }

        private void getHours(){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(APIManagement.DOMAIN)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            APIManagement service = retrofit.create(APIManagement.class);

            service.getListData().enqueue(new Callback<List<Item>>() {
                @Override
                public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                    if(response.body() == null) return;
                    List<Item> listWeather = response.body();
                    WeatherAdapter adapter = new WeatherAdapter(MainActivity.this, listWeather);
                    rvHour.setAdapter(adapter);

                    Item weather = listWeather.get(0);
                    tvTem.setText(weather.getTemperature().getValue()+"°");
                    tvStatus.setText(weather.getIconPhrase());
                }

                @Override
                public void onFailure(Call<List<Item>> call, Throwable t) {
                    Log.d("TAG", "error");
                }
            });
        }
    }
