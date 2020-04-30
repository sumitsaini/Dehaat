package com.sumit.dehaat.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AuthorResponse {
    @SerializedName("data")
    ArrayList<Author> authorArrayList;

    public ArrayList<Author> getAuthorArrayList() {
        return authorArrayList;
    }

    public void setAuthorArrayList(ArrayList<Author> authorArrayList) {
        this.authorArrayList = authorArrayList;
    }
}
