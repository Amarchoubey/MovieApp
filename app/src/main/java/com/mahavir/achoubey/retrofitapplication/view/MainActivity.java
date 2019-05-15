package com.mahavir.achoubey.retrofitapplication.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.mahavir.achoubey.retrofitapplication.MovieApplication;
import com.mahavir.achoubey.retrofitapplication.R;
import com.mahavir.achoubey.retrofitapplication.adapter.MoviesAdapter;
import com.mahavir.achoubey.retrofitapplication.model.Movie;
import com.mahavir.achoubey.retrofitapplication.viewModel.MovieViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    //API key here
    private final static String API_KEY = "b0719d3c4e67c6f26854805a8116b3de";

    //Declare view model
    MovieViewModel movieViewModel;

    //Declare recycler view here
    RecyclerView recyclerView;
    MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.movies_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
            return;
        }

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

        ((MovieApplication) getApplication()).getNetComponent().inject(movieViewModel);

        movieViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movieList) {
                moviesAdapter = new MoviesAdapter(getApplicationContext(), R.layout.list_item_movie, movieList);
                recyclerView.setAdapter(moviesAdapter);

                Log.d(TAG, "Number of movies received: " + movieList.size());
            }
        });

    }
}
