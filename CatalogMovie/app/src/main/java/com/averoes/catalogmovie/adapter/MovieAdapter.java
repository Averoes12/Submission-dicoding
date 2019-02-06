package com.averoes.catalogmovie.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.averoes.catalogmovie.DetailActivity;
import com.averoes.catalogmovie.MovieList;
import com.averoes.catalogmovie.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.GlideException;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MovieAdapter extends BaseAdapter {

    private ArrayList<MovieList> data = new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;

    private String url_image = "http://image.tmdb.org/t/p/w154";

    public MovieAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<MovieList> items) {
        data = items;
        notifyDataSetChanged();
    }

    public void addItem(final MovieList item) {
        data.add(item);
        notifyDataSetChanged();
    }

    public void clearData() {
        data.clear();
    }


    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        if (data == null) return 0;
        return data.size();
    }

    @Override
    public MovieList getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.movie_model, null);
            holder.movieThumbnail = convertView.findViewById(R.id.thumbnail);
            holder.movieTittle = convertView.findViewById(R.id.movie_tittle);
            holder.movieDesc = convertView.findViewById(R.id.descrip);
            holder.movieDate = convertView.findViewById(R.id.date_released);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.movieTittle.setText(data.get(position).getMov_title());
        holder.movieDesc.setText(data.get(position).getMov_synopsis());
        holder.movieDate.setText(data.get(position).getMov_releasedate());

        Picasso.with(context).load(url_image + data.get(position).getMov_poster())
                .into(holder.movieThumbnail);


        return convertView;
    }
}


