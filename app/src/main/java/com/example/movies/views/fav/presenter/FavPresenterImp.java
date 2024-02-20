package com.example.movies.views.fav.presenter;

import com.example.movies.views.fav.view.FavView;
import com.example.movies.models.pojos.MoviePojo;
import com.example.movies.models.repositories.movie.MovieRepo;

public class FavPresenterImp implements FavPresenter{

    private FavView favView;

    private MovieRepo movieRepo;

    public FavPresenterImp(FavView favView, MovieRepo movieRepo){
        this.favView = favView;
        this.movieRepo = movieRepo;
    }
    @Override
    public void getMoviesFromDB() {
        favView.showData(movieRepo.getAllMoviesFav());
    }

    @Override
    public void removeFromFav(MoviePojo moviePojo) {
        movieRepo.delMovieFromFav(moviePojo);
        favView.showData(movieRepo.getAllMoviesFav());
    }

}
