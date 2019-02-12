package com.averoes.daff.cataloguemovie20.upcoming;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.averoes.daff.cataloguemovie20.BuildConfig;
import com.averoes.daff.cataloguemovie20.DetailActivity;
import com.averoes.daff.cataloguemovie20.R;
import com.averoes.daff.cataloguemovie20.nowShowing.MovieList;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by daff on 09/02/19 at 20:03.
 */

public class UpcomingAdapter extends RecyclerView.Adapter<Holder> {

    private ArrayList<UpcomingList> list_upcoming;
    private Context context;

    public UpcomingAdapter(ArrayList<UpcomingList> movieItems, Context context) {
        this.context = context;
        this.list_upcoming = movieItems;
    }

    public void setList_upcoming(ArrayList<UpcomingList> list_upcoming) {
        this.list_upcoming = list_upcoming;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_model, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {

        final String URL_IMG = BuildConfig.URL_IMG+"w154";
        final UpcomingList list = list_upcoming.get(position);
        holder.title.setText(list.getMov_title());
        holder.rate.setText(list.getMov_rate());
        holder.overview.setText(list.getMov_description());
        holder.date.setText(list.getMov_date());

        Glide.with(context).load(URL_IMG+list_upcoming.get(position).getMov_image())
                .into(holder.poster);

        holder.list_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpcomingList list_film = list_upcoming.get(position);
                Intent detail = new Intent(context, DetailActivity.class);
                detail.putExtra(DetailActivity.EXTRA_TITLE, list_film.getMov_title());
                detail.putExtra(DetailActivity.EXTRA_RATE, list_film.getMov_rate());
                detail.putExtra(DetailActivity.EXTRA_OVERVIEW, list_film.getMov_description());
                detail.putExtra(DetailActivity.EXTRA_RELEASE_DATE, list_film.getMov_date());
                detail.putExtra(DetailActivity.EXTRA_POSTER_JPG, list_film.getMov_image());

                context.startActivity(detail);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list_upcoming.size();
    }
}
