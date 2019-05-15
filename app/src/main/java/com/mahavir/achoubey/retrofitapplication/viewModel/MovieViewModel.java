package com.mahavir.achoubey.retrofitapplication.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.mahavir.achoubey.retrofitapplication.model.Movie;
import com.mahavir.achoubey.retrofitapplication.model.MoviesResponse;
import com.mahavir.achoubey.retrofitapplication.rest.ApiInterface;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MovieViewModel extends ViewModel {

    ApiInterface apiService;

    @Inject
    Retrofit retrofit;

    // TODO - insert your themoviedb.org API KEY here
    private final static String API_KEY = "b0719d3c4e67c6f26854805a8116b3de";

    //this is the data that we will fetch asynchronously
    private MutableLiveData<List<Movie>> heroList;

    //we will call this method to get the data
    public LiveData<List<Movie>> getMovies() {
        //if the list is null
        if (heroList == null) {
            heroList = new MutableLiveData<List<Movie>>();
            //we will load it asynchronously from server in this method
            loadMoview();
        }

        //finally we will return the list
        return heroList;
    }

    public void loadMoview() {
        //apiService = ApiClient.getClient().create(ApiInterface.class);
        apiService = retrofit.create(ApiInterface.class);

        Call<MoviesResponse> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                int statusCode = response.code();
                Log.e("Status code", String.valueOf(statusCode));
                heroList.setValue(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MoviesResponse>call, Throwable t) {
                // Log error here since request failed
                Log.e("Call Failed", t.toString());
            }
        });
    }

}
