package com.example.shreetesh.restapiusingretrofit.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shreetesh on 1/9/18.
 */

public class CreateUserInterface {
    @SerializedName("name")
    public String name;
    @SerializedName("job")
    public String job;
    @SerializedName("id")
    public String id;
    @SerializedName("createdAt")
    public String createdAt;
}
