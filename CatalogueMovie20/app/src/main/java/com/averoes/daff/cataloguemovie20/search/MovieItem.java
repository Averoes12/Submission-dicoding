package com.averoes.daff.cataloguemovie20.search;

import org.json.JSONException;
import org.json.JSONObject;

public class MovieItem {
    private String mov_title;
    private String mov_synopsis;
    private String mov_releasedate;
    private String mov_poster;
    private String mov_popularity;

    public MovieItem(JSONObject object){


        try {
            String title = object.getString("title");
            String synopsis = object.getString("overview");
            String rlsdate = object.getString("release_date");
            String poster = object.getString("poster_path");
            String popularity = object.getString("popularity");

            this.mov_title = title;
            this.mov_synopsis = synopsis;
            this.mov_releasedate = rlsdate;
            this.mov_poster = poster;
            this.mov_popularity = popularity;

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

        public String getMov_title () {
            return mov_title;
        }

        public void setMov_title (String mov_title){
            this.mov_title = mov_title;
        }

        public String getMov_synopsis () {
            return mov_synopsis;
        }

        public void setMov_synopsis (String mov_synopsis){
            this.mov_synopsis = mov_synopsis;
        }

        public String getMov_releasedate () {
            return mov_releasedate;
        }

        public void setMov_releasedate (String mov_releasedate){
            this.mov_releasedate = mov_releasedate;
        }

        public String getMov_poster () {
            return mov_poster;
        }

        public void setMov_poster (String mov_poster){
            this.mov_poster = mov_poster;
        }

        public String getMov_popularity () {
            return mov_popularity;
        }

        public void setMov_popularity ( String mov_popularity){
            this.mov_popularity = mov_popularity;
        }
    }