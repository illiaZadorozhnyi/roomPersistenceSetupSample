package com.example.roomwordsample.ui;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomwordsample.db.Word;
import com.example.roomwordsample.repository.WordRepository;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository mRepository;
    private LiveData<List<Word>> mAllWords;


    public WordViewModel(Application application) {
        super(application);

        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllAlphabetizedWords();
    }

    public LiveData<List<Word>> getmAllWords() {
        return mAllWords;
    }

    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        mRepository.insert(word);
    }
}