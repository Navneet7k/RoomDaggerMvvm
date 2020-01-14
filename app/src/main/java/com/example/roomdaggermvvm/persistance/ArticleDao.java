package com.example.roomdaggermvvm.persistance;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ArticleModel word);

    @Query("DELETE FROM article_table")
    void deleteAll();

    @Query("DELETE FROM article_table WHERE articleId = :id")
    void delete(int id);

    @Query("SELECT * from article_table ORDER BY articleId DESC")
    LiveData<List<ArticleModel>> getAlphabetizedWords();
}
