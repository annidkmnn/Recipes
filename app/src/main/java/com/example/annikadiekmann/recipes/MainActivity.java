package com.example.annikadiekmann.recipes;


import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private ArrayList<Recipe> recipes = new ArrayList<>();
    private RecipeAdapter mSectionsPagerAdapter;
    private static final String TAG = MainActivity.class.getSimpleName();

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new RecipeAdapter(getSupportFragmentManager(),recipes);

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        requestData();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void requestData() {
        FoodApi foodApi = FoodApi.retrofit.create(FoodApi.class);
        Call<RecipeRespond> call = foodApi.getFoodNumber();

        call.enqueue(new Callback<RecipeRespond>() {
            @Override
            public void onResponse(Call<RecipeRespond> call, Response<RecipeRespond> response) {

                if (response.body() != null) {

                    Log.i(TAG, "Response: " + response.toString());
                    recipes.addAll(Arrays.asList(response.body().recipes));
                    mSectionsPagerAdapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onFailure(Call<RecipeRespond> call, Throwable t) {
                Log.i(TAG," "+t.getMessage());

            }
        });


    }


}
