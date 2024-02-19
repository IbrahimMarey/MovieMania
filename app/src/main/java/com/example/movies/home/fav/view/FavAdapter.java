package com.example.movies.home.fav.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movies.R;
import com.example.movies.models.pojos.MoviePojo;
import com.example.movies.remote.MovieConnection;

import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.FavViewHolder> {

    private Context context;
    private List<MoviePojo> moviePojoList;
    private OnClickFavListener listener;
    private OnDetailsListener onDetailsListener;

    public FavAdapter(Context context, List<MoviePojo> moviePojoList, OnClickFavListener listener,OnDetailsListener onDetailsListener) {
        this.context = context;
        this.moviePojoList = moviePojoList;
        this.listener = listener;
        this.onDetailsListener=onDetailsListener;

    }

    @NonNull
    @Override
    public FavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fav_item, parent, false);
        return new FavViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavViewHolder holder, int position) {
        MoviePojo pojo = moviePojoList.get(position);
        Glide.with(context)
                .load(MovieConnection.IMAGE_URL + moviePojoList.get(position).getPoster_path())
                .centerCrop()
                .placeholder(R.drawable.poster)
                .into(holder.photo);

        holder.favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(pojo);
            }
        });
        holder.itemConstraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDetailsListener.onDetailsListener(pojo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviePojoList.size();
    }

    public void setList(List<MoviePojo> moviePojoList) {
        this.moviePojoList = moviePojoList;
    }


    public static class FavViewHolder extends RecyclerView.ViewHolder {

        private ImageView photo;
        private Button favBtn;

        private ConstraintLayout itemConstraint;
        private View view;



        public FavViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;
            itemConstraint=itemView.findViewById(R.id.item_constrian);
            photo = itemView.findViewById(R.id.image_movie);
            favBtn = itemView.findViewById(R.id.name_movie);
        }
    }
}
