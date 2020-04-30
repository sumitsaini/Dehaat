package com.sumit.dehaat.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.sumit.dehaat.R;
import com.sumit.dehaat.model.Author;
import com.sumit.dehaat.model.Book;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BooksViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Book> bookArrayList;

    public BookAdapter(Context context, ArrayList<Book> bookArrayList) {
        this.context = context;
        this.bookArrayList = bookArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public BooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_book, null);
        BooksViewHolder booksViewHolder = new BooksViewHolder(view);
        return booksViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BooksViewHolder holder, int position) {

        Book book = bookArrayList.get(position);

        holder.tvTitle.setText(book.getTitle());
        holder.tvPublisher.setText(book.getPublisher());
        holder.tvDate.setText(book.getPublished_date());
        holder.tvPrice.setText(context.getString(R.string.txt_ruppes)+book.getPrice());
        holder.tvDesc.setText(book.getDescription());

    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }

    class BooksViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvPublisher;
        private TextView tvDate;
        private TextView tvPrice;
        private TextView tvDesc;

        public BooksViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvPublisher = itemView.findViewById(R.id.tv_publisher);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvPrice = itemView.findViewById(R.id.tv_amount);
            tvDesc = itemView.findViewById(R.id.tv_desc);

        }
    }
}
