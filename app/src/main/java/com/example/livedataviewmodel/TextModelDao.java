package com.example.livedataviewmodel;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TextModelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TextModel text);

    //@Update
    @Query("UPDATE text_table SET text = :text")
    public void update(String text);

    // Simple query that does not take parameters and returns nothing.
    @Query("DELETE FROM text_table")
    void deleteAll();

    // Query with parameter that returns a specific word or words.
    @Query("SELECT * FROM text_table WHERE text LIKE :txt")
    public LiveData<List<TextModel>> getTheText(String txt);

    @Query("SELECT * FROM text_table LIMIT 1")
    public LiveData<TextModel> getFirstText();

}
