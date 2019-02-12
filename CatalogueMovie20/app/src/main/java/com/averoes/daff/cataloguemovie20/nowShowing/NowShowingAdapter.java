package com.averoes.daff.cataloguemovie20.nowShowing;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.averoes.daff.cataloguemovie20.BuildConfig;
import com.averoes.daff.cataloguemovie20.DetailActivity;
import com.averoes.daff.cataloguemovie20.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by daff on 08/02/19 at 16:58.
 */

public class NowShowingAdapter extends RecyclerView.Adapter<Holder> {

    private ArrayList<MovieList> data_film;
    private Context context;

    public NowShowingAdapter(ArrayList<MovieList> item, Context context1) {
        this.data_film = item;
        this.context = context1;

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_model, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {

        final  String URL = BuildConfig.URL_IMG+"w154";
        final MovieList movieList = data_film.get(position);
        holder.title.setText(movieList.getMov_title());
        holder.overview.setText(movieList.getMov_description());
        holder.date.setText(movieList.getMov_date());
        holder.rate.setText(movieList.getMov_rate());

        Glide.with(context).load(URL+data_film.get(position).getMov_image())
                .into(holder.poster);

        holder.list_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieList list_film = data_film.get(position);
                Intent detail = new Intent(context, DetailActivity.class);
                detail.putExtra(DetailActivity.EXTRA_TITLE, list_film.getMov_title());
                detail.putExtra(DetailActivity.EXTRA_OVERVIEW, list_film.getMov_description());
                detail.putExtra(DetailActivity.EXTRA_RATE, list_film.getMov_rate());
                detail.putExtra(DetailActivity.EXTRA_POSTER_JPG, list_film.getMov_image());

                context.startActivity(detail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data_film.size();
    }
}
