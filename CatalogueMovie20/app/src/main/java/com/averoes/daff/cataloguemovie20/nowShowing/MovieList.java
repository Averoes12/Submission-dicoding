package com.averoes.daff.cataloguemovie20.nowShowing;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by daff on 08/02/19 at 20:26.
 */

public class MovieList {
    private String mov_title;
    private String mov_description;
    private String mov_date;
    private String mov_image;
    private String mov_rate;

    public MovieList(JSONObject object){


        try {
            String title = object.getString("title");
            String synopsis = object.getString("overview");
            String rlsdate = object.getString("release_date");
            String poster = object.getString("poster_path");
            String popularity = object.getString("popularity");

            this.mov_title = title;
            this.mov_description = synopsis;
            this.mov_date = rlsdate;
            this.mov_image = poster;
            this.mov_rate = popularity;

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getMov_title() {
        return mov_title;
    }

    public void setMov_title(String mov_title) {
        this.mov_title = mov_title;
    }

    public String getMov_description() {
        return mov_description;
    }

    public void setMov_description(String mov_description) {
        this.mov_description = mov_description;
    }

    public String getMov_date() {
        return mov_date;
    }

    public void setMov_date(String mov_date) {
        this.mov_date = mov_date;
    }

    public String getMov_image() {
        return mov_image;
    }

    public void setMov_image(String mov_image) {
        this.mov_image = mov_image;
    }

    public String getMov_rate() {
        return mov_rate;
    }

    public void setMov_rate(String mov_rate) {
        this.mov_rate = mov_rate;
    }
}
