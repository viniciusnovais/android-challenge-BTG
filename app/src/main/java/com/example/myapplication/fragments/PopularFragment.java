package com.example.myapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DetailsActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapters.PopularAdapter;
import com.example.myapplication.managers.FavoriteManager;
import com.example.myapplication.managers.PopularManager;
import com.example.myapplication.model.Movie;

public class PopularFragment extends Fragment {


    public static PopularAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_popular, container, false);

        RecyclerView recyclerView = v.findViewById(R.id.recycleView);
        androidx.appcompat.widget.SearchView searchView = v.findViewById(R.id.searchView);

        LinearLayoutManager llm = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(llm);

        adapter = new PopularAdapter(getContext(), PopularManager.getMovieList());
        recyclerView.setAdapter(adapter);

        adapter.setOnClickListener(new PopularAdapter.ItemClick() {
            @Override
            public void onClick(Movie m) {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                new FavoriteManager(getContext());
                m.setId(FavoriteManager.getIdByTitle(m.getTitle()));
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

        return v;
    }
}
