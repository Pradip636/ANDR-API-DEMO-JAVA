package com.app.apidemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityResponse {

    @SerializedName("success")
    String success;

    @SerializedName("response")
    List<City> response;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<City> getResponse() {
        return response;
    }

    public void setResponse(List<City> response) {
        this.response = response;
    }
}
