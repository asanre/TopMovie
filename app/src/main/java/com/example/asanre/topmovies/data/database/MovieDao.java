package com.example.asanre.topmovies.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.asanre.topmovies.data.model.MovieEntity;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(MovieEntity... movie);

    @Query("SELECT * FROM movie WHERE page = :page")
    MovieEntity[] getMoviesByPage(int page);

    @Query("delete from movie")
    void clear();
}
