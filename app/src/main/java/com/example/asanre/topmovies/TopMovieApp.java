package com.example.asanre.topmovies;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class TopMovieApp extends Application {

    @Override
    public void onCreate() {

        super.onCreate();

        initializeStetho();
    }

    private void initializeStetho() {

        Stetho.initializeWithDefaults(this);
    }
}
