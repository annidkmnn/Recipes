package com.example.annikadiekmann.recipes;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class RecipeAdapter extends FragmentPagerAdapter

    {

        private ArrayList<Recipe> recipes;

    public RecipeAdapter(FragmentManager fragmentManager, ArrayList<Recipe> recipes) {
            super(fragmentManager);
            this.recipes = recipes;
        }


        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return RecipeFragment.newInstance(recipes.get(position));
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return recipes.size();
        }

    }