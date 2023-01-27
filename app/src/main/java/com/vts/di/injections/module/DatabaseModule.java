package com.vts.di.injections.module;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

import com.vts.di.data.local.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

    @Singleton
    @Provides
    public static AppDatabase provideDatabase(@ApplicationContext Context applicationContext) {
        return Room.databaseBuilder(applicationContext,
                AppDatabase.class, "users").build();
    }
}
