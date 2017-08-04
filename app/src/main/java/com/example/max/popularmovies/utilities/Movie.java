package com.example.max.popularmovies.utilities;

import org.json.JSONObject;

import java.net.URL;

/**
 * Created by Max on 8/3/17.
 */

public class Movie {
    private static final String  MOVIE_TITLE_REF = "original_title";
    private static final String  MOVIE_ID_REF = "id";
    private static final String  MOVIE_POPULARITY_REF = "popularity";
    private static final String  MOVIE_POSTER_PATH_REF = "poster_path";
    private static final String  MOVIE_OVERVIEW_REF = "overview";
    private static final String  MOVIE_RELEASE_DATE_REF = "release_date";

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

    public Movie(){
        title = "Fight Club";
        id = 550;
        popularity = 10.173646;
        posterPath = "/adw6Lq9FiC9zjYEpOqfq03ituwp.jpg";
        overview = "A ticking-time-bomb insomniac and a slippery soap salesman channel primal \n" +
                "male aggression into a shocking new form of therapy. Their concept catches on, with \n" +
                "underground \"fight clubs\" forming in every town, until an eccentric gets in the way and\n" +
                "ignites an out-of-control spiral toward oblivion.";
        releaseDate = "1999-10-15";
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

    public int getId(){
        return id;
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

    public void setTitle(String title){
        this.title = title;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setPopularity(double popularity){
        this.popularity = popularity;
    }

    public void setPosterPath(String posterPath){
        this.posterPath = posterPath;
    }

    public void setOverview(String overview){
        this.overview = overview;
    }

    public void setReleaseDate(String releaseDate){
        this.releaseDate = releaseDate;
    }

    public String toSting(){
        return "Title: " + title + "\n" +
                "Popularity: " + popularity + "\n" +
                "Release Date: " + releaseDate + "\n" +
                "Overview: " + overview;
    }
}
