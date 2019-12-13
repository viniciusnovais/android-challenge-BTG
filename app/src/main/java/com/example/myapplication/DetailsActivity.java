package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

import com.example.myapplication.managers.FavoriteManager;
import com.example.myapplication.managers.GenresManager;
import com.example.myapplication.model.Genres;
import com.example.myapplication.model.Movie;
import com.example.myapplication.services.DownloadImageTask;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.IllegalFormatCodePointException;
import java.util.Locale;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        final Movie m = getIntent().getParcelableExtra("movie");

        if (m == null)
            return;

        AppCompatImageView imageView = findViewById(R.id.imagePoster);
        TextView textViewTitle = findViewById(R.id.title);
        TextView textViewSinope = findViewById(R.id.sinopse);
        TextView textViewNotes = findViewById(R.id.notes);
        TextView textViewGeneros = findViewById(R.id.gene);
        final FloatingActionButton fab = findViewById(R.id.fab);

        textViewTitle.setText(m.getTitle());
        textViewSinope.setText(m.getSynopsis());
        textViewNotes.setText(String.format(Locale.getDefault(), "%.2f", m.getNote()));
        new DownloadImageTask(imageView).execute(m.getPoster());
        textViewGeneros.setText(GenresManager.genres(m).equals("") ? "-" : GenresManager.genres(m));
        m.setGenresStr(GenresManager.genres(m).equals("") ? "-" : GenresManager.genres(m));

        if (m.getId() > 0) {
            fab.setImageResource(R.drawable.ic_star_yellow);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FavoriteManager(DetailsActivity.this);

                if (m.getId() > 0) {
                    fab.setImageResource(R.drawable.ic_star_gray);
                    FavoriteManager.delete(m.getId());
                    m.setId(0);
                } else {
                    fab.setImageResource(R.drawable.ic_star_yellow);
                    m.setId((int) FavoriteManager.addFavoriteMovie(m));
                }
            }
        });


    }
}
