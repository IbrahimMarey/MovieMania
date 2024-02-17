package com.example.movies.home.search.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.movies.R;
import com.example.movies.home.search.presenter.SearchPresenter;
import com.example.movies.home.search.presenter.SearchPresenterInterface;
import com.example.movies.local.MovieLocalSource;
import com.example.movies.models.pojos.MovieListPojo;
import com.example.movies.models.repositories.movie.MovieRepo;
import com.example.movies.remote.MovieConnection;

public class SearchFragment extends Fragment implements SearchFragmentInterface{

    SearchPresenterInterface searchPresenterInterface;
    EditText searchEditText;

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
        searchEditText = view.findViewById(R.id.search_edit_text);
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
        Log.i("TAG", "onSuccessSearch: "+movieListPojo.getResults().get(0).getTitle());
    }

    @Override
    public void onFailureSearch() {

    }
}