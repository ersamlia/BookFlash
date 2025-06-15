package com.example.latihan_praktikum_6.Data.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.latihan_praktikum_6.Data.Entity.Book;

import java.util.List;

@Dao
public interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBooks(List<Book> books);

    @Query("SELECT * FROM book")
    LiveData<List<Book>> getAllBooks();
}

