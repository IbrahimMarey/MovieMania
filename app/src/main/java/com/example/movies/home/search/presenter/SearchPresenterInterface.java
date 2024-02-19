package com.example.movies.home.search.presenter;

import com.example.movies.models.pojos.MoviePojo;

public interface SearchPresenterInterface {
    void getSearchByMovieName(String movieName);
    public void addToWatchList(MoviePojo pojo);
}
