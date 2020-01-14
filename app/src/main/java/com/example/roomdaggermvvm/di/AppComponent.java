package com.example.roomdaggermvvm.di;

import android.app.Application;

import com.example.roomdaggermvvm.di.modules.ActivityModule;
import com.example.roomdaggermvvm.di.modules.AppModule;
import com.example.roomdaggermvvm.di.modules.NetworkModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        NetworkModule.class,
        ActivityModule.class}
)
public interface AppComponent {

    void inject(MvvmRoomApp app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
