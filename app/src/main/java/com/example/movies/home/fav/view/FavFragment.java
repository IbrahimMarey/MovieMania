package com.example.movies.home.fav.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.movies.R;
import com.example.movies.home.fav.presenter.FavPresenterImp;
import com.example.movies.local.MovieLocalSource;
import com.example.movies.models.pojos.MoviePojo;
import com.example.movies.models.repositories.movie.MovieRepo;
import com.example.movies.remote.MovieConnection;

import java.util.ArrayList;
import java.util.List;


public class FavFragment extends Fragment implements FavView , OnClickFavListener{

    private View view;

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    FavPresenterImp presenter;

    FavAdapter favAdapter;
    public FavFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.fav_recyclerview);

        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        favAdapter = new FavAdapter(getContext(), new ArrayList<>(), this);
        presenter =new FavPresenterImp(this,
                MovieRepo.getInstance(
                        MovieLocalSource.getInstance(getContext()),
                        MovieConnection.getInstance()));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(favAdapter);
        presenter.getMoviesFromDB();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav, container, false);
    }

    @Override
    public void showData(LiveData<List<MoviePojo>> allMovies) {
        allMovies.observe(getViewLifecycleOwner(), new Observer<List<MoviePojo>>() {
            @Override
            public void onChanged(List<MoviePojo> movieItem) {
                recyclerView.setVisibility(View.VISIBLE);
                favAdapter.setList(movieItem);
                favAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void showErrorMsg(String error) {
        Toast.makeText(getContext(),  error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteMovie(MoviePojo moviePojo) {
        presenter.removeFromFav(moviePojo);
        favAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(MoviePojo movieItem) {
        deleteMovie(movieItem);
    }

}