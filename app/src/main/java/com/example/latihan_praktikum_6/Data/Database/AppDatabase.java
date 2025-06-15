package com.example.latihan_praktikum_6.Data.Database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.latihan_praktikum_6.Data.Entity.Book;

@Database(entities = {Book.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract BookDao bookDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "disney_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}

