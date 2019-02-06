package com.averoes.catalogmovie;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MyAsyncTaskLoader extends AsyncTaskLoader<ArrayList<MovieList>> {

    private ArrayList<MovieList> data;
    private boolean result = false;

    private String mTitleMovie;

    public MyAsyncTaskLoader(final Context context, String titleMovie) {
        super(context);

        onContentChanged();
        this.mTitleMovie = titleMovie;
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged()) {
            forceLoad();

        } else if (result) {

            deliverResult(data);
        }
    }

    @Override
    public void deliverResult(final ArrayList<MovieList> items) {
        data = items;
        result = true;
        super.deliverResult(items);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (result) {
            onReleaseResource(data);
            data = null;
            result = false;
        }
    }


    //Tempat isi API KEY
    private static final String API_KEY = "9428d1de175b89f3fba2af8d0b021de0";

    private void onReleaseResource(ArrayList<MovieList> data) {
    }


    @Override
    public ArrayList<MovieList> loadInBackground() {

        SyncHttpClient client = new SyncHttpClient();

        final ArrayList<MovieList> movieLists = new ArrayList<>();

        String url = "https://api.themoviedb.org/3/search/movie?api_key="+API_KEY+"&language=en-US&query=" + mTitleMovie;

        if (TextUtils.isEmpty(mTitleMovie)){
            
        }

        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                super.onStart();
                Log.d("TAG", "MASUK");
                setUseSynchronousMode(true);

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {

                    String result = new String(responseBody);
                    JSONObject object = new JSONObject(result);
                    JSONArray list = object.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject film = list.getJSONObject(i);

                        MovieList movieItems = new MovieList(film);
                        movieLists.add(movieItems);
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
        return movieLists;
    }
}
