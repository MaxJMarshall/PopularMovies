package com.example.max.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.max.popularmovies.utilities.Movie;
import com.example.max.popularmovies.utilities.PicassoCreator;

/**
 * Created by Max on 7/5/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder>{

    //private ArrayList<Movie> mMovies;
    private Movie[] mMoviesAsArray;
    private final MovieAdapterOnClickHandler mClickHandler;

    public interface MovieAdapterOnClickHandler{
        void onClick(Movie movie);
    }

    public MovieAdapter(MovieAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final ImageView mMovieImageView;

        public MovieAdapterViewHolder(View view){
            super(view);
            Log.v("Made it to", "Movie Adapter View Holder constructor after super was called");
            mMovieImageView = (ImageView) view.findViewById(R.id.iv_poster_in_list);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            Log.v("Made it to", "On Click");
            int adapterPosition = getAdapterPosition();
            Movie movieToDetail;
            if(adapterPosition<mMoviesAsArray.length) {
                movieToDetail = mMoviesAsArray[adapterPosition];
                mClickHandler.onClick(movieToDetail);
            }
        }
    }

    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        Log.v("Made it to","On Create View Holder");
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new MovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieAdapterViewHolder holder, int position) {
        Log.v("Made it to", "On Bind View Holder");
        if(mMoviesAsArray == null){
            Log.v("No movies to bind", "ERROR on bind view holder");
        }
        for(int i = 0; i<mMoviesAsArray.length; i++){
            mMoviesAsArray[i] = mMoviesAsArray[i];
        }
        if(position<mMoviesAsArray.length && mMoviesAsArray[position] != null){
            String moviePosterPath = mMoviesAsArray[position].getPosterPath();
            Context context = holder.mMovieImageView.getContext();
            new PicassoCreator(context, moviePosterPath, holder.mMovieImageView);
            Log.v("Movies were bound", "OK on lines 72-74");
            //holder.mMovieImageView.setImageDrawable(Picasso.with(context).load("http://image.tmdb.org/t/p/w185/" + mMovies[position]));
        }

        /*else{
            String moviePosterPath = mMovies[0].getPosterPath();
            Context context = holder.mMovieImageView.getContext();
            new PicassoCreator(context, moviePosterPath, holder.mMovieImageView);
        }*/
    }

    @Override
    public int getItemCount() {
        if(mMoviesAsArray == null){
            Log.v("mMovies length", "null");
            return 0;
        }
        Log.v("mMovies length", "" + mMoviesAsArray.length);
        return mMoviesAsArray.length;
    }

    public void setMovieSet(Movie[] movieSet){
        Log.v("Made it to", "Movie Set");
        mMoviesAsArray = movieSet;
        notifyDataSetChanged();
    }
}
