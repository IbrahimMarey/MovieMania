package com.example.movies.views.fav.view;

import androidx.lifecycle.LiveData;

import com.example.movies.models.pojos.MoviePojo;

import java.util.List;

public interface FavView {
    public void showData(LiveData<List<MoviePojo>> allMovies);
    public void showErrorMsg(String error);
    public void deleteMovie(MoviePojo moviePojo);
}
