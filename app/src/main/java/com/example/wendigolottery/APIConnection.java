package com.example.wendigolottery;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIConnection {
    private static final String BASE_URL_API = "http://loteria.cronogramatds.online/";

    public static RestService createConnectionToAPI() {
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_API)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(RestService.class);
    }
}
