package com.example.max.popularmovies.utilities;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Max on 7/30/17.
 */

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String API_KEY =
            new ApiKey().getApiKey();

    private static final String DEFAULT_MOVIE_URL =
            "https://api.themoviedb.org/3/movie/popular?api_key=" + API_KEY;

    private static final String SPECIFIC_MOVIE_URL = "https://api.themoviedb.org/3/movie/";
    public static URL buildDefaultMovieUrl(){

        Uri builtUri = Uri.parse(DEFAULT_MOVIE_URL);

        URL url = null;

        try{
            url = new URL(builtUri.toString());
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }

        Log.v(TAG, "Built URL" + builtUri);

        return url;
    }

    public static URL buildSpecificMovieUrl(int id){

        Uri builtUri = Uri.parse(SPECIFIC_MOVIE_URL + id +"?api_key=" + API_KEY);

        URL url = null;

        try{
            url = new URL(builtUri.toString());
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }

        Log.v(TAG, "Built URL" + builtUri);

        return url;
    }

    public static String getResponseFromHttpURL(URL url) throws IOException{
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try{
            InputStream is = urlConnection.getInputStream();

            Scanner scanner = new Scanner(is);
            scanner.useDelimiter("\\A");

            if(scanner.hasNext()){
                return scanner.next();
            }

            return null;
        }
        finally {
            urlConnection.disconnect();
        }
    }
}
