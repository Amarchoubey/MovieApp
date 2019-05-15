package com.mahavir.achoubey.retrofitapplication;

import android.app.Application;

import com.mahavir.achoubey.retrofitapplication.daggercomponents.ApiComponent;
import com.mahavir.achoubey.retrofitapplication.daggercomponents.ApiModule;
import com.mahavir.achoubey.retrofitapplication.daggercomponents.AppModule;
import com.mahavir.achoubey.retrofitapplication.daggercomponents.DaggerApiComponent;
import com.mahavir.achoubey.retrofitapplication.helper.Helper;

public class MovieApplication extends Application {

    private ApiComponent mApiComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApiComponent = DaggerApiComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule(Helper.BASE_URL))
                .build();
    }

    public ApiComponent getNetComponent() {
        return mApiComponent;
    }

}
