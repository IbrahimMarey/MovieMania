package com.example.movies.remote;

import com.example.movies.models.pojos.MovieListPojo;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieConnection implements MovieConnectionInterface
{
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String API_KEY="a4e12a16d1df010db28f0f570554e512";
    public static final String YoutubeAPI_KEY = "AIzaSyCdAVi5z_C1WStIlghm-4GiaKJWpNguOAg";
    public static final String YoutubeBaseURL = "https://youtube.googleapis.com/youtube/v3/search?";
    private static MovieConnection movieConnection;
    private MovieServices movieServices;
    private MovieConnection()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        movieServices = retrofit.create(MovieServices.class);
    }

    public static synchronized MovieConnection getInstance()
    {
        if (movieConnection == null)
            movieConnection= new MovieConnection();
        return movieConnection;
    }

    @Override
    public Single<MovieListPojo> getTrending()
    {
        return movieServices.getTrending(API_KEY);
    }

    @Override
    public Single<MovieListPojo> getUpComing() {
        return movieServices.getUpComing(API_KEY);
    }

    @Override
    public Single<MovieListPojo> getPopular() {
        return movieServices.getPopular(API_KEY);
    }

    @Override
    public Single<MovieListPojo> getTopRated() {
        return movieServices.getTopRated(API_KEY);
    }

    @Override
    public Single<MovieListPojo> getDiscover() {
        return movieServices.getDiscover(API_KEY);
    }

    @Override
    public Single<MovieListPojo> getSearch(String search) {
        return movieServices.getSearch(API_KEY,search);
    }
}
