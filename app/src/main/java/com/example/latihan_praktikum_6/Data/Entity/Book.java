package com.example.latihan_praktikum_6.Data.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "book")
public class Book {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String cover;
    private String summary;

    // Getters dan Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCover() { return cover; }
    public void setCover(String cover) { this.cover = cover; }

    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
}
