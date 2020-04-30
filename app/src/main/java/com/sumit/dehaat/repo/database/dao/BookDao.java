package com.sumit.dehaat.repo.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.sumit.dehaat.model.AuthorDb;
import com.sumit.dehaat.model.BookDb;

import java.util.List;

@Dao
public interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBook(BookDb bookDb);


    @Query("select * from book where author_id=:authorId")
    LiveData<List<BookDb>> getBookList(int authorId);

}
