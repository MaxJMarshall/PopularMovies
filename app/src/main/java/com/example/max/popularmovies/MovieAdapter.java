package com.example.max.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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

    private Movie[] mMovies;
    private final MovieAdapterOnClickHandler mClickHandler;

    public interface MovieAdapterOnClickHandler{
        void onClick(String moviePosterPath);
    }

    public MovieAdapter(MovieAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final ImageView mMovieImageView;

        public MovieAdapterViewHolder(View view){
            super(view);
            mMovieImageView = (ImageView) view.findViewById(R.id.iv_movie_poster);
        }

        @Override
        public void onClick(View v){
            int adapterPosition = getAdapterPosition();
            String moviePosterPath = mMovies[adapterPosition].getPosterPath();
            mClickHandler.onClick(moviePosterPath);
        }
    }

    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);

        return new MovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieAdapterViewHolder holder, int position) {
        if(position<mMovies.length){
            String moviePosterPath = mMovies[position].getPosterPath();
            Context context = holder.mMovieImageView.getContext();
            new PicassoCreator(context, moviePosterPath, holder.mMovieImageView);
            //holder.mMovieImageView.setImageDrawable(Picasso.with(context).load("http://image.tmdb.org/t/p/w185/" + mMovies[position]));
        }
    }

    @Override
    public int getItemCount() {
        if(mMovies == null) return 0;
        return mMovies.length;
    }

    public void setMoviePoster(Movie posters[]){
        mMovies = posters;
        notifyDataSetChanged();
    }
}
