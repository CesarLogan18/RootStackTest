package com.example.rootstackandroidtest.ui.queue;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.rootstackandroidtest.R;

public class QueueFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_queue, container, false);
        final TextView textView = root.findViewById(R.id.text_queue);
        textView.setText(getString(R.string.new_features_title, "My Queues"));
        return root;
    }
}