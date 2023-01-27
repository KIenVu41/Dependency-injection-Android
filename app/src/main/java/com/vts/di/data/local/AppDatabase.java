package com.vts.di.data.local;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.vts.di.data.entity.UserEntity;
import com.vts.di.data.local.dao.UserDao;

@Database(entities = {UserEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
