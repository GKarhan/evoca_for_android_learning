package com.example.evoca.evocaforandroidlearning.api;


import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class ApiManager {

    private RestAdapter restAdapter;
    private static ApiManager instance;

    private ApiManager(){
        restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("http://api.evocalab.com/evoca/test")
                .setClient(new OkClient(new OkHttpClient()))
                .build();
    }

    public static ApiManager getInstance() {
        if(instance == null) {
            instance = new ApiManager();
        }
        return instance;
    }

    public Api getClient() {
        return restAdapter.create(Api.class);
    }
}
