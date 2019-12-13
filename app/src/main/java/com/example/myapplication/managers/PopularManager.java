package com.example.myapplication.managers;

import android.content.Context;
import android.util.Log;

import androidx.core.view.accessibility.AccessibilityViewCommand;

import com.example.myapplication.IResulService;
import com.example.myapplication.R;
import com.example.myapplication.fragments.PopularFragment;
import com.example.myapplication.model.Movie;
import com.example.myapplication.services.AsynTaskMovies;
import com.example.myapplication.utils.Helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PopularManager implements IResulService {


    private static List<Movie> movieList;

    private Context context;

    public PopularManager(Context context) {
        this.context = context;
        movieList = new ArrayList<>();

        AsynTaskMovies asynTaskMovies = new AsynTaskMovies(context);
        asynTaskMovies.setOnResulService(this);
        asynTaskMovies.execute("https://api.themoviedb.org/3/movie/popular");
    }


    public static List<Movie> getMovieList() {
        return movieList;
    }

    @Override
    public void onSucess(String object) {
        try {
            JSONArray jsonArray = new JSONObject(object).optJSONArray("results");

            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    Movie movie = new Movie();

                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                    movie.setName(jsonObject.getString("original_title"));
                    movie.setTitle(jsonObject.getString("title"));
                    movie.setPoster(jsonObject.getString("poster_path"));
                    movie.setSynopsis(jsonObject.getString("overview"));
                    int[] genres = new int[jsonObject.getJSONArray("genre_ids").length()];
                    for (int j = 0; j < genres.length; j++) {
                        genres[j] = jsonObject.getJSONArray("genre_ids").getInt(j);
                    }
                    movie.setGenres(genres);
                    movie.setNote((float) jsonObject.getDouble("vote_average"));
                    movie.setYear(jsonObject.getString("release_date").split("-")[0]);

                    movieList.add(movie);
                }

                PopularFragment.adapter.notifyDataSetChanged();

            } else {
                Helper.alertDialog(context, context.getString(R.string.no_items));
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
