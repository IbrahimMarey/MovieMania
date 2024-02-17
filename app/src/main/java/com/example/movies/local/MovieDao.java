package com.example.movies.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.movies.models.pojos.MoviePlanPojo;
import com.example.movies.models.pojos.MoviePojo;

import java.util.List;

@Dao
public interface MovieDao
{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMovieToFav(MoviePojo moviePojo);
    @Delete
    void delMovieFromFav(MoviePojo moviePojo);
    @Query("SELECT * FROM favmovie")
    LiveData<List<MoviePojo>> getAllMoviesFav();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMovieToWatching(MoviePlanPojo moviePojo);
    @Delete
    void delMovieFromWatching(MoviePlanPojo moviePojo);
    @Query("SELECT * FROM watching")
    LiveData<List<MoviePlanPojo>> getAllMoviesWatching();
}
