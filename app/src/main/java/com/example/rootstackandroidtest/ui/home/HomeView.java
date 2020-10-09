package com.example.rootstackandroidtest.ui.home;

import com.example.rootstackandroidtest.model.UserModel;

import java.util.List;

public interface HomeView {

    void onNewData(List<UserModel> data);

    void onDataError();
}
