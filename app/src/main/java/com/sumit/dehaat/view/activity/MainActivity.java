package com.sumit.dehaat.view.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sumit.dehaat.R;
import com.sumit.dehaat.Utils;
import com.sumit.dehaat.model.Author;
import com.sumit.dehaat.model.AuthorDb;
import com.sumit.dehaat.model.AuthorResponse;
import com.sumit.dehaat.model.Book;
import com.sumit.dehaat.model.BookDb;
import com.sumit.dehaat.model.Category;
import com.sumit.dehaat.repo.database.AppDatabase;
import com.sumit.dehaat.view.adapter.AuthorAdapter;
import com.sumit.dehaat.view.adapter.BookAdapter;
import com.sumit.dehaat.viewmodel.MainViewModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private MainViewModel mainViewModel;
    private RecyclerView recyclerViewAuthor;
    private RecyclerView recyclerViewBook;
    private ArrayList<Book> bookArrayList = new ArrayList<>();
    private BookAdapter bookAdapter;
    private TextView tvBookGuide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        initViews();
        setProperties();


    }

    /**
     * This method find and init the requried views
     */
    public void initViews() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        recyclerViewAuthor = findViewById(R.id.recycler_view_author);
        recyclerViewBook = findViewById(R.id.recycler_view_book);
        tvBookGuide = findViewById(R.id.tv_book_guide);
    }

    /**
     * this method set properties like click listener and added another properties
     */
    public void setProperties() {

        // checking is author and book data stored in db or not, if not and display it and store it in db.
        if (!Utils.isDataStoredInDb(this)) {
            AuthorResponse authorResponse = mainViewModel.getAuthorData();
            showAuthors(authorResponse.getAuthorArrayList());
        } else {
            AppDatabase.getDatabase(this).getAuthorDao().getAuthorList().observe(this, new Observer<List<AuthorDb>>() {
                @Override
                public void onChanged(List<AuthorDb> authorDbs) {
                    if (authorDbs != null) {
                        ArrayList<Author> arrayList = new ArrayList<>();
                        for (int i = 0; i < authorDbs.size(); i++) {
                            AuthorDb authorDb = authorDbs.get(i);
                            arrayList.add(new Author(authorDb.getAuthor_name(), authorDb.getAuthor_bio()));
                        }
                        showAuthors(arrayList);
                    }
                }
            });
        }

        // checks landscape orientation and setup adapter
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            showBooks();
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * This method show list of authors and show book list once author selected on the basis of orientation.
     * @param authorArrayList
     */
    public void showAuthors(final ArrayList<Author> authorArrayList) {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        final AuthorAdapter authorAdapter = new AuthorAdapter(this, authorArrayList);
        recyclerViewAuthor.setAdapter(authorAdapter);
        recyclerViewAuthor.setLayoutManager(layoutManager);


        authorAdapter.getIntegerMutableLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (!Utils.isDataStoredInDb(MainActivity.this)) {

                    ArrayList<Book> books = authorArrayList.get(integer-1).getBookArrayList();
                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        bookArrayList.clear();
                        bookArrayList.addAll(books);
                        bookAdapter.notifyDataSetChanged();
                        tvBookGuide.setVisibility(View.GONE);

                    } else {
                        launchBooksActivity(books);

                    }
                } else {

                    AppDatabase.getDatabase(MainActivity.this).getBookDao().getBookList(integer).observe(MainActivity.this, new Observer<List<BookDb>>() {
                        @Override
                        public void onChanged(List<BookDb> bookDbs) {

                            ArrayList<Book> arrayList = new ArrayList<Book>();
                            for (int i = 0; i < bookDbs.size(); i++) {
                                BookDb bookDb = bookDbs.get(i);
                                arrayList.add(new Book(bookDb.getTitle(), bookDb.getDescription(), bookDb.getPublisher(), bookDb.getPublished_date(), bookDb.getPrice()));
                            }

                            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                                bookArrayList.clear();
                                bookArrayList.addAll(arrayList);
                                bookAdapter.notifyDataSetChanged();
                                tvBookGuide.setVisibility(View.GONE);
                            } else {
                                launchBooksActivity(arrayList);
                            }


                        }
                    });
                }


            }
        });

    }

    /**
     * This method set up adapter for books list
     */
    public void showBooks() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        bookAdapter = new BookAdapter(this, bookArrayList);
        recyclerViewBook.setAdapter(bookAdapter);
        recyclerViewBook.setLayoutManager(layoutManager);

    }

    /**
     * launch books list
     * @param bookArrayList
     */
    public void launchBooksActivity(ArrayList<Book> bookArrayList) {
        Intent intent = new Intent(this, BookActivity.class);
        intent.putParcelableArrayListExtra("books", bookArrayList);
        startActivity(intent);
    }


    /**
     * This method logout user and take it to login screen
     */
    public void logout() {
        Utils.updateLoginStatus(this, false);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
