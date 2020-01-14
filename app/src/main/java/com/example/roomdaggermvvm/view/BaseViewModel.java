
package com.example.roomdaggermvvm.view;

import androidx.lifecycle.ViewModel;

import com.example.roomdaggermvvm.ArticleRepository;

public abstract class BaseViewModel extends ViewModel {

    private final ArticleRepository mArticleRepository;


    public BaseViewModel(ArticleRepository mArticleRepository) {
        this.mArticleRepository = mArticleRepository;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public ArticleRepository getArticleRepository() {
        return mArticleRepository;
    }
}
