package com.example.max.popularmovies.utilities;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Max on 8/3/17.
 */

public class MovieDBJsonUtils {
    final String MOVIE_RESULTS_REF = "results";
    public ArrayList<Movie> getSimpleMovieDataFromJson(Context context, String movieJasonStr) throws JSONException{

        JSONObject jsonObject = new JSONObject(movieJasonStr);
        JSONArray jsonArray = jsonObject.getJSONArray(MOVIE_RESULTS_REF);
        ArrayList<Movie> moviesArray = new ArrayList<>();
        for(int i = 0; i<jsonArray.length(); i++){
            JSONObject jsonMovie = jsonArray.getJSONObject(i);
            Movie movie = new Movie(jsonMovie);
            moviesArray.add(movie);
        }

        return moviesArray;
    }
}
