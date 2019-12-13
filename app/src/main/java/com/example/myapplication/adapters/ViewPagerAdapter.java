package com.example.myapplication.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myapplication.R;
import com.example.myapplication.fragments.FavoritesFragment;
import com.example.myapplication.fragments.PopularFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    public ViewPagerAdapter(Context context, @NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {


        switch (position) {
            case 0:
                return new PopularFragment();
            case 1:
                return new FavoritesFragment();
        }
        return new PopularFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return context.getString(R.string.popular);
            case 1:
                return context.getString(R.string.favorites);
        }

        return null;
    }
}
