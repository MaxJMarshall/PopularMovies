package com.example.max.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.max.popularmovies.utilities.Movie;
import com.example.max.popularmovies.utilities.PicassoCreator;

public class DetailActivity extends AppCompatActivity {

    TextView mMovieDetails;
    ImageView mMoviePoster;
    int identificationNumber;
    String posterPath;
    //final String MOVIE_ID = "id";
    //final String MOVIE_IMAGE = "poster_path";
    //final String MOVIE_TITLE = "original_title";
    //final String MOVIE_RELEASE_DATE = "release_date";
    //final String MOVIE_VOTE_AVERAGE = "vote_average";
    //final String MOVIE_PLOT_SYNOPSIS = "overview";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mMovieDetails = (TextView) findViewById(R.id.tv_movie_data);
        mMoviePoster = (ImageView) findViewById(R.id.iv_poster_in_detail);
        
        Intent intentThatStartedThis = getIntent();
        
        if(intentThatStartedThis != null){
            if(intentThatStartedThis.hasExtra("movie")){
                Movie sentMovie = (Movie) intentThatStartedThis.getSerializableExtra("movie");
                identificationNumber = sentMovie.getId();
                posterPath = sentMovie.getPosterPath();
                String title;
                String release_date;
                String plot_synopsis;
                String poster_path;
                double popularity;
                
                title = sentMovie.getTitle();
                release_date = sentMovie.getReleaseDate();
                plot_synopsis = sentMovie.getOverview();
                poster_path = sentMovie.getPosterPath();
                popularity = sentMovie.getPopularity();

                Context context = this;
                new PicassoCreator(context, poster_path, mMoviePoster);

                mMovieDetails.setText("Title: " + title + "\n" +
                   "Release Date: " + release_date + "\n" +
                   "Vote Average: " + popularity + "\n" +
                   "Plot Synopsis: " + plot_synopsis);
            }
        }
    }
}
