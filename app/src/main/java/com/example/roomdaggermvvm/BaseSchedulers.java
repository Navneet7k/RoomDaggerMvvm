package com.example.roomdaggermvvm;

import androidx.annotation.NonNull;

import io.reactivex.Scheduler;

public interface BaseSchedulers {

    @NonNull
    Scheduler io();

    @NonNull
    Scheduler ui();

}
