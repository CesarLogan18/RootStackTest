package com.example.rootstackandroidtest.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitGenerator {
    private final static String BASE_URL = "https://reqres.in/api/";

    private static final Retrofit.Builder retrofitBuilderGSON = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create());

    public static UserInterfaceApi getUserInterfaceApi() {
        Retrofit retrofit = retrofitBuilderGSON.baseUrl(BASE_URL).build();
        return retrofit.create(UserInterfaceApi.class);
    }

}
