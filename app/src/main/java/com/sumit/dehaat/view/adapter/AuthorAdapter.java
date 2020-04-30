package com.sumit.dehaat.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.sumit.dehaat.R;
import com.sumit.dehaat.model.Author;
import com.sumit.dehaat.model.Book;

import java.util.ArrayList;

public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.AuthorViewHolder> {


    private ArrayList<Author> authorArrayList;
    private Context context;
    private LayoutInflater layoutInflater;
    private MutableLiveData<ArrayList<Book>> mutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Integer> integerMutableLiveData = new MutableLiveData<>();

    public AuthorAdapter(Context context, ArrayList<Author> authorArrayList) {
        this.context = context;
        this.authorArrayList = authorArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_author, null);
        AuthorViewHolder authorViewHolder = new AuthorViewHolder(view);
        return authorViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorViewHolder holder, int position) {
        Author author = authorArrayList.get(position);
        holder.tvAuthorName.setText(author.getAuthor_name());
        holder.tvAuthorBio.setText(author.getAuthor_bio());

        holder.tvReadMore.setTag(holder.tvAuthorBio);
        holder.tvReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tvTemp = (TextView) view.getTag();
                TextView tvRead = (TextView) view;
                System.out.println(tvTemp.getMaxLines());
                if (tvTemp.getMaxLines() == 3) {
                    tvTemp.setMaxLines(Integer.MAX_VALUE);
                    tvRead.setText("Read Less");
                } else {
                    tvTemp.setMaxLines(3);
                    tvRead.setText("Read More");
                }

            }
        });

        holder.cardParent.setTag(position);
        holder.cardParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (Integer) view.getTag();
                integerMutableLiveData.setValue(pos);
            }
        });

    }

    @Override
    public int getItemCount() {
        return authorArrayList.size();
    }

    class AuthorViewHolder extends RecyclerView.ViewHolder {

        private TextView tvAuthorName;
        private TextView tvAuthorBio;
        private TextView tvReadMore;
        private CardView cardParent;

        public AuthorViewHolder(@NonNull View itemView) {
            super(itemView);
            cardParent = itemView.findViewById(R.id.card_parent);
            tvAuthorName = itemView.findViewById(R.id.tv_author_name);
            tvAuthorBio = itemView.findViewById(R.id.tv_author_bio);
            tvReadMore = itemView.findViewById(R.id.tv_read_more);
        }
    }

    public MutableLiveData<Integer> getIntegerMutableLiveData() {
        return integerMutableLiveData;
    }

    public void setIntegerMutableLiveData(MutableLiveData<Integer> integerMutableLiveData) {
        this.integerMutableLiveData = integerMutableLiveData;
    }
}
