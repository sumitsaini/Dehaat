package com.sumit.dehaat.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sumit.dehaat.R;
import com.sumit.dehaat.Utils;
import com.sumit.dehaat.model.Author;
import com.sumit.dehaat.model.AuthorDb;
import com.sumit.dehaat.model.AuthorResponse;
import com.sumit.dehaat.model.Book;
import com.sumit.dehaat.model.BookDb;
import com.sumit.dehaat.repo.database.AppDatabase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private Application application;
    private AppDatabase appDatabase;

    public MainViewModel(Application application) {
        super(application);
        this.application = application;

        appDatabase = AppDatabase.getDatabase(application);

    }

    public AuthorResponse getAuthorData() {
        InputStream inputStream = application.getResources().openRawResource(R.raw.sample_data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            byte buf[] = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new GsonBuilder().setLenient().create();
        AuthorResponse authorResponse = gson.fromJson(outputStream.toString(), AuthorResponse.class);

        saveData(authorResponse);

        return authorResponse;


    }

    public void saveData(AuthorResponse authorResponse) {
        ArrayList<Author> authorArrayList = authorResponse.getAuthorArrayList();
        for (int i = 0; i < authorArrayList.size(); i++) {
            Author author = authorArrayList.get(i);

            AuthorDb authorDb = new AuthorDb(i, author.getAuthor_name(), author.getAuthor_bio());
            new AddAsyncTaskAuthor(appDatabase).execute(authorDb);
            ArrayList<Book> bookArrayList = author.getBookArrayList();
            for (int j = 0; j < bookArrayList.size(); j++) {
                Book book = bookArrayList.get(j);
                BookDb bookDb = new BookDb(book.getTitle(), book.getDescription(), book.getPublisher(), book.getPublished_date(), book.getPrice(), i);
                new AddAsyncTaskRoom(appDatabase).execute(bookDb);
            }


        }
        Utils.updateDataStoredInDb(application, true);

    }

    private class AddAsyncTaskAuthor extends AsyncTask<AuthorDb, Void, Void> {

        private AppDatabase db;

        AddAsyncTaskAuthor(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final AuthorDb... params) {
            db.getAuthorDao().insertAuthor(params[0]);
            System.out.println("author inserted");
            return null;
        }

    }

    private class AddAsyncTaskRoom extends AsyncTask<BookDb, Void, Void> {

        private AppDatabase db;

        AddAsyncTaskRoom(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final BookDb... params) {
            db.getBookDao().insertBook(params[0]);
            System.out.println("book inserted");
            return null;
        }

    }


}
