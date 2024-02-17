package com.example.movies.local;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.movies.models.pojos.MoviePlanPojo;
import com.example.movies.models.pojos.MoviePojo;

import java.util.List;

public class MovieLocalSource implements LocalSourceInterface
{
    private MovieDao movieDao;
    private static  MovieLocalSource movieLocalSource;

    private MovieLocalSource(Context context)
    {
        AppDatabase appDatabase = AppDatabase.getInstance(context.getApplicationContext());
        movieDao = appDatabase.movieDao();
    }
    public static synchronized MovieLocalSource getInstance(Context context)
    {
        if (movieLocalSource==null)
            movieLocalSource = new MovieLocalSource(context);
        return movieLocalSource;
    }

    @Override
    public void insertMovieToFav(MoviePojo moviePojo) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                movieDao.insertMovieToFav(moviePojo);
            }
        }).start();
    }

    @Override
    public void delMovieFromFav(MoviePojo moviePojo) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                movieDao.delMovieFromFav(moviePojo);
            }
        }).start();
    }

    @Override
    public LiveData<List<MoviePojo>> getAllMoviesFav() {
        return movieDao.getAllMoviesFav();
    }

    @Override
    public void insertMovieToWatching(MoviePlanPojo moviePojo) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                movieDao.insertMovieToWatching(moviePojo);
            }
        }).start();
    }

    @Override
    public void delMovieFromWatching(MoviePlanPojo moviePojo) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                movieDao.delMovieFromWatching(moviePojo);
            }
        }).start();
    }

    @Override
    public LiveData<List<MoviePlanPojo>> getAllMoviesWatching() {
        return movieDao.getAllMoviesWatching();
    }
}
