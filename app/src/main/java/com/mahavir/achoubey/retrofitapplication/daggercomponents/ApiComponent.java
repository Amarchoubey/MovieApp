package com.mahavir.achoubey.retrofitapplication.daggercomponents;

import com.mahavir.achoubey.retrofitapplication.view.MainActivity;
import com.mahavir.achoubey.retrofitapplication.viewModel.MovieViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface ApiComponent {
    void inject(MovieViewModel movieViewModel);
}
