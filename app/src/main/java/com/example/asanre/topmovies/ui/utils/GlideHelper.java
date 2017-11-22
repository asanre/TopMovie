package com.example.asanre.topmovies.ui.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.asanre.topmovies.R;

public class GlideHelper {

    private static final String BASE_LIST_IMAGE_URL = "http://image.tmdb.org/t/p/w342%s";
    private static final String BASE_HERO_IMAGE_URL = "http://image.tmdb.org/t/p/w780%s";

    public static String getListImageUrl(String path) {

        return String.format(BASE_LIST_IMAGE_URL, path);
    }

    public static Object getHeroImage(String path) {

        return String.format(BASE_HERO_IMAGE_URL, path);
    }

    public static void getImageForList(ImageView imageView, Context context, String url) {

        Glide.with(context)
                .load(url)
                .apply(new RequestOptions().error(R.drawable.ic_image_error)
                        .placeholder(R.drawable.ic_image_holder)
                        .override(180, 220)
                        .centerCrop())
                .into(imageView);
    }
}
