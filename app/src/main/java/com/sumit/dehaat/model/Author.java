package com.sumit.dehaat.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class Author {

    @SerializedName("author_name")
    private String author_name;
    @SerializedName("author_bio")
    private String author_bio;
    @SerializedName("books")
    private ArrayList<Book> bookArrayList;

    public Author(String author_name, String author_bio) {
        this.author_name = author_name;
        this.author_bio = author_bio;
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

    public ArrayList<Book> getBookArrayList() {
        return bookArrayList;
    }

    public void setBookArrayList(ArrayList<Book> bookArrayList) {
        this.bookArrayList = bookArrayList;
    }

}
