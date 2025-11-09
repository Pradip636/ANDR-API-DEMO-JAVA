package com.app.apidemo.model;

import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("image")
    String image;

    @SerializedName("city")
    String city;

    @SerializedName("age")
    String age;

    @SerializedName("name")
    String name;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
