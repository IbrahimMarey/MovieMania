package com.example.movies.movieDetails.presenter;

import com.example.movies.models.pojos.MoviePojo;

public interface IMovieDetailPresenter {
    void addToWatchList(MoviePojo movie);
    void removeFromWatchList(MoviePojo movie);
}
