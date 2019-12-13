package com.example.myapplication.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DetailsActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapters.FavoritesAdapter;
import com.example.myapplication.managers.FavoriteManager;
import com.example.myapplication.model.Movie;

public class FavoritesFragment extends Fragment {

    public static FavoritesAdapter adapter;
    private RecyclerView recyclerView;
    androidx.appcompat.widget.SearchView searchView;
    private Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getContext() != null)
            context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_popular, container, false);

        recyclerView = v.findViewById(R.id.recycleView);
        searchView = v.findViewById(R.id.searchView);

        LinearLayoutManager llm = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(llm);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        new FavoriteManager(context);
        adapter = new FavoritesAdapter(context, FavoriteManager.listFavorite());
        recyclerView.setAdapter(adapter);

        adapter.setOnClickListener(new FavoritesAdapter.ItemClick() {
            @Override
            public void onClick(Movie m) {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("movie", m);
                startActivity(intent);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}
