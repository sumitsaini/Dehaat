package com.sumit.dehaat.repo.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.sumit.dehaat.model.AuthorDb;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface AuthorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAuthor(AuthorDb author);

    @Query("select * from author")
    LiveData<List<AuthorDb>> getAuthorList();


}
