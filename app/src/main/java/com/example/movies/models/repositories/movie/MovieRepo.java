package com.example.movies.models.repositories.movie;


import androidx.lifecycle.LiveData;
import com.example.movies.local.MovieLocalSource;
import com.example.movies.models.pojos.MovieListPojo;
import com.example.movies.models.pojos.MoviePlanPojo;
import com.example.movies.models.pojos.MoviePojo;
import com.example.movies.views.movieDetails.view.IMovieDetailView;
import com.example.movies.remote.MovieConnection;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class MovieRepo implements MovieRepoInterface
{
    private MovieLocalSource movieLocalSource;
    private MovieConnection movieConnection;
    private static MovieRepo movieRepo;
    private MovieRepo(MovieLocalSource movieLocalSource,MovieConnection movieConnection)
    {
        this.movieLocalSource = movieLocalSource;
        this.movieConnection = movieConnection;
    }
    public static synchronized MovieRepo getInstance(MovieLocalSource movieLocalSource,MovieConnection movieConnection)
    {
        if (movieRepo == null)
            movieRepo = new MovieRepo(movieLocalSource,movieConnection);
        return movieRepo;
    }

    @Override
    public Single<MovieListPojo> getTrending() {
        return movieConnection.getTrending();
    }

    @Override
    public Single<MovieListPojo> getUpComing() {
        return movieConnection.getUpComing();
    }

    @Override
    public Single<MovieListPojo> getPopular() {
        return movieConnection.getPopular();
    }

    @Override
    public Single<MovieListPojo> getTopRated() {
        return movieConnection.getTopRated();
    }

    @Override
    public Single<MovieListPojo> getDiscover() {
        return movieConnection.getDiscover();
    }

    @Override
    public Single<MovieListPojo> getSearch(String search) {
        return movieConnection.getSearch(search);
    }

    @Override
    public void insertMovieToFav(MoviePojo moviePojo) {
        movieLocalSource.insertMovieToFav(moviePojo);
    }

    @Override
    public void delMovieFromFav(MoviePojo moviePojo) {
        movieLocalSource.delMovieFromFav(moviePojo);
    }

    @Override
    public LiveData<List<MoviePojo>> getAllMoviesFav() {
        return movieLocalSource.getAllMoviesFav();
    }

    @Override
    public void insertMovieToWatching(MoviePojo moviePojo) {
        movieLocalSource.insertMovieToFav(moviePojo);
    }

    @Override
    public void delMovieFromWatching(MoviePojo moviePojo) {
        movieLocalSource.delMovieFromFav(moviePojo);
    }

    @Override
    public LiveData<List<MoviePojo>> getAllMoviesWatching() {
        return movieLocalSource.getAllMoviesFav();
    }

    @Override
    public void getVideo(String title, IMovieDetailView iMovieDetailView) {
        movieConnection.getMovie(title,iMovieDetailView);
    }
}
