package com.example.movies.models.repositories.movie;

import androidx.lifecycle.LiveData;

import com.example.movies.models.pojos.MovieListPojo;
import com.example.movies.models.pojos.MoviePlanPojo;
import com.example.movies.models.pojos.MoviePojo;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface MovieRepoInterface
{
    Single<MovieListPojo> getTrending();
    Single<MovieListPojo> getUpComing();
    Single<MovieListPojo> getPopular();
    Single<MovieListPojo> getTopRated();
    Single<MovieListPojo> getDiscover();
    Single<MovieListPojo> getSearch(String search);
    void insertMovieToFav(MoviePojo moviePojo);
    void delMovieFromFav(MoviePojo moviePojo);
    LiveData<List<MoviePojo>> getAllMoviesFav();

    void insertMovieToWatching(MoviePlanPojo moviePojo);
    void delMovieFromWatching(MoviePlanPojo moviePojo);
    LiveData<List<MoviePlanPojo>> getAllMoviesWatching();
}
