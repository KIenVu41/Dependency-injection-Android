package com.vts.di.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.os.Build;
import android.util.Log;
import android.view.View;

import com.vts.di.data.entity.UserEntity;
import com.vts.di.data.mapper.UserEntity2UserModel;
import com.vts.di.data.repository.UserRepository;
import com.vts.di.model.User;

import org.reactivestreams.Subscription;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.CompletableObserver;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@HiltViewModel
public class MainViewModel extends ViewModel {

//    @Inject
//    UserRepository userRepository;
    private final UserRepository repository;
    private UserEntity2UserModel userEntity2UserModel;

    private CompositeDisposable compositeDisposable;

    @Inject
    MainViewModel(UserRepository repository)
    {
        this.repository = repository;
        userEntity2UserModel = new UserEntity2UserModel();
        compositeDisposable = new CompositeDisposable();
    }

    private MutableLiveData<List<User>> netWorkLiveData = new MutableLiveData<>();
    private MutableLiveData<Integer> progressLiveData = new MutableLiveData<>();
    private MutableLiveData<List<UserEntity>> localLiveData = new MutableLiveData<>();

    public void getUserFromApi() {
        repository.fetchUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<UserEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<UserEntity> userEntities) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            netWorkLiveData.postValue(userEntities.stream().map(o -> userEntity2UserModel.map(o)).collect(Collectors.toList()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("TAG", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void insertAll(List<User> users) {
        repository.insertAll(users)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void getUserFromDb() {
        progressLiveData.setValue(View.VISIBLE);
        compositeDisposable.add(repository.getAllFromDb()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(t -> {
                    localLiveData.postValue(t);
                    progressLiveData.postValue(View.GONE);
                }));
    }

    public LiveData<List<User>> getNetWorkLiveData() {
        return netWorkLiveData;
    }

    public LiveData<List<UserEntity>> getLocalLiveData() {
        return localLiveData;
    }

    public LiveData<Integer> getProgressLiveData() {
        return progressLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
