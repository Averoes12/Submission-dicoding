package com.averoes.daff.cataloguemovie20.search;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BuildConfig;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by daff on 07/02/19 at 20:18.
 */

public class MyAsyncTaskLoader extends AsyncTaskLoader<ArrayList<MovieItem>> {
    private ArrayList<MovieItem> list_film;
    private boolean result = false;

    public String title = "";

    public MyAsyncTaskLoader(final Context context, String title_movie) {
        super(context);

        onContentChanged();
        this.title = title_movie;
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged()) {
            forceLoad();
        } else if (result) {
            deliverResult(list_film);
        }
    }

    @Override
    public void deliverResult(ArrayList<MovieItem> data) {
        super.deliverResult(data);
        list_film = data;
        result = true;
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (result) {
            onReleaseResource(list_film);
            list_film = null;
            result = false;
        }
    }

    private void onReleaseResource(ArrayList<MovieItem> list_film) {
    }

    private static final String API_KEY = "9428d1de175b89f3fba2af8d0b021de0";
    private final String URL = "https://api.themoviedb.org/3/movie/popular?api_key="+API_KEY+"&language=en-US&query=";

    @Override
    public ArrayList<MovieItem> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();

        final ArrayList<MovieItem> movieItems = new ArrayList<>();

        client.get(URL, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
                Log.d("Response", "Log in");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.v("Response", "Succes");
                try {

                    String result = new String(responseBody);
                    JSONObject object = new JSONObject(result);
                    JSONArray list = object.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject film = list.getJSONObject(i);

                        MovieItem movieItem = new MovieItem(film);
                        movieItems.add(movieItem);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                Log.e("Response", "Failure");
            }
        });

        return movieItems;
    }
}
