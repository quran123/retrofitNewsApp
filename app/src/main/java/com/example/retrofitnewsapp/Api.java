package com.example.retrofitnewsapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    String BASE_URL = "https://newsapi.org/v2/";

    @GET("top-headlines?country=us")
    Call<NewsList> getNews(@Query("apiKey") String api_key);
}