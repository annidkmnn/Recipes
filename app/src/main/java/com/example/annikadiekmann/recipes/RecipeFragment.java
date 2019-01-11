package com.example.annikadiekmann.recipes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class RecipeFragment extends Fragment {
    private static final String ARG_DATA = "data";

    private Recipe mData;

    public static RecipeFragment newInstance(Recipe recipe) {
        RecipeFragment fragment = new RecipeFragment();
        Bundle args = new Bundle();

        args.putParcelable(ARG_DATA, recipe);


        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mData = getArguments().getParcelable(ARG_DATA);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        TextView textView = rootView.findViewById(R.id.recipe_name);
        ImageView imageView = rootView.findViewById(R.id.recipe_image);
        textView.setText(mData.getName());

        Log.i("recipedata",mData.getImage());

        Glide.with(this)
                .load(mData.getImage())
                .into(imageView);

        return rootView;
    }
}
