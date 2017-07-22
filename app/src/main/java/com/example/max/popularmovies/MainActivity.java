package com.example.max.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler{

    private RecyclerView mRecyclerView;
    private TextView mErrorMessage;
    private ProgressBar mProgressBar;

    private MovieAdapter mMovieAdapter;

    public final String apiKey = "9dee2214e44919b386ad7e2acc1ad305";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mErrorMessage = (TextView)     findViewById(R.id.error_message);
        mProgressBar  = (ProgressBar)  findViewById(R.id.progress_bar);

        GridLayoutManager layoutManager =
                new GridLayoutManager(this, GridLayoutManager.DEFAULT_SPAN_COUNT, GridLayoutManager.HORIZONTAL, false);

        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        mMovieAdapter = new MovieAdapter(this);

        mRecyclerView.setAdapter(mMovieAdapter);

        loadMovieData();
    }

    private void loadMovieData(){
        showMovieDataView();
        new FetchMovieTask().execute();
    }


    @Override
    public void onClick(String movieIdAndData) {
        Context context = this;
        Class destinationClass = DetailActivity.class;
        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
        intentToStartDetailActivity.putExtra(Intent.EXTRA_TEXT, movieIdAndData);
        startActivity(intentToStartDetailActivity);
    }

    private void showMovieDataView(){
        mRecyclerView.setVisibility(View.VISIBLE);
        mErrorMessage.setVisibility(View.INVISIBLE);
    }

    private void showErrorMessage(){
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessage.setVisibility(View.VISIBLE);
    }

    public class FetchMovieTask extends AsyncTask<String, Void, String[]>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String[] doInBackground(String... strings) {
            String[] parsedMovieIds = null;

            final String MOVIE_ID = "id";
            final String MOVIE_IMAGE = "poster_path";
            //final String MOVIE_TITLE = "original_title";
            //final String MOVIE_RELEASE_DATE = "release_date";
            //final String MOVIE_VOTE_AVERAGE = "vote_average";
            //final String MOVIE_PLOT_SYNOPSIS = "overview";

            try{
                JSONObject movieJson = new JSONObject("https://api.themoviedb.org/3/movie/popular?api_key=" + apiKey);
                JSONArray moviesArray = movieJson.getJSONArray("results");
                parsedMovieIds = new String[moviesArray.length()];
                System.out.println(moviesArray.length());
                if(moviesArray.length()>0){
                    for(int i = 0; i<moviesArray.length(); i++){
                     //String title;
                        //String release_date;
                        //String plot_synopsis;
                        String image_path;
                        //double vote_average;
                        int id;

                        JSONObject movie = moviesArray.getJSONObject(i);
                        //title = movie.getString(MOVIE_TITLE);
                        //release_date = movie.getString(MOVIE_RELEASE_DATE);
                        //plot_synopsis = movie.getString(MOVIE_PLOT_SYNOPSIS);
                        //vote_average = movie.getDouble(MOVIE_VOTE_AVERAGE);
                        image_path = movie.getString(MOVIE_IMAGE);
                        id = movie.getInt(MOVIE_ID);

                        /*parsedMovieIds[i] = "Title: " + title + "/n" +
                                "Release Date: " + release_date + "/n" +
                                "Vote Average: " + vote_average + "n/" +
                                "Plot Synopsis: " + plot_synopsis;*/
                        parsedMovieIds[i] = image_path + " * " + id;
                    }
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }

            if(parsedMovieIds == null)
                showErrorMessage();

            return parsedMovieIds;
        }

        @Override
        protected void onPostExecute(String[] strings) {
            super.onPostExecute(strings);
            mProgressBar.setVisibility(View.INVISIBLE);
            if(strings != null){
                showMovieDataView();
                mMovieAdapter.setMoviePoster(strings);
            }
            else{
                showErrorMessage();
            }
        }

    }

}
