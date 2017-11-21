package com.example.asanre.topmovies.data.network.restclient;

import com.example.asanre.topmovies.BuildConfig;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private final static String BASE_URL = "https://api.themoviedb.org/3/movie/";
    private static ApiService apiInterface;

    public synchronized static ApiService getClient() {

        if (apiInterface == null) {
            final OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

            OkHttpClient.Builder builder = okHttpClient.newBuilder()
                    .readTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS);

            if (BuildConfig.DEBUG) {
                builder.addNetworkInterceptor(new StethoInterceptor());
            }

            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiInterface = retrofit.create(ApiService.class);
        }

        return apiInterface;
    }
}
