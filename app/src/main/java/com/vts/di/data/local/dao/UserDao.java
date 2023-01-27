package com.vts.di.data.local.dao;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.vts.di.data.entity.UserEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll(List<UserEntity> userEntities);

    @Query("SELECT * FROM user")
    Observable<List<UserEntity>> getAll();
}
