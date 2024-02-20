package com.example.movies.views.home.view;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

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
    onWatchListListener _watcher;


    public HomeAdapter(Context _context, List<MoviePojo> movies, OnClickListener _listener,onWatchListListener _watcher) {
        this.movies = movies;
        this.context = _context;
        this._listener = _listener;
        this._watcher=_watcher;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.movie_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        MoviePojo pojo = movies.get(position);
        Glide.with(context)
                .load(MovieConnection.IMAGE_URL + movies.get(position).getPoster_path())
                .placeholder(R.drawable.video)
                .into(holder.getImageView());
        holder.constraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _listener.onClickListener(pojo);
            }
        });
        holder.constraint.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, view);
                popupMenu.getMenuInflater().inflate(R.menu.watchlist, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.addToWatchlist) {
                            _watcher.onWatch(pojo);
                            Toast.makeText(context, "Added to Watchlist", Toast.LENGTH_SHORT).show();
                            return true;
                        }
                        return false;
                    }
                });
                popupMenu.show();
                return true;
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
