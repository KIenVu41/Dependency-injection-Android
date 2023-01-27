package com.vts.di.data.repository;

import com.vts.di.data.entity.UserEntity;
import com.vts.di.model.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;


public interface UserRepository {

    Observable<List<UserEntity>> fetchUsers();
    Completable insertAll(List<User> users);
    Observable<List<UserEntity>> getAllFromDb();
}
