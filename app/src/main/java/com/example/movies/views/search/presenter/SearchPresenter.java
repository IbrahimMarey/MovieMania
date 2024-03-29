package com.example.movies.views.search.presenter;

import com.example.movies.views.search.view.SearchFragmentInterface;
import com.example.movies.models.pojos.MovieListPojo;
import com.example.movies.models.pojos.MoviePojo;
import com.example.movies.models.repositories.movie.MovieRepo;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenter implements SearchPresenterInterface{
    MovieRepo movieRepo;
    SearchFragmentInterface searchFragmentInterface;

    public SearchPresenter(SearchFragmentInterface searchFragmentInterface,MovieRepo movieRepo) {
        this.searchFragmentInterface = searchFragmentInterface;
        this.movieRepo = movieRepo;
    }



    @Override
    public void getSearchByMovieName(String movieName) {
        movieRepo.getSearch(movieName).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<MovieListPojo>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull MovieListPojo movieListPojo) {
                searchFragmentInterface.onSuccessSearch(movieListPojo);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });

    }
    public void addToWatchList(MoviePojo pojo) {
        movieRepo.insertMovieToFav(pojo);
    }

}
