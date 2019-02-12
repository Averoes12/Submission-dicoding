package com.averoes.daff.cataloguemovie20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    public static String EXTRA_TITLE        = "extra_title";
    public static String EXTRA_OVERVIEW     = "extra_overview";
    public static String EXTRA_RELEASE_DATE = "extra_release_date";
    public static String EXTRA_POSTER_JPG   = "extra_poster_jpg";
    public static String EXTRA_RATE         = "extra_rate";

    private static final String URL_IMG = BuildConfig.URL_IMG+"w500";
    private static final String THUMBNAIL = BuildConfig.URL_IMG+"w154";
     TextView title, overview, rate, date;
     ImageView poster, thumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        
        title = findViewById(R.id.title_detail);
        overview = findViewById(R.id.overview_detail);
        date = findViewById(R.id.date_detail);
        rate = findViewById(R.id.rating_detail);
        poster = findViewById(R.id.img_detail);
        thumbnail = findViewById(R.id.thumbnail_detail);

        String title_detail = getIntent().getStringExtra(EXTRA_TITLE);
        String overview_detail = getIntent().getStringExtra(EXTRA_OVERVIEW);
        String rating_detail = getIntent().getStringExtra(EXTRA_RATE);
        String poster_jpg = getIntent().getStringExtra(EXTRA_POSTER_JPG);
        String release_date = getIntent().getStringExtra(EXTRA_RELEASE_DATE);


        title.setText(title_detail);
        overview.setText(overview_detail);
        date.setText(release_date);
        rate.setText(rating_detail);

        Glide.with(getApplicationContext()).load(URL_IMG + poster_jpg).into(poster);
        Glide.with(getApplicationContext()).load(THUMBNAIL + poster_jpg).into(thumbnail);

    }
}
