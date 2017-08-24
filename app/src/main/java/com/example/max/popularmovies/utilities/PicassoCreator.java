package com.example.max.popularmovies.utilities;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.net.URL;

/**
 * Created by Max on 8/24/17.
 */

public class PicassoCreator {
    Picasso poster;
    String urlAsString;

    public PicassoCreator(Context context, String posterKey, ImageView target){
        URL url = NetworkUtils.buildPosterUrl(posterKey);
        try {
            urlAsString = NetworkUtils.getResponseFromHttpURL(url);
            Picasso.with(context).load(urlAsString).into(target);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
