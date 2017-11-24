package com.example.asanre.topmovies;

import android.app.Application;

import com.example.asanre.topmovies.domain.Provider;
import com.facebook.stetho.Stetho;

public class TopMovieApp extends Application {

    @Override
    public void onCreate() {

        super.onCreate();

        initializeStetho();
        Provider.init(this);

    }

    private void initializeStetho() {

        Stetho.initializeWithDefaults(this);
    }
}
