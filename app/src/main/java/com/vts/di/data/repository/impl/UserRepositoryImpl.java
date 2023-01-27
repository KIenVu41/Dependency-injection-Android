package com.vts.di.data.repository.impl;

import android.os.Build;

import com.vts.di.data.entity.UserEntity;
import com.vts.di.data.local.AppDatabase;
import com.vts.di.data.mapper.UserEntity2UserModel;
import com.vts.di.data.mapper.UserModel2UserEntity;
import com.vts.di.data.remote.UserService;
import com.vts.di.data.repository.UserRepository;
import com.vts.di.model.User;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Retrofit;

public class UserRepositoryImpl implements UserRepository {

    UserService userService;
    AppDatabase appDatabase;
    UserModel2UserEntity userModel2UserEntity;

    @Inject
    public UserRepositoryImpl(UserService userService, AppDatabase appDatabase) {
        this.userService = userService;
        this.appDatabase = appDatabase;
        userModel2UserEntity = new UserModel2UserEntity();
    }

    @Override
    public Observable<List<UserEntity>> fetchUsers() {
        return userService.getUsersFromApi();
    }

    @Override
    public Completable insertAll(List<User> users) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return appDatabase.userDao().insertAll(users.stream().map(o -> userModel2UserEntity.map(o)).collect(Collectors.toList()));
        }
        return null;
    }

    @Override
    public Observable<List<UserEntity>> getAllFromDb() {
        return appDatabase.userDao().getAll();
    }

}
