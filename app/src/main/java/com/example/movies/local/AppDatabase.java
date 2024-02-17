package com.example.movies.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.movies.models.pojos.MoviePojo;

@Database(entities = {MoviePojo.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase
{
    private static AppDatabase instance;
    public abstract MovieDao movieDao();
    public static synchronized AppDatabase getInstance(Context context)
    {
        if (instance ==null)
            instance= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"movie.db").build();
        return instance;
    }
}
