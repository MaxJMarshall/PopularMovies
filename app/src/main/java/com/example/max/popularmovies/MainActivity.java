package com.example.max.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.MyGridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.max.popularmovies.utilities.Movie;
import com.example.max.popularmovies.utilities.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler{

    private RecyclerView mRecyclerView;
    private TextView mErrorMessage;
    private ProgressBar mProgressBar;

    private MovieAdapter mMovieAdapter;

    ArrayList<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mErrorMessage = (TextView)     findViewById(R.id.error_message);
        mProgressBar  = (ProgressBar)  findViewById(R.id.progress_bar);

        GridLayoutManager layoutManager =
                new MyGridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        mMovieAdapter = new MovieAdapter(this);

        mRecyclerView.setAdapter(mMovieAdapter);

        //loadMovieData();

    }

    private void loadMovieData(){
        showMovieDataView();
        new FetchMovieTask().execute();
    }


    @Override
    public void onClick(Movie movie) {
        Context context = this;
        Class destinationClass = DetailActivity.class;
        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
        intentToStartDetailActivity.putExtra("movie", movie);
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

    public class FetchMovieTask extends AsyncTask<String, Void, Movie[]>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Movie[] doInBackground(String... strings) {
            Movie[] movieList;

            final String MOVIE_RESULTS_REF = "results";

            URL url = NetworkUtils.buildDefaultMovieUrl();
            try{
                String jsonMovieResponse = NetworkUtils.getResponseFromHttpURL(url);
                JSONObject movieResultsJson = new JSONObject(jsonMovieResponse);
                JSONArray moviesArray = movieResultsJson.getJSONArray(MOVIE_RESULTS_REF);
                movieList = new Movie[moviesArray.length()];
                for(int i = 0; i<moviesArray.length(); i++){
                    JSONObject movie = moviesArray.getJSONObject(i);

                    movies.add(new Movie(movie));

                    movieList[i] = movies.get(i);
                }
            }
            catch (Exception e){
                e.printStackTrace();
                showErrorMessage();
                return null;
            }

            return movieList;
        }

        @Override
        protected void onPostExecute(Movie[] loadedMovies) {
            super.onPostExecute(loadedMovies);
            mProgressBar.setVisibility(View.INVISIBLE);
            if(loadedMovies != null && loadedMovies.length > 0){
                showMovieDataView();
                mMovieAdapter.setMovieSet(loadedMovies);
            }
            else{
                showErrorMessage();
            }
        }
    }

    public void sortByPopularity(Movie[] unsortedMovies){
        Movie[] sortedMovies = new Movie[unsortedMovies.length];
        for(int k = 0; k<unsortedMovies.length; k++){
            Movie tempMovie = unsortedMovies[0];
            for(int i = 1; i<unsortedMovies.length; i++){
                if(tempMovie.getPopularity() > unsortedMovies[i].getPopularity()){
                    tempMovie = unsortedMovies[i];
                }
                sortedMovies[k] = tempMovie;
            }
        }
        mMovieAdapter.setMovieSet(sortedMovies);
    }

    public void sortByTitle(Movie[] unsortedMovies){
        Movie[] sortedMovies = new Movie[unsortedMovies.length];
        for(int k = 0; k<unsortedMovies.length; k++){
            Movie tempMovie = unsortedMovies[0];
            for(int i = 1; i<unsortedMovies.length; i++){
                if(tempMovie.getTitle().compareTo(unsortedMovies[i].getTitle()) > 0){
                    tempMovie = unsortedMovies[i];
                }
                sortedMovies[k] = tempMovie;
            }
        }
        mMovieAdapter.setMovieSet(sortedMovies);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemRefNum = item.getItemId();

        if(itemRefNum == R.id.sort_by_popularity){

        }
        return true;
    }
}
