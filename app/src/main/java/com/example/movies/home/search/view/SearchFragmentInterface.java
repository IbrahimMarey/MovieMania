package com.example.movies.home.search.view;

import com.example.movies.models.pojos.MovieListPojo;

public interface SearchFragmentInterface {
    void onSuccessSearch(MovieListPojo movieListPojo);
    void onFailureSearch();
}
