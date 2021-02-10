package com.example.livedataviewmodel;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName ="text_table")
public class TextModel {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "text")
    private String text;

    public TextModel(@NonNull String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
