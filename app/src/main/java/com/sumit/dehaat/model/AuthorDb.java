package com.sumit.dehaat.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "author")
public class AuthorDb {

    @PrimaryKey()
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "name")
    private String author_name;
    @ColumnInfo(name = "bio")
    private String author_bio;


    public AuthorDb(int id, String author_name, String author_bio) {
        this.id = id;
        this.author_name = author_name;
        this.author_bio = author_bio;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getAuthor_bio() {
        return author_bio;
    }

    public void setAuthor_bio(String author_bio) {
        this.author_bio = author_bio;
    }
}
