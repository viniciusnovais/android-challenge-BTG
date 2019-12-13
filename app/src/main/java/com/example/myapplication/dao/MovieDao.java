package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.lifecycle.LiveData;

import com.example.myapplication.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieDao {

    SQLiteDatabase database;
    private DbHelper helper;


    public MovieDao(Context context) {
        helper = new DbHelper(context);
    }

    SQLiteDatabase getDatabase() {

        if (database == null)
            database = helper.getWritableDatabase();

        return database;
    }

    public long insert(Movie m) {

        ContentValues values = new ContentValues();

        try {

            values.put("title", m.getTitle());
            values.put("poster", m.getPoster());
            values.put("sinopse", m.getSynopsis());
            values.put("note", m.getNote());
            values.put("favorite", 1);
            values.put("gen", m.getGenresStr());


            return getDatabase().insert("favorites_movies", null, values);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public int deletar(int id) {
        return getDatabase().delete("favorites_movies", "_id = ?", new String[]{id + ""});
    }

    public int getIdByTitle(String title) {
        Cursor cursor = getDatabase().rawQuery("SELECT _id FROM favorites_movies WHERE title = ?", new String[]{title});
        try {
            while (cursor.moveToFirst())
                return cursor.getInt(cursor.getColumnIndex("_id"));

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            cursor.close();
        }
        return 0;
    }


    public List<Movie> listFavorite() {
        Cursor cursor = getDatabase().rawQuery("SELECT * FROM favorites_movies", null);
        List<Movie> list = new ArrayList<>();
        try {
            while (cursor.moveToNext()) {

                Movie m = new Movie();
                m.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                m.setPoster(cursor.getString(cursor.getColumnIndex("poster")));
                m.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                m.setNote(cursor.getFloat(cursor.getColumnIndex("note")));
                m.setSynopsis(cursor.getString(cursor.getColumnIndex("sinopse")));
                m.setFavorite(cursor.getInt(cursor.getColumnIndex("favorite")) == 1);
                m.setGenresStr(cursor.getString(cursor.getColumnIndex("gen")));

                list.add(m);

            }

            return list;

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            cursor.close();
        }
        return new ArrayList<>();
    }

}
