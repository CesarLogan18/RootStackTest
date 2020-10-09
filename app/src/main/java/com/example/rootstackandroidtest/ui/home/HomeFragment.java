package com.example.rootstackandroidtest.ui.home;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rootstackandroidtest.R;
import com.example.rootstackandroidtest.model.UserModel;
import com.example.rootstackandroidtest.ui.user.UserAdapter;

import java.util.List;

public class HomeFragment extends Fragment implements HomeView {

    private HomePresenter homePresenter;
    private EditText etSearch;
    private RecyclerView recyclerView;
    private UserAdapter adapter;
    private LinearLayoutManager manager;
    private boolean loading = false;
    private ProgressDialog pd;
    private Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        pd = new ProgressDialog(context);
        manager = new LinearLayoutManager(context);
        adapter = new UserAdapter(getActivity());
        homePresenter = new HomePresenter(this);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        etSearch = root.findViewById(R.id.editTextTextUser);
        recyclerView = root.findViewById(R.id.my_recycler_view);

        setupList();
        setupSearch();

        homePresenter.getUsers("");
        showProgressDialog();

        return root;
    }

    @Override
    public void onNewData(List<UserModel> data) {
        adapter.setDataSet(data);
        adapter.notifyDataSetChanged();
        loading = false;
        hideProgressDialog();
    }

    @Override
    public void onDataError() {
        Toast.makeText(context, "Error obtaining Data, please restart app", Toast.LENGTH_LONG).show();
        hideProgressDialog();
    }

    private void showProgressDialog() {
        pd.setMessage("loading");
        pd.show();
    }

    private void hideProgressDialog() {
        pd.hide();
    }

    private void setupList() {
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(
                new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    int visibleItemCount = manager.getChildCount();
                    int totalItemCount = manager.getItemCount();
                    int pastVisiblesItems = manager.findFirstVisibleItemPosition();

                    if (!loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = true;
                            homePresenter.getUsers(etSearch.getText().toString());

                        }
                    }
                }
            }
        });


    }

    private void setupSearch() {
        etSearch.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                onNewData(homePresenter.filterUsers(s.toString()));
            }
        });

        etSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideKeyboard(getActivity());
                return true;
            }
            return false;
        });
    }

    private static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);

        View view = activity.getCurrentFocus();

        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}