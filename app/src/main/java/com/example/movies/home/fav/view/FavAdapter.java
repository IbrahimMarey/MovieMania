package com.example.movies.home.fav.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movies.R;
import com.example.movies.models.pojos.MoviePojo;

import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.FavViewHolder> {

    private Context context;
    private List<MoviePojo> moviePojoList;
    private OnClickFavListener listener;

    public FavAdapter(Context context, List<MoviePojo> moviePojoList, OnClickFavListener listener) {
        this.context = context;
        this.moviePojoList = moviePojoList;
        this.listener = listener;

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
        holder.name.setText(moviePojoList.get(position).getTitle());


        Glide.with(context)
                .load(moviePojoList.get(position).getPoster_path())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.photo);

        holder.favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(moviePojoList.get(position));
            }
        });
        holder.itemConstraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDetailsItemClick(moviePojoList.get(position));
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
        private TextView name;
        private ImageView favBtn;

        private ConstraintLayout itemConstraint;
        private View view;



        public FavViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;
            photo = itemView.findViewById(R.id.image_movie);
            name = itemView.findViewById(R.id.name_movie);
            favBtn = itemView.findViewById(R.id.fav_btn_home);
            itemConstraint = view.findViewById(R.id.item_constrian);
        }
    }
}
