package com.vts.di.injections.module;

import com.vts.di.data.local.AppDatabase;
import com.vts.di.data.remote.UserService;
import com.vts.di.data.repository.UserRepository;
import com.vts.di.data.repository.impl.UserRepositoryImpl;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.scopes.ViewModelScoped;

@Module
@InstallIn(ViewModelComponent.class)
public class ViewModelModule {

    @Provides
    @ViewModelScoped
    public UserRepository provideUserRepository(UserService userService, AppDatabase appDatabase) {
        return new UserRepositoryImpl(userService, appDatabase);
    }
}
