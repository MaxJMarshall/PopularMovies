package com.example.max.popularmovies.utilities;

import org.json.JSONObject;

import java.net.URL;

/**
 * Created by Max on 8/3/17.
 */

public class Movie {
    private static final String  MOVIE_TITLE_REF = "title";
    private static final String  MOVIE_ID_REF = "id";
    private static final String  MOVIE_POPULARITY_REF = "popularity";
    private static final String  MOVIE_POSTER_PATH_REF = "poster path";
    private static final String  MOVIE_OVERVIEW_REF = "overview";
    private static final String  MOVIE_RELEASE_DATE_REF = "release date";

    private String title;
    private int id;
    private double popularity;
    private String posterPath;
    private String overview;
    private String releaseDate;

    public Movie(JSONObject jsonObject){
        setData(jsonObject);
    }

    public Movie(int identification){
        id = identification;
        URL url = NetworkUtils.buildSpecificMovieUrl(id);
        String urlAsString = url.toString();
        try{
            JSONObject jsonObject = new JSONObject(urlAsString);
            setData(jsonObject);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public Movie(String title, int id, double popularity, String posterPath, String overview, String releaseDate){
        this.title = title;
        this.id = id;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.overview = overview;
        this.releaseDate = releaseDate;

    }

    private void setData(JSONObject jsonObject){
        try {
            title = jsonObject.getString(MOVIE_TITLE_REF);
            id = jsonObject.getInt(MOVIE_ID_REF);
            popularity = jsonObject.getDouble(MOVIE_POPULARITY_REF);
            posterPath = jsonObject.getString(MOVIE_POSTER_PATH_REF);
            overview = jsonObject.getString(MOVIE_OVERVIEW_REF);
            releaseDate = jsonObject.getString(MOVIE_RELEASE_DATE_REF);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getTitle(){
        return title;
    }

    public double getPopularity(){
        return popularity;
    }

    public String getPosterPath(){
        return posterPath;
    }

    public String getOverview(){
        return overview;
    }

    public String getReleaseDate(){
        return releaseDate;
    }
}
