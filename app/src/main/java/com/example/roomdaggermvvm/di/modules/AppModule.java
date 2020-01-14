package com.example.roomdaggermvvm.di.modules;

import android.app.Application;
import android.content.Context;

import com.example.roomdaggermvvm.Api;
import com.example.roomdaggermvvm.ArticleRepository;
import com.example.roomdaggermvvm.BaseSchedulers;
import com.example.roomdaggermvvm.di.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = {ViewModelModule.class})
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    BaseSchedulers providesSchedulers() {return new SchedulerProvider();
    }

    @Provides
    @Singleton
    Api provideApi(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }

    @Provides
    @Singleton
    ArticleRepository provideArticleRepository(Context context, Api api, BaseSchedulers scheduler){
        return new ArticleRepository(context,api,scheduler);
    }
}
