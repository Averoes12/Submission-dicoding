package com.averoes.daff.cataloguemovie20.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.averoes.daff.cataloguemovie20.BuildConfig;
import com.averoes.daff.cataloguemovie20.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**mo
 * Created by daff on 07/02/19 at 19:32.
 */

public class MovieAdpater extends BaseAdapter{

    private ArrayList<MovieItem> list_film = new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;

    private static final String URL = BuildConfig.URL_IMG+"w154";

    public MovieAdpater(ArrayList<MovieItem> movieItems, Context context) {
        this.context = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setList_film(ArrayList<MovieItem> item) {
        this.list_film = item;
        notifyDataSetChanged();
    }

    public void addItem(final MovieItem item){
        list_film.add(item);
        notifyDataSetChanged();
    }

    public void clearData(){
        list_film.clear();
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
        if (list_film == null) return 0;
        return list_film.size();
    }

    @Override
    public Object getItem(int position) {
        return list_film.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView  == null){
            holder  = new Holder();
            convertView = inflater.inflate(R.layout.list_model, null);

            holder.movieTittle = convertView.findViewById(R.id.movie_tittle);
            holder.moviePopularity = convertView.findViewById(R.id.popularity);
            holder.movieDesc = convertView.findViewById(R.id.descrip);
            holder.movieDate = convertView.findViewById(R.id.date_released);
            holder.movieThumbnail = convertView.findViewById(R.id.thumbnail);
            convertView.setTag(holder);
        }else {
            holder = (Holder) convertView.getTag();
        }

        holder.movieTittle.setText(list_film.get(position).getMov_title());
        holder.movieDesc.setText(list_film.get(position).getMov_synopsis());
        holder.movieDate.setText(list_film.get(position).getMov_releasedate());
        holder.moviePopularity.setText(list_film.get(position).getMov_popularity());

        Glide.with(context).load(URL +list_film.get(position).getMov_poster())
                .into(holder.movieThumbnail);

        return convertView;
    }
}
