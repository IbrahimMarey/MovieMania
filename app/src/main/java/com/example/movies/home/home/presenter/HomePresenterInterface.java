package com.example.movies.home.home.presenter;

import com.example.movies.models.pojos.MoviePojo;

public interface HomePresenterInterface {
    void getTrendingMovies();
    void getPopular();
    void getTopRated();
    void getDiscover();
    void addToWatchList(MoviePojo pojo);
}
