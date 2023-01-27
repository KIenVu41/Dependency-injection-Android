package com.vts.di.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vts.di.R;
import com.vts.di.data.entity.UserEntity;
import com.vts.di.data.local.AppDatabase;
import com.vts.di.data.mapper.UserEntity2UserModel;
import com.vts.di.data.mapper.UserModel2UserEntity;
import com.vts.di.model.User;
import com.vts.di.ui.adapter.UserAdapter;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ProgressBar pb;
    RecyclerView rv;
    UserAdapter userAdapter;
    MainViewModel mainViewModel;
    UserModel2UserEntity userModel2UserEntity;
    UserEntity2UserModel userEntity2UserModel;
    FloatingActionButton fab;
    @Inject
    AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userModel2UserEntity = new UserModel2UserEntity();
        userEntity2UserModel = new UserEntity2UserModel();
        initView();
        observeData();
        mainViewModel.getUserFromApi();
    }

    private void initView() {
        pb = findViewById(R.id.pb);
        rv = findViewById(R.id.rvUsers);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
        userAdapter = new UserAdapter(User.userDiffUtil);
        rv.setAdapter(userAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void observeData() {
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getProgressLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                pb.setVisibility(integer);
            }
        });
        mainViewModel.getLocalLiveData().observe(this, new Observer<List<UserEntity>>() {
            @Override
            public void onChanged(List<UserEntity> users) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    userAdapter.submitList(users.stream().map(o -> userEntity2UserModel.map(o)).collect(Collectors.toList()));
                }
            }
        });
        mainViewModel.getNetWorkLiveData().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                mainViewModel.insertAll(users);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    mainViewModel.getUserFromDb();
                }
                Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}