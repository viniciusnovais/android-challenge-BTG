package com.example.myapplication.managers;

import android.content.Context;

import com.example.myapplication.adapters.FavoritesAdapter;
import com.example.myapplication.dao.MovieDao;
import com.example.myapplication.fragments.FavoritesFragment;
import com.example.myapplication.model.Movie;

import java.util.List;

public class FavoriteManager {

    static Context context;

    public FavoriteManager(Context context) {
        FavoriteManager.context = context;
    }

    public static long addFavoriteMovie(Movie movie) {
        MovieDao movieDao = new MovieDao(context);
        return movieDao.insert(movie);

    }

    public static List<Movie> listFavorite() {
        MovieDao movieDao = new MovieDao(context);
        return movieDao.listFavorite();
    }

    public static void delete(int id) {
        MovieDao movieDao = new MovieDao(context);
        movieDao.deletar(id);
    }

    public static int getIdByTitle(String title) {
        MovieDao movieDao = new MovieDao(context);
        return movieDao.getIdByTitle(title);
    }
}
