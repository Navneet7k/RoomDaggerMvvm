package com.example.roomdaggermvvm.view;

import androidx.lifecycle.LiveData;

import com.example.roomdaggermvvm.ArticleRepository;
import com.example.roomdaggermvvm.persistance.ArticleModel;

import java.util.List;

import javax.inject.Inject;

public class MainActivityVM extends BaseViewModel {

    final ArticleRepository mArticleRepository;
    private LiveData<List<ArticleModel>> mAllArticles;

    @Inject
    public MainActivityVM(ArticleRepository mArticleRepository) {
        super(mArticleRepository);
        this.mArticleRepository = mArticleRepository;
        mAllArticles = mArticleRepository.getAllArticles();
    }

    LiveData<List<ArticleModel>> getAllWords() { return mAllArticles; }
}
