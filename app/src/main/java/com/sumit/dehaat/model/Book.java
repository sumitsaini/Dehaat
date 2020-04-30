package com.sumit.dehaat.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Book implements Parcelable {

    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("publisher")
    private String publisher;
    @SerializedName("published_date")
    private String published_date;
    @SerializedName("price")
    private String price;


    public Book(String title, String description, String publisher, String published_date, String price) {
        this.title = title;
        this.description = description;
        this.publisher = publisher;
        this.published_date = published_date;
        this.price = price;

    }


    public Book(Parcel parcel) {
        title = parcel.readString();
        description = parcel.readString();
        publisher = parcel.readString();
        published_date = parcel.readString();
        price = parcel.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(publisher);
        parcel.writeString(published_date);
        parcel.writeString(price);


    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
