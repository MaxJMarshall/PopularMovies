package com.example.max.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity {

    String mMovieTitle;
    TextView mMovieDetails;
    ImageView mMoviePoster;
    int identificationNumber;
    String posterPath;
    //final String MOVIE_ID = "id";
    //final String MOVIE_IMAGE = "poster_path";
    final String MOVIE_TITLE = "original_title";
    final String MOVIE_RELEASE_DATE = "release_date";
    final String MOVIE_VOTE_AVERAGE = "vote_average";
    final String MOVIE_PLOT_SYNOPSIS = "overview";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mMovieDetails = (TextView) findViewById(R.id.tv_movie_data);
        mMoviePoster = (ImageView) findViewById(R.id.iv_movie_poster);
        
        Intent intentThatStartedThis = getIntent();
        
        if(intentThatStartedThis != null){
            if(intentThatStartedThis.hasExtra(Intent.EXTRA_TEXT)){
                String idAndPosterPath = intentThatStartedThis.getStringExtra(Intent.EXTRA_TEXT);
                mMovieTitle = intentThatStartedThis.getStringExtra(Intent.EXTRA_TEXT);
                mMovieDetails.setText(mMovieTitle);
                identificationNumber = Integer.parseInt(idAndPosterPath.substring(0,idAndPosterPath.indexOf(" *")));
                posterPath = idAndPosterPath.substring(idAndPosterPath.indexOf("* "));
                
                
            }
            try{
                JSONObject movieJson = new JSONObject("https://api.themoviedb.org/3/movie/" + identificationNumber +"?api_key=9dee2214e44919b386ad7e2acc1ad305");
                String title;
                String release_date;
                String plot_synopsis;
                double vote_average;
                
                title = movieJson.getString(MOVIE_TITLE);
                release_date = movieJson.getString(MOVIE_RELEASE_DATE);
                plot_synopsis = movieJson.getString(MOVIE_PLOT_SYNOPSIS);
                vote_average = movieJson.getDouble(MOVIE_VOTE_AVERAGE);
                
                mMovieDetails.setText("Title: " + title + "/n" +
                   "Release Date: " + release_date + "/n" +
                   "Vote Average: " + vote_average + "n/" +
                   "Plot Synopsis: " + plot_synopsis);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
