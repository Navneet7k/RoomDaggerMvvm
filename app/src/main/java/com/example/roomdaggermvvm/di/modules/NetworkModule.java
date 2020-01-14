package com.example.roomdaggermvvm.di.modules;

import com.example.roomdaggermvvm.ArticleRepository;

import java.net.CookieManager;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private static final long CONNECTION_TIMEOUT = 30000;

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor logging) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder().
                readTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS).
                connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS).
                writeTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS);
        return httpClient.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl("http://www.google.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideLogging() {
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }
}
