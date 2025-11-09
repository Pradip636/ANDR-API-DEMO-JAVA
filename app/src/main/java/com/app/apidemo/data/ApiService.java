package com.app.apidemo.data;

import com.app.apidemo.model.CityResponse;

import retrofit2.Call;
import retrofit2.http.POST;

public interface ApiService {
    @POST("android_service")
    Call<CityResponse> getData();
}
