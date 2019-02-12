package com.averoes.daff.cataloguemovie20.nowShowing;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.averoes.daff.cataloguemovie20.R;

/**
 * Created by daff on 09/02/19 at 8:00.
 */

public class Holder extends RecyclerView.ViewHolder {

    public TextView title, overview, date, rate;
    public ImageView poster;
    public LinearLayout list_movie;

    public Holder(View itemView) {
        super(itemView);

        // init the View objects
        title       =  itemView.findViewById(R.id.movie_tittle);
        poster      =  itemView.findViewById(R.id.thumbnail);
        overview    =  itemView.findViewById(R.id.descrip);
        date        =  itemView.findViewById(R.id.date_released);
        rate        = itemView.findViewById(R.id.popularity);
        list_movie  =  itemView.findViewById(R.id.ly_movie);
    }
}
