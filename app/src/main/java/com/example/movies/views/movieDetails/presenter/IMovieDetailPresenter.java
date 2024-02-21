package com.example.movies.views.movieDetails.presenter;

import androidx.lifecycle.LiveData;

import com.example.movies.models.pojos.MoviePojo;
import com.example.movies.views.movieDetails.view.IMovieDetailView;

public interface IMovieDetailPresenter {
    void addToWatchList(MoviePojo movie);
    void removeFromWatchList(MoviePojo movie);
    void getVideo(String title , IMovieDetailView iMovieDetailView);
    LiveData<MoviePojo> getMovieFromFavById(String id);
}
