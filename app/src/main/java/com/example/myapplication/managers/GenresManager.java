package com.example.myapplication.managers;

import android.content.Context;

import com.example.myapplication.IResulService;
import com.example.myapplication.fragments.PopularFragment;
import com.example.myapplication.model.Genres;
import com.example.myapplication.model.Movie;
import com.example.myapplication.services.AsynTaskMovies;
import com.example.myapplication.utils.Helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GenresManager implements IResulService {

    private static List<Genres> genresMovie;
    private Context context;

    public GenresManager(Context context) {
        this.context = context;
        genresMovie = new ArrayList<>();

        AsynTaskMovies asynTaskMovies = new AsynTaskMovies(context);
        asynTaskMovies.setOnResulService(this);
        asynTaskMovies.execute("https://api.themoviedb.org/3/genre/movie/list");
    }


    public static String genres(Movie m) {

        StringBuilder stringBuilder = new StringBuilder();
        for (Genres g : genresMovie) {
            for (int i = 0; i < m.getGenres().length; i++) {
                if (g.getId() == m.getGenres()[i]) {
                    if (m.getGenres().length - 1 == i)
                        stringBuilder.append(g.getName());
                    else
                        stringBuilder.append(g.getName()).append(" - ");
                }
            }
        }

        return stringBuilder.toString();
    }

    @Override
    public void onSucess(String object) {
        try {
            JSONArray jsonArray = new JSONObject(object).optJSONArray("genres");

            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    Genres genres = new Genres();

                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                    genres.setId(jsonObject.getInt("id"));
                    genres.setName(jsonObject.getString("name"));

                    genresMovie.add(genres);
                }

                PopularFragment.adapter.notifyDataSetChanged();

            } else {
                //Helper.alertDialog(context, context.getString(R.string.no_items_favorites));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Exception e) {
        Helper.alertDialog(context, e.getMessage());
    }
}
