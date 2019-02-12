package com.averoes.daff.cataloguemovie20.search;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.averoes.daff.cataloguemovie20.DetailActivity;
import com.averoes.daff.cataloguemovie20.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by daff on 09/02/19 at 7:34.
 */

public class SearchFragment extends Fragment {

    ListView listView;
    EditText editJudul;
    ImageView imgPoster;
    Button btnCari;
    MovieAdpater adapter;
    private View view;
    private ArrayList<MovieItem> movieItems;

    static final String EXTRAS_MOVIE = "EXTRAS_MOVIE";

    public SearchFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);

        listView  = view.findViewById(R.id.list_movie);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MovieItem item = (MovieItem) parent.getItemAtPosition(position);

                Intent intent = new Intent(getActivity(), DetailActivity.class);

                intent.putExtra(DetailActivity.EXTRA_TITLE, item.getMov_title());
                intent.putExtra(DetailActivity.EXTRA_OVERVIEW, item.getMov_synopsis());
                intent.putExtra(DetailActivity.EXTRA_RELEASE_DATE, item.getMov_releasedate());
                intent.putExtra(DetailActivity.EXTRA_POSTER_JPG, item.getMov_poster());
                intent.putExtra(DetailActivity.EXTRA_RATE, item.getMov_popularity());

                startActivity(intent);
            }
        });

        editJudul   = view.findViewById(R.id.edt_judul);
        imgPoster   = view.findViewById(R.id.thumbnail);

        btnCari     = view.findViewById(R.id.btn_cari);
        btnCari.setOnClickListener(movieListener);


        String judul_movie = editJudul.getText().toString().trim();

        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_MOVIE, judul_movie);

        loadUrl();
        return view;

    }

    public void loadUrl() {

        final String title = editJudul.getText().toString().trim();
         final  String URL = "https://api.themoviedb.org/3/search/movie?api_key=9428d1de175b89f3fba2af8d0b021de0&language=en-US&query="+title;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Response", "Succes");
                        try {

                            JSONObject object = new JSONObject(response);
                            JSONArray list_film = object.getJSONArray("results");

                            for (int i = 0; i < list_film.length(); i++){

                                JSONObject data = list_film.getJSONObject(i);

                                MovieItem movieItem = new MovieItem(data);
                                movieItems.add(movieItem);

                            }
                            adapter = new MovieAdpater(movieItems, getActivity());
                            listView.setAdapter(adapter);

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error" + error.toString(), Toast.LENGTH_SHORT).show();
                loadUrl();
                Log.e("Reponse", "Error");
            }
        });

       // RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        //requestQueue.add(stringRequest);
    }


    View.OnClickListener movieListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String judul_movie = editJudul.getText().toString();
            if(TextUtils.isEmpty(judul_movie)){
                return;
            }

            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_MOVIE, judul_movie);
        }
    };
}
