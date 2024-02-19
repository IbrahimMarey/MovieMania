package com.example.movies.home.home.view;

import com.example.movies.models.pojos.MovieListPojo;

public interface HomeView {
    void showTrendingMovies(MovieListPojo movieListPojo);
    void showPopular(MovieListPojo movieListPojo);
    void showTopRated(MovieListPojo movieListPojo);
    void showDiscover(MovieListPojo movieListPojo);
}
