package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.managers.PopularManager;
import com.example.myapplication.model.Movie;
import com.example.myapplication.services.DownloadImageTask;

import java.util.ArrayList;
import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> implements Filterable {

    private List<Movie> list;
    LayoutInflater layoutInflater;
    ItemClick itemClick;

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults results = new FilterResults();
                List<Movie> filteredList = new ArrayList<>();

                for (Movie movie : list)
                    if (!charSequence.equals(""))
                        if (movie.getTitle().contains(charSequence))
                            filteredList.add(movie);

                if (!charSequence.equals("")) {
                    results.count = filteredList.size();
                    results.values = filteredList;
                } else {
                    results = null;
                }


                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                if (filterResults != null)
                    list = (List<Movie>) filterResults.values;
                else
                    list = PopularManager.getMovieList();

                notifyDataSetChanged();
            }
        };
    }

    public interface ItemClick {
        void onClick(Movie m);
    }

    public void setOnClickListener(ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    public PopularAdapter(Context context, List<Movie> list) {
        this.list = list;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.adapter_popular_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Movie movie = list.get(position);

        holder.textViewNameMovie.setText(movie.getTitle());

        holder.textViewYearMovie.setText(movie.getYear());

        new DownloadImageTask(holder.imagePoster)
                .execute(movie.getPoster());
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        AppCompatImageView imagePoster;
        TextView textViewNameMovie;
        TextView textViewYearMovie;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            imagePoster = itemView.findViewById(R.id.imagePoster);
            textViewNameMovie = itemView.findViewById(R.id.nameMovie);
            textViewYearMovie = itemView.findViewById(R.id.yearMovie);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClick.onClick(list.get(getAdapterPosition()));
        }
    }
}
