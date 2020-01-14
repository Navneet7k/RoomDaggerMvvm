package com.example.roomdaggermvvm.di;

import androidx.annotation.NonNull;

import com.example.roomdaggermvvm.BaseSchedulers;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SchedulerProvider implements BaseSchedulers {
    @NonNull
    @Override
    public Scheduler io() {
        return Schedulers.newThread();
    }

    @NonNull
    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }
}
