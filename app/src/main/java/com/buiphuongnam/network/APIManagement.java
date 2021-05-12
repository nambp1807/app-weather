package com.buiphuongnam.network;

import com.buiphuongnam.model.Item;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIManagement {

    String DOMAIN = "http://dataservice.accuweather.com";

    @GET("/forecasts/v1/hourly/12hour/353412?apikey=93Qg780lHwYM4SO58n7DFPLqHg4oKADn&language=vi-vn&metric=true")
    Call<Item> getItemData();

    @GET("/forecasts/v1/hourly/12hour/353412?apikey=93Qg780lHwYM4SO58n7DFPLqHg4oKADn&language=vi-vn&metric=true")
    Call<List<Item>> getListData();
}
