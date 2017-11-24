package com.example.asanre.topmovies.ui.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;
import com.example.asanre.topmovies.R;

public class GlideHelper {

    private static String baseLowUrl;
    private static String baseHighUrl;

    public static void getImageForList(ImageView imageView, Context context, String url) {

        setBaseUrls(context);
        Glide.with(context).load(getListImageUrl(url))
                .apply(new RequestOptions().error(R.drawable.ic_image_error)
                        .placeholder(R.drawable.ic_image_holder)
                        .override(110, 130)
                        .centerCrop())
                .into(imageView);
    }

    public static void getHeroImage(ImageView imageView, Context context, String url) {

        setBaseUrls(context);
        RequestBuilder<Drawable> thumbnail = Glide.with(context)
                .load(getListImageUrl(url))
                .apply(new RequestOptions().dontAnimate().centerCrop().priority(Priority.HIGH));

        Glide.with(context)
                .load(getHeroImageUrl(url))
                .apply(new RequestOptions().error(R.drawable.ic_image_error)
                        .dontAnimate()
                        .centerCrop())
                .thumbnail(thumbnail)
                .into(imageView);
    }

    private static void setBaseUrls(Context context) {

        if (deviceOnWifi(context)) {
            baseLowUrl = "http://image.tmdb.org/t/p/w185%s";
            baseHighUrl = "http://image.tmdb.org/t/p/original%s";
        } else {
            baseLowUrl = "http://image.tmdb.org/t/p/w92%s";
            baseHighUrl = "http://image.tmdb.org/t/p/w500%s";
        }
    }

    private static boolean deviceOnWifi(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {

            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(
                    ConnectivityManager.TYPE_WIFI);
            return networkInfo.isConnected();
        }

        return false;
    }

    private static String getListImageUrl(String path) {

        return String.format(baseLowUrl, path);
    }

    private static String getHeroImageUrl(String path) {

        return String.format(baseHighUrl, path);
    }
}
