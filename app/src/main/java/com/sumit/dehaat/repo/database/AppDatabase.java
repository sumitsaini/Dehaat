package com.sumit.dehaat.repo.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.sumit.dehaat.model.AuthorDb;
import com.sumit.dehaat.model.BookDb;
import com.sumit.dehaat.model.Category;
import com.sumit.dehaat.repo.database.dao.AuthorDao;
import com.sumit.dehaat.repo.database.dao.BookDao;

@Database(entities = {AuthorDb.class, BookDb.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "dehaat.db").build();

                    System.out.println("db created");
                }
            }
        }
        return INSTANCE;
    }

    public abstract AuthorDao getAuthorDao();

    public abstract BookDao getBookDao();

}
