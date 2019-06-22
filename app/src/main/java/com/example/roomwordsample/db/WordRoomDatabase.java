package com.example.roomwordsample.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Word.class}, version = 1)
public abstract class WordRoomDatabase extends RoomDatabase {

    public abstract WordDao wordDao();

    private static volatile WordRoomDatabase DATABASE;

    public static WordRoomDatabase getDatabase(final Context context) {
        if (DATABASE == null) {
            synchronized (WordRoomDatabase.class) {
                if (DATABASE == null) {
                    DATABASE = Room.databaseBuilder(context.getApplicationContext(), WordRoomDatabase.class, "word_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return DATABASE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen (SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(DATABASE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final WordDao mDao;

        public PopulateDbAsync(WordRoomDatabase db) {
            mDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(Void... params) {
            mDao.deleteAll();
            Word word = new Word("First");
            mDao.insert(word);
            word = new Word("Second");
            mDao.insert(word);
            word = new Word("Third");
            mDao.insert(word);
            return null;
        }
    }

}
