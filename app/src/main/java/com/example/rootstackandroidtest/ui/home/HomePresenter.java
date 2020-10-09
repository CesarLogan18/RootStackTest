package com.example.rootstackandroidtest.ui.home;

import android.os.Handler;
import android.util.Log;

import com.example.rootstackandroidtest.data.UserRepository;
import com.example.rootstackandroidtest.data.UserResponse;
import com.example.rootstackandroidtest.model.UserModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter {

    private int currentPage = 1;
    private UserRepository userRepository;
    private HomeView view;
    private Handler handler = new Handler();
    private List<UserModel> users = new ArrayList<>();

    public HomePresenter(HomeView view) {
        this.view = view;
        userRepository = new UserRepository();
    }

    public List<UserModel> filterUsers(String filter) {

        String filterPattern = filter.toLowerCase().trim();

        List<UserModel> filteredUsers = new ArrayList<>();

        for (UserModel user : users) {
            if (user.getFirstName().contains(filterPattern) || user.getLastName().contains(filterPattern) ||
                    user.getEmail().contains(filterPattern)) {
                filteredUsers.add(user);
            }
        }

        return filteredUsers;
    }

    public void getUsers(String filter) {
        handler.postDelayed(() -> userRepository.getUsers(currentPage).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                Log.i("data", new Gson().toJson(response.body()));
                users.addAll(response.body().getData());
                view.onNewData(filterUsers(filter));
                currentPage++;
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                view.onDataError();
            }
        }), 1000);

    }
}