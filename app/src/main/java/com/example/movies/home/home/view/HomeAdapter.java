package com.example.movies.home.home.view;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movies.R;
import com.example.movies.models.pojos.MoviePojo;
import com.example.movies.remote.MovieConnection;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    List<MoviePojo> movies;
    Context context;
    OnClickListener _listener;


    public HomeAdapter(Context _context, List<MoviePojo> movies, OnClickListener _listener) {
        this.movies = movies;
        this.context = _context;
        this._listener=_listener;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.movie_card, parent, false);
        Log.i("TAG", "onCreateViewHolder:   Creating Home Movie");
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        Log.i("TAG", "onBindViewHolder: image is " + movies.get(position).getPoster_path());
        MoviePojo pojo = movies.get(position);
        Glide.with(context).load(MovieConnection.IMAGE_URL + movies.get(position).getPoster_path()).placeholder(R.drawable.poster).into(holder.getImageView());
        holder.constraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _listener.onClickListener(pojo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setMovies(List<MoviePojo> movies) {
        this.movies = movies;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageViewHomeImage;
        ConstraintLayout constraint;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            imageViewHomeImage = itemView.findViewById(R.id.imageViewHomeImage);
            constraint = itemView.findViewById(R.id.constraint);
        }

        public ImageView getImageView() {
            return imageViewHomeImage;
        }

        public View getView() {
            return view;
        }
    }
}
