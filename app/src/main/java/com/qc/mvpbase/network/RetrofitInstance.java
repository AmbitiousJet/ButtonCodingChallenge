package com.qc.mvpbase.network;

import android.support.annotation.NonNull;
import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitInstance {

    private ButtonService buttonService;
    private static RetrofitInstance instance = null;


    private RetrofitInstance() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NonNull String message) {
                Log.d("Retrofit", message);
            }
        }));
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://fake-button.herokuapp.com/")
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        buttonService = retrofit.create(ButtonService.class);
    }

    public static RetrofitInstance getInstance() {
        if (instance == null) {
            instance = new RetrofitInstance();
        }
        return instance;
    }

    public ButtonService getButtonService() {
        return buttonService;
    }
}
