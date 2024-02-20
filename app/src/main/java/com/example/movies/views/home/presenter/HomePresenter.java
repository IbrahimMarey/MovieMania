package com.example.movies.views.home.presenter;

import com.example.movies.views.home.view.HomeView;
import com.example.movies.models.pojos.MovieListPojo;
import com.example.movies.models.pojos.MoviePojo;
import com.example.movies.models.repositories.movie.MovieRepoInterface;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenter implements HomePresenterInterface{
    HomeView _view;
    MovieRepoInterface _repo;

    private static HomePresenter instance =null;
    private HomePresenter(MovieRepoInterface _repo,HomeView _view){
        this._repo=_repo;
        this._view=_view;
    }
    public static HomePresenter getInstance(MovieRepoInterface _repo,HomeView _view){
        if(instance==null){
            instance = new HomePresenter(_repo,_view);
        }
        return instance;
    }
    @Override
    public void getTrendingMovies() {
        _repo.getTrending().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MovieListPojo>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull MovieListPojo movieListPojo) {
                        if(movieListPojo!=null){
                            _view.showTrendingMovies(movieListPojo);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    @Override
    public void getPopular() {
        _repo.getPopular().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MovieListPojo>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull MovieListPojo movieListPojo) {
                        if(movieListPojo!=null){
                            _view.showPopular(movieListPojo);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    @Override
    public void getTopRated() {
        _repo.getTopRated().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MovieListPojo>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull MovieListPojo movieListPojo) {
                        if(movieListPojo!=null){
                            _view.showTopRated(movieListPojo);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    @Override
    public void getDiscover() {
        _repo.getDiscover().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MovieListPojo>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull MovieListPojo movieListPojo) {
                        if(movieListPojo!=null){
                            _view.showDiscover(movieListPojo);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    @Override
    public void addToWatchList(MoviePojo pojo) {
        _repo.insertMovieToFav(pojo);
    }
}
