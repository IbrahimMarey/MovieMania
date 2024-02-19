package com.example.movies.movieDetails.presenter;

import com.example.movies.models.pojos.MoviePlanPojo;
import com.example.movies.models.pojos.MoviePojo;
import com.example.movies.models.repositories.movie.MovieRepoInterface;
import com.example.movies.movieDetails.view.IMovieDetailView;

public class MovieDetailPresenter implements IMovieDetailPresenter {
    MovieRepoInterface repo ;
    IMovieDetailView view;
    public MovieDetailPresenter(MovieRepoInterface repo , IMovieDetailView view) {
        this.repo = repo;
        this.view = view;
    }

    @Override
    public void addToWatchList(MoviePojo movie) {


        repo.insertMovieToWatching(pojoToPlanPojo(movie));
        view.showToast("Added to watchList");
    }

    @Override
    public void removeFromWatchList(MoviePojo movie) {
        repo.delMovieFromWatching(pojoToPlanPojo(movie));
        view.showToast("Removed from watchList");
    }
    private MoviePlanPojo pojoToPlanPojo(MoviePojo pojo){
        MoviePlanPojo planPojo = new MoviePlanPojo();
        planPojo.setMovieID(Long.toString(pojo.getId()));
        planPojo.setId(pojo.getId());
        planPojo.setPoster_path(pojo.getPoster_path());
        planPojo.setVote_average(pojo.getVote_average());
        planPojo.setTitle(pojo.getTitle());
        planPojo.setOverview(pojo.getOverview());
        return  planPojo;
    }
}
