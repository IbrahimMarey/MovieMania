package com.example.movies.remote;

import com.example.movies.models.pojos.MovieListPojo;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieServices
{
    @GET("trending/movie/day")
    public Single<MovieListPojo> getTrending(@Query("api_key") String apiKey);
    @GET("movie/upcoming")
    public Single<MovieListPojo> getUpComing(@Query("api_key") String apiKey);
    @GET("movie/popular")
    public Single<MovieListPojo> getPopular(@Query("api_key") String apiKey);
    @GET("movie/top_rated")
    public Single<MovieListPojo> getTopRated(@Query("api_key") String apiKey);
    @GET("discover/movie")
    public Single<MovieListPojo> getDiscover(@Query("api_key") String apiKey);
    @GET("search/movie")
    public Single<MovieListPojo> getSearch(@Query("api_key") String apiKey,@Query("query") String search);
}
