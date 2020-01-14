package com.example.roomdaggermvvm;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.roomdaggermvvm.persistance.ArticleDao;
import com.example.roomdaggermvvm.persistance.ArticleModel;
import com.example.roomdaggermvvm.persistance.ArticleRoomDatabase;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ArticleRepository {
    private ArticleDao mArticleDao;
    private LiveData<List<ArticleModel>> mAllArticles;
    private final Api api;
    private final BaseSchedulers scheduler;

    @Inject
    public ArticleRepository(Context application,Api api,BaseSchedulers scheduler) {
        ArticleRoomDatabase db = ArticleRoomDatabase.getDatabase(application);
        mArticleDao = db.articleDao();
        mAllArticles = mArticleDao.getAlphabetizedWords();
        this.api=api;
        this.scheduler=scheduler;
    }

    private <T> void performRequest(Observable<T> observable, final ResponseListener<T> responseListener) {
        observable.subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        responseListener.onStart();
                    }

                    @SuppressWarnings("unchecked")
                    @Override
                    public void onNext(T t) {
                        //Decrypt
                        responseListener.onResponse(new ApiResponse(ResponseStatus.SUCCESS, t, null));
                    }

                    @SuppressWarnings("unchecked")
                    @Override
                    public void onError(Throwable e) {
                        responseListener.onResponse(new ApiResponse(ResponseStatus.ERROR, null, e));
                    }

                    @Override
                    public void onComplete() {
                        responseListener.onFinish();
                    }
                });


    }

    public LiveData<List<ArticleModel>> getAllArticles() {
        return mAllArticles;
    }

    public void insert(ArticleModel articleModel) {
        ArticleRoomDatabase.databaseWriteExecutor.execute(() -> {
            mArticleDao.insert(articleModel);
        });
    }
}
