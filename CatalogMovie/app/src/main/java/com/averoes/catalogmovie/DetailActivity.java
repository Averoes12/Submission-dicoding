package com.averoes.catalogmovie;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailActivity extends AppCompatActivity {

    TextView tittle_detail, desc, date_of_released;
    ImageView img1;

    public static final String TITTLE_DETAIL = "Tittle";
    public static final String DESC_DETAIL   = "Desc Tittle";
    public static final String RELEASED_DATE = "Released Date";
    public static final String POSTER_PATH   = "Poster Path";
    public static final String BACKDROP_PATH  = "Backdrop Path";
    public Context context;
    private String url_image1 = "http://image.tmdb.org/t/p/w500/";

    private RequestOptions options;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detai);

        tittle_detail = findViewById(R.id.tittle_detail);
        desc = findViewById(R.id.desc_detail);
        date_of_released = findViewById(R.id.date_released_detail);

        img1 = findViewById(R.id.img_detail);

        String tittle = getIntent().getStringExtra(TITTLE_DETAIL);
        String description = getIntent().getStringExtra(DESC_DETAIL);
        String released_date = getIntent().getStringExtra(RELEASED_DATE);
        String poster_path = getIntent().getStringExtra(POSTER_PATH);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(released_date);

            SimpleDateFormat new_date = new SimpleDateFormat("dd-MM-yyyy");
            String date_released = new_date.format(date);
            date_of_released.setText(date_released);
        }catch (Exception e){
            e.printStackTrace();
        }

        tittle_detail.setText(tittle);
        desc.setText(description);
        Picasso.with(context).load(url_image1 + poster_path).into(img1);

    }
}
