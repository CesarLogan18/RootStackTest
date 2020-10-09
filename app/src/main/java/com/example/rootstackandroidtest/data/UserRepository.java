package com.example.rootstackandroidtest.data;

import retrofit2.Call;

public class UserRepository {

    private UserInterfaceApi userInterfaceApi;

    public UserRepository()  {
        userInterfaceApi = RetrofitGenerator.getUserInterfaceApi();
    }

    public Call<UserResponse> getUsers(int page) {
        return userInterfaceApi.getUsers(page);
    }
}
