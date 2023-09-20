package com.example.newsapp.retrofit;

import com.example.newsapp.Model.FirstPage;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("top-headlines")
    Call<FirstPage> getFirstPage(
            @Query("country") String country,
            @Query("apiKey") String apiKey
    );

    /*@GET("everything")  // for search button
    Call<FirstPage> getSpecificData(
            @Query("q") String query,
            @Query("apiKey") String apiKey
    );
*/
}


// interface for API call

