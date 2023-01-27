package com.vts.di.data.remote;

import androidx.lifecycle.LiveData;

import com.vts.di.data.entity.UserEntity;
import com.vts.di.model.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface UserService {
    @GET("users")
    Observable<List<UserEntity>> getUsersFromApi();
}
