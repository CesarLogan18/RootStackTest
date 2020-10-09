package com.example.rootstackandroidtest.ui.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rootstackandroidtest.R;
import com.example.rootstackandroidtest.model.UserModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.AdapterViewHolder> {
    private List<UserModel> dataSet = new ArrayList<>();
    private Context context;

    public static class AdapterViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivAvatar;
        private TextView tvFirstName;
        private TextView tvLastName;
        private TextView tvEmail;

        public AdapterViewHolder(View v) {
            super(v);
            ivAvatar = v.findViewById(R.id.imageViewPhoto);
            tvFirstName = v.findViewById(R.id.textViewFirstName);
            tvLastName = v.findViewById(R.id.textViewLastName);
            tvEmail = v.findViewById(R.id.textViewEmail);
        }
    }

    public void setDataSet(List<UserModel> newData) {
        dataSet.clear();
        dataSet.addAll(newData);
    }

    public UserAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(ViewGroup parent,
                                                int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_row_view, parent, false);

        return new AdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterViewHolder holder, int position) {

        UserModel model = dataSet.get(position);

        holder.tvFirstName.setText(model.getFirstName());
        holder.tvLastName.setText(model.getLastName());
        holder.tvEmail.setText(model.getEmail());
        Picasso.with(context).load(model.getPhotoUrl()).into(holder.ivAvatar);

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}