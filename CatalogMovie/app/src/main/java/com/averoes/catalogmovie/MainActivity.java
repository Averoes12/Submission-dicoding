package com.averoes.catalogmovie;


import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import com.averoes.catalogmovie.adapter.MovieAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MovieList>> {

    ListView list_Movie;
    EditText edtTitle;
    ImageView imgMovie;
    Button cari;
    MovieAdapter adapter;

    static final String EXTRAS_MOVIE = "Movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTitle = findViewById(R.id.edtCari);
        imgMovie = findViewById(R.id.thumbnail);
        cari = findViewById(R.id.btnCari);

        adapter = new MovieAdapter(this);
        adapter.notifyDataSetChanged();

        list_Movie = findViewById(R.id.list_movie);
        list_Movie.setAdapter(adapter);
        list_Movie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MovieList item = (MovieList)parent.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);

                intent.putExtra(DetailActivity.TITTLE_DETAIL, item.getMov_title());
                intent.putExtra(DetailActivity.DESC_DETAIL, item.getMov_synopsis());
                intent.putExtra(DetailActivity.RELEASED_DATE, item.getMov_releasedate());
                intent.putExtra(DetailActivity.POSTER_PATH, item.getMov_poster());
                intent.putExtra(DetailActivity.BACKDROP_PATH, item.getMov_backdrop());

                startActivity(intent);
            }
        });



        cari.setOnClickListener(whenClick);

        String tittle = edtTitle.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_MOVIE, tittle);

        getLoaderManager().initLoader(0,bundle, this);

    }

    @Override
    public Loader<ArrayList<MovieList>> onCreateLoader(int id, Bundle args) {
        String movie_tittle = "";
        if (args != null){

            movie_tittle = args.getString(EXTRAS_MOVIE);
        }
        return new MyAsyncTaskLoader(this, movie_tittle);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MovieList>> loader, ArrayList<MovieList> data) {

        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MovieList>> loader) {

        adapter.setData(null);
    }

    View.OnClickListener whenClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String tittle_movie = edtTitle.getText().toString();

            if (TextUtils.isEmpty(tittle_movie))return;

            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_MOVIE, tittle_movie);

            getLoaderManager().restartLoader(0, bundle, MainActivity.this);
            }
        };
    }