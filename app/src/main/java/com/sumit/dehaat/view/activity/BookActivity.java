package com.sumit.dehaat.view.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sumit.dehaat.R;
import com.sumit.dehaat.model.Book;
import com.sumit.dehaat.view.adapter.BookAdapter;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ArrayList<Book> bookArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        bookArrayList = getIntent().getParcelableArrayListExtra("books");

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

        recyclerView = findViewById(R.id.recycler_view_book);
    }

    /**
     * this method set properties like click listener and added another properties
     */
    public void setProperties() {

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        BookAdapter authorAdapter = new BookAdapter(this, bookArrayList);
        recyclerView.setAdapter(authorAdapter);
        recyclerView.setLayoutManager(layoutManager);

    }


}
