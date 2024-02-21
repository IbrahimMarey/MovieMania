package com.example.movies.local;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.movies.models.pojos.MoviePlanPojo;
import com.example.movies.models.pojos.MoviePojo;

import java.util.List;

public interface LocalSourceInterface
{
    void insertMovieToFav(MoviePojo moviePojo);
    void delMovieFromFav(MoviePojo moviePojo);
    LiveData<List<MoviePojo>> getAllMoviesFav();

    void insertMovieToWatching(MoviePlanPojo moviePojo);
    void delMovieFromWatching(MoviePlanPojo moviePojo);
    LiveData<List<MoviePlanPojo>> getAllMoviesWatching();

    LiveData<MoviePojo> getMovieFromFavById(String id);
}
