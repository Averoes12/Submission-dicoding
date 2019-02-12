package com.averoes.daff.cataloguemovie20.upcoming;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.averoes.daff.cataloguemovie20.R;
import com.averoes.daff.cataloguemovie20.nowShowing.MovieList;
import com.averoes.daff.cataloguemovie20.nowShowing.NowShowingAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Upcoming extends Fragment {


    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    public View view;
    public ArrayList<UpcomingList> movieItems;

    public Upcoming() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_upcoming, container, false);

        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        movieItems = new ArrayList<>();

        loadUrl();

        return view;
    }

    private final static String URL = "https://api.themoviedb.org/3/movie/upcoming?api_key=9428d1de175b89f3fba2af8d0b021de0&language=en-US&page=";

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

                                UpcomingList movie = new UpcomingList(data);

                                movieItems.add(movie);
                            }

                            adapter = new UpcomingAdapter(movieItems, getActivity());
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
