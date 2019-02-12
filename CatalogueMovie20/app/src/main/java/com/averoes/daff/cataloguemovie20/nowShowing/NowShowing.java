package com.averoes.daff.cataloguemovie20.nowShowing;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.averoes.daff.cataloguemovie20.BuildConfig;
import com.averoes.daff.cataloguemovie20.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by daff on 08/02/19 at 16:14.
 */

public class NowShowing extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private View view;
    private ArrayList<MovieList> movieItems;


    public NowShowing() {
    }

    @SuppressLint("ResourceType")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_now_showing, container, false);

        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        movieItems = new ArrayList<>();

        loadUrl();
        return view;
    }

    private final static String URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=9428d1de175b89f3fba2af8d0b021de0&language=en-US&page=";

    private void loadUrl() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject object = new JSONObject(response);

                            JSONArray list = object.getJSONArray("results");
                            for (int i = 0; i < list.length(); i++) {

                                JSONObject data = list.getJSONObject(i);

                                MovieList movie = new MovieList(data);

                                movieItems.add(movie);
                            }

                            adapter = new NowShowingAdapter(movieItems, getActivity());
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error" + error.toString(), Toast.LENGTH_SHORT).show();
                loadUrl();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }


}
