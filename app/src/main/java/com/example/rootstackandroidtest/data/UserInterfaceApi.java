package com.example.rootstackandroidtest.data;


import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserInterfaceApi {

    @NonNull
    @GET("users")
    Call<UserResponse> getUsers(@Query("page") int page);

}
