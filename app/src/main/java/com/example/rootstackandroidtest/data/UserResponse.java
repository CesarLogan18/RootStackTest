package com.example.rootstackandroidtest.data;

import com.example.rootstackandroidtest.model.UserModel;

import java.util.List;

public class UserResponse {
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<UserModel> data;

    public List<UserModel> getData() {
        return data;
    }
}
