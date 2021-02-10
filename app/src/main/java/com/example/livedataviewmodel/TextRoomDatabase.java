package com.example.livedataviewmodel;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.w3c.dom.Text;

@Database(entities = {TextModel.class}, version = 1, exportSchema = false)
public abstract class TextRoomDatabase extends RoomDatabase {
    public abstract TextModelDao textModelDao();
    private static TextRoomDatabase INSTANCE;

    static TextRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TextRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TextRoomDatabase.class, "text_database")
                            .fallbackToDestructiveMigration()
                            //.addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Database Callback
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    //First deletes all data and adds new words
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };


    /**
     * Populate the database in the background.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final TextModelDao mDao;

        PopulateDbAsync(TextRoomDatabase db) {
            mDao = db.textModelDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            mDao.deleteAll();

            TextModel text = new TextModel("first word!");
            mDao.insert(text);

            return null;
        }
    }

}
