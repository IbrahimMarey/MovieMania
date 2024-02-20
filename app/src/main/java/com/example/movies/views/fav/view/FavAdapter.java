package com.example.movies.views.fav.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private OnClickFavListener onClickDelFavListener;

    public FavAdapter(Context context, List<MoviePojo> moviePojoList, OnClickFavListener listener,OnDetailsListener onDetailsListener,OnClickFavListener delItem) {
        this.context = context;
        this.moviePojoList = moviePojoList;
        this.listener = listener;
        this.onDetailsListener=onDetailsListener;
        this.onClickDelFavListener= delItem;
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
                .placeholder(R.drawable.video)
                .into(holder.photo);
        holder.favTitleTV.setText(pojo.getTitle());
        holder.delItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDetailsListener.onDetailsListener(pojo);
            }
        });
        holder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickDelFavListener.onItemClick(pojo);
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
        private ImageView imgDel;
        private TextView favTitleTV;
        private ConstraintLayout itemConstraint;
        private LinearLayout delItem;
        private View view;



        public FavViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;
            itemConstraint=itemView.findViewById(R.id.item_constrian);
            delItem=itemView.findViewById(R.id.fav_linear_item);
            photo = itemView.findViewById(R.id.image_movie);
            favTitleTV = itemView.findViewById(R.id.fav_movie_title);
            imgDel = itemView.findViewById(R.id.del_item_fav);
        }
    }
}
