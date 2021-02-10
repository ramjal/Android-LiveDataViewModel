package com.example.livedataviewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

public class TextModelRepository {
    private TextModelDao textModelDao;
    private LiveData<TextModel> textModelLive;

    public TextModelRepository(Application application) {
        TextRoomDatabase db = TextRoomDatabase.getDatabase(application);
        textModelDao = db.textModelDao();
        textModelLive = textModelDao.getFirstText();
    }

    public LiveData<TextModel> getFirstText() {
        return textModelLive;
    }

    public void insert(TextModel textModel) {
        new insertAsyncTask(textModelDao).execute(textModel);
    }

    public void update(String text) {
//        if (textModelLive.getValue() == null) {
//            insert(new TextModel(text));
//        } else {
//            new updateAsyncTask(textModelDao).execute(text);
//        }
        new updateAsyncTask(textModelDao).execute(text);
    }

    public void deleteAll() {
        new deleteAsyncTask(textModelDao).execute();
    }

    private static class insertAsyncTask extends AsyncTask<TextModel, Void, Void> {

        private TextModelDao mAsyncTaskDao;

        insertAsyncTask(TextModelDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final TextModel... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<String, Void, Void> {

        private TextModelDao mAsyncTaskDao;

        updateAsyncTask(TextModelDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Void, Void, Void> {

        private TextModelDao mAsyncTaskDao;

        deleteAsyncTask(TextModelDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }
}
