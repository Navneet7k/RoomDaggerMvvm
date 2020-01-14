package com.example.roomdaggermvvm.persistance;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "article_table")
public class ArticleModel {

    @PrimaryKey(autoGenerate = true)
    private int articleId;
    @ColumnInfo(name = "articletitle")
    private String articletitle;
    @ColumnInfo(name = "articleDesc")
    private String articleDesc;

    public ArticleModel(String articletitle, String articleDesc) {
        this.articletitle = articletitle;
        this.articleDesc = articleDesc;
    }

    public String getArticletitle() {
        return articletitle;
    }

    public String getArticleDesc() {
        return articleDesc;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public void setArticletitle(String articletitle) {
        this.articletitle = articletitle;
    }

    public void setArticleDesc(String articleDesc) {
        this.articleDesc = articleDesc;
    }
}
