package com.firstdataproject.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Avdhesh AKhani on 10/26/2016.
 */

public class ApiClient {

    public static final String BASE_URL = "http://finance.google.com/finance/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {


        if (retrofit==null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}