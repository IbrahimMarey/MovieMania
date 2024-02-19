package com.example.movies.home.search.view;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.movies.R;
import com.example.movies.home.home.view.HomeAdapter;
import com.example.movies.home.home.view.OnClickListener;
import com.example.movies.home.home.view.onWatchListListener;
import com.example.movies.home.search.presenter.SearchPresenter;
import com.example.movies.home.search.presenter.SearchPresenterInterface;
import com.example.movies.local.MovieLocalSource;
import com.example.movies.models.pojos.MovieListPojo;
import com.example.movies.models.pojos.MoviePojo;
import com.example.movies.models.repositories.movie.MovieRepo;
import com.example.movies.remote.MovieConnection;

import java.util.ArrayList;

public class SearchFragment extends Fragment implements SearchFragmentInterface, OnClickListener, onWatchListListener {

    SearchPresenterInterface searchPresenterInterface;
    ConstraintLayout constraintLayout;
    EditText searchEditText;
    RecyclerView recyclerView;
    HomeAdapter searchAdapter;

    public SearchFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_search, container, false);
        constraintLayout = view.findViewById(R.id.no_result_search_name);
        searchEditText = view.findViewById(R.id.search_edit_text);
        recyclerView = view.findViewById(R.id.search_recycler_view);
        constraintLayout.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        searchAdapter = new HomeAdapter(this.getContext(),new ArrayList<>(),this::onClickListener,this::onWatch);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(searchAdapter);
        searchPresenterInterface = new SearchPresenter(this, MovieRepo.getInstance(MovieLocalSource.getInstance(getActivity()), MovieConnection.getInstance()));
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchPresenterInterface.getSearchByMovieName(searchEditText.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return view;
    }

    @Override
    public void onSuccessSearch(MovieListPojo movieListPojo) {
        if (movieListPojo.getResults().isEmpty()){
            constraintLayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else{
            constraintLayout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            searchAdapter.setMovies(movieListPojo.getResults());
            searchAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onFailureSearch() {

    }

    @Override
    public void onClickListener(MoviePojo pojo) {
        //Navigate to Movie Details
    }

    @Override
    public void onWatch(MoviePojo pojo) {
        //Add to favorite
    }
}