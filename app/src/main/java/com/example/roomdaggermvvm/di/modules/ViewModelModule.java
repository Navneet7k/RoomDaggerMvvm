package com.example.roomdaggermvvm.di.modules;

import androidx.lifecycle.ViewModel;

import com.example.roomdaggermvvm.view.MainActivityVM;
import com.example.roomdaggermvvm.di.ViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityVM.class)
    abstract ViewModel bindMainActivityVM(MainActivityVM mainActivityVM);
}
