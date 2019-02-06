package com.averoes.catalogmovie;

import org.json.JSONObject;

public class MovieList {

    private String mov_title;
    private String mov_synopsis;
    private String mov_releasedate;
    private String mov_poster;
    private String mov_backdrop;

    public MovieList(JSONObject object) {

        try {
            String title = object.getString("title");
            String synopsis = object.getString("overview");
            String rlsdate = object.getString("release_date");
            String poster = object.getString("poster_path");
            String backDrop = object.getString("backdrop_path");

            this.mov_title = title;
            this.mov_synopsis = synopsis;
            this.mov_releasedate = rlsdate;
            this.mov_poster = poster;
            this.mov_backdrop = backDrop;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getMov_title() {
        return mov_title;
    }

    public void setMov_title(String mov_title) {
        this.mov_title = mov_title;
    }

    public String getMov_synopsis() {
        return mov_synopsis;
    }

    public void setMov_synopsis(String mov_synopsis) {
        this.mov_synopsis = mov_synopsis;
    }

    public String getMov_releasedate() {
        return mov_releasedate;
    }

    public void setMov_releasedate(String mov_releasedate) {
        this.mov_releasedate = mov_releasedate;
    }

    public String getMov_poster() {
        return mov_poster;
    }

    public void setMov_poster(String mov_poster) {
        this.mov_poster = mov_poster;
    }

    public String getMov_backdrop() {
        return mov_backdrop;
    }

    public void setMov_backdrop(String mov_backdrop) {
        this.mov_backdrop = mov_backdrop;
    }
}
