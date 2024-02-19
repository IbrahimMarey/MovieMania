package com.example.movies.movieDetails.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movies.R;
import com.example.movies.models.pojos.MoviePojo;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;


public class MovieDetailsFragment extends Fragment {

MoviePojo moviePojo ;
YouTubePlayerView youTubePlayerView;



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
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
              //  String videoMealDetail = getVideoLink(moviePojo.ge());
            //    youTubePlayer.cueVideo(videoMealDetail, 0);
            }
        });
        return view;
    }
    public String getVideoLink(String link) {
        if (link != null && link.split("\\?v=").length > 1)
            return link.split("\\?v=")[1];
        else return "";
    }
}