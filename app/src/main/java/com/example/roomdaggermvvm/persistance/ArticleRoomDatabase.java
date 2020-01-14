package com.example.roomdaggermvvm.persistance;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {ArticleModel.class}, version = 1, exportSchema = false)
public abstract class ArticleRoomDatabase extends RoomDatabase {
    public abstract ArticleDao articleDao();

    private static volatile ArticleRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ArticleRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ArticleRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ArticleRoomDatabase.class, "article_database")
//                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            databaseWriteExecutor.execute(() -> {

                ArticleDao dao = INSTANCE.articleDao();
                dao.deleteAll();

                ArticleModel articleModel = new ArticleModel("This is a sample article title","This is a sample article description This is a sample article description This is a sample article description This is a sample article descriptionThis is a sample article descriptionThis is a sample article description This is a sample article description This is a sample article description");
                dao.insert(articleModel);
            });
        }
    };
}
