package com.example.movies.home.home.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.movies.R;
import com.example.movies.home.home.presenter.HomePresenter;
import com.example.movies.home.home.presenter.HomePresenterInterface;
import com.example.movies.local.MovieLocalSource;
import com.example.movies.models.pojos.MovieListPojo;
import com.example.movies.models.pojos.MoviePojo;
import com.example.movies.models.repositories.movie.MovieRepo;
import com.example.movies.remote.MovieConnection;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements HomeView ,OnClickListener{
    HomePresenterInterface homePresenter;
    HomeAdapter trendingMovies,popularMovies,topRatedMovies,discoverMovies;
    RecyclerView recyclerView,recyclerViewPopular,recyclerViewTopRated,recyclerViewDiscover;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView= view.findViewById(R.id.recyclerView);
        recyclerViewPopular = view.findViewById(R.id.recyclerViewPopular);
        recyclerViewTopRated =view.findViewById(R.id.recyclerViewTopRated);
        recyclerViewDiscover=view.findViewById(R.id.recyclerViewDiscover);
        homePresenter = HomePresenter.getInstance(MovieRepo.getInstance(MovieLocalSource.getInstance(this.getContext()), MovieConnection.getInstance()),this);
        trendingMovies = new HomeAdapter(this.getContext(), new ArrayList<>(),this);
        popularMovies = new HomeAdapter(this.getContext(),new ArrayList<>(),this);
        topRatedMovies = new HomeAdapter(this.getContext(),new ArrayList<>(),this);
        discoverMovies = new HomeAdapter(this.getContext(),new ArrayList<>(),this);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        LinearLayoutManager popularManager = new LinearLayoutManager(getContext());
        LinearLayoutManager topRatedManager = new LinearLayoutManager(getContext());
        LinearLayoutManager discoverManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        popularManager.setOrientation(RecyclerView.HORIZONTAL);
        topRatedManager.setOrientation(RecyclerView.HORIZONTAL);
        discoverManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewPopular.setLayoutManager(popularManager);
        recyclerViewTopRated.setLayoutManager(topRatedManager);
        recyclerViewDiscover.setLayoutManager(discoverManager);

        recyclerView.setAdapter(trendingMovies);
        recyclerViewPopular.setAdapter(popularMovies);
        recyclerViewTopRated.setAdapter(topRatedMovies);
        recyclerViewDiscover.setAdapter(discoverMovies);
        homePresenter.getTrendingMovies();
        homePresenter.getDiscover();
        homePresenter.getPopular();
        homePresenter.getTopRated();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void showTrendingMovies(MovieListPojo movieListPojo) {
        trendingMovies.setMovies(movieListPojo.getResults());
        trendingMovies.notifyDataSetChanged();
    }

    @Override
    public void showPopular(MovieListPojo movieListPojo) {
        popularMovies.setMovies(movieListPojo.getResults());
        popularMovies.notifyDataSetChanged();
        Log.i("TAG", "showPopular: "+movieListPojo.getResults().size());
    }

    @Override
    public void showTopRated(MovieListPojo movieListPojo) {
        topRatedMovies.setMovies(movieListPojo.getResults());
        topRatedMovies.notifyDataSetChanged();
    }

    @Override
    public void showDiscover(MovieListPojo movieListPojo) {
        discoverMovies.setMovies(movieListPojo.getResults());
        discoverMovies.notifyDataSetChanged();
    }

    @Override
    public void onClickListener(MoviePojo pojo) {
        Toast.makeText(getContext(), "Here to go to details Movie Name is :" +pojo.getTitle(), Toast.LENGTH_SHORT).show();
    }
}