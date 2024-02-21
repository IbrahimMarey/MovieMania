package com.example.movies.views.movieDetails.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movies.R;
import com.example.movies.local.MovieLocalSource;
import com.example.movies.models.pojos.MoviePojo;
import com.example.movies.models.repositories.movie.MovieRepo;
import com.example.movies.views.movieDetails.presenter.IMovieDetailPresenter;
import com.example.movies.views.movieDetails.presenter.MovieDetailPresenter;
import com.example.movies.remote.MovieConnection;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;


public class MovieDetailsFragment extends Fragment implements IMovieDetailView {

MoviePojo moviePojo ;
YouTubePlayerView youTubePlayerView;
TextView overviewTextView ;
RatingBar ratingBar;
ImageView addToWatchBtn;

IMovieDetailPresenter presenter ;
boolean toWatch = false ;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      moviePojo = MovieDetailsFragmentArgs.fromBundle(getArguments()).getMoviePojo();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);
        youTubePlayerView = view.findViewById(R.id.youTubePlayerView);
        overviewTextView = view.findViewById(R.id.overviewTxtView);
        ratingBar= view.findViewById(R.id.ratingBar);
        addToWatchBtn = view.findViewById(R.id.addToWatchBtn);
        presenter = new MovieDetailPresenter(MovieRepo.getInstance(MovieLocalSource.getInstance(this.getContext()), MovieConnection.getInstance()),this);


        overviewTextView.setText(moviePojo.getOverview());
        ratingBar.setRating((float) moviePojo.getVote_average());
        ratingBar.setEnabled(false);


        addToWatchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(!toWatch){
                   toWatch = true ;
                   presenter.addToWatchList(moviePojo);
                   addToWatchBtn.setImageResource(R.drawable.ic_fav);

               }else{
                   toWatch = false;
                    presenter.removeFromWatchList(moviePojo);
                   addToWatchBtn.setImageResource(R.drawable.ic_not_fav);
               }
            }
        });
        getMovieFromLocal(String.valueOf(moviePojo.getId()));
        presenter.getVideo(moviePojo.getTitle(),this);
        return view;
    }


    private void getMovieFromLocal(String id) {
        presenter.getMovieFromFavById(id).observe(getViewLifecycleOwner(), new Observer<MoviePojo>() {
            @Override
            public void onChanged(MoviePojo moviePojo) {
                if (moviePojo !=null) {
                    toWatch = true;
                    addToWatchBtn.setImageResource(R.drawable.ic_fav);

                }else {
                    toWatch = false;
                }
            }
        });
    }
    public String getVideoLink(String link) {
        if (link != null && link.split("\\?v=").length > 1)
            return link.split("\\?v=")[1];
        else return "";
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(String code) {
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                if (code!=null)
                    youTubePlayer.cueVideo(code,0);
                else
                    youTubePlayer.cueVideo("5VIcLkr0NZk",0);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        youTubePlayerView.release();
    }
}