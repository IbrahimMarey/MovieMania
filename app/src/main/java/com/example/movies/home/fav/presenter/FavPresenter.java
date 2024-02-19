package com.example.movies.home.fav.presenter;

import com.example.movies.models.pojos.MoviePojo;

public interface FavPresenter {
    public void getMoviesFromDB();
    public void removeFromFav(MoviePojo moviePojo);
}
