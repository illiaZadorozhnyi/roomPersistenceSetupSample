package com.example.roomwordsample.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomwordsample.R;
import com.example.roomwordsample.db.Word;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {


    class WordViewHolder extends RecyclerView.ViewHolder {

        private final TextView wordItemView;

        private WordViewHolder(@NonNull View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.itemView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Word> mWords; // cached copy of words

    public WordListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        if (mWords != null) {
            Word current = mWords.get(position);
            holder.wordItemView.setText(current.getWord());
        } else {
//  this covers the case when data is not displayed yet
            holder.wordItemView.setText("No Word");
        }
    }

    void setWords(List<Word> words) {
        mWords = words;
        notifyDataSetChanged();
    }

    //  getItemCount() is called many times, and when it is first called, mWords has not yet been updated (means, initially,
//  it's null, and we can not return null)
    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }
}
