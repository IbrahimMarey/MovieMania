package com.example.movies.remote;

import com.example.movies.models.pojos.MovieListPojo;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieConnectionInterface
{
    Single<MovieListPojo> getTrending();
    Single<MovieListPojo> getUpComing();
    Single<MovieListPojo> getPopular();
    Single<MovieListPojo> getTopRated();
    Single<MovieListPojo> getDiscover();
    Single<MovieListPojo> getSearch(String search);
}
