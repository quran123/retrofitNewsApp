package com.example.retrofitnewsapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private NewsAdapter adapter;
    private List<NewsList.Datum> NL;
    private ListNews listNews;
    private NewsList.Datum datum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView) findViewById(R.id.listViewNews);
        rv.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
                DividerItemDecoration.VERTICAL));

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(mLayoutManager);
        NL = new ArrayList<>();

        //calling the method to display the News
        getNews();
    }

    private void getNews() {


        int cacheSize = 10 * 1024 * 1024; // 10 MB
        Cache cache = new Cache(getCacheDir(), cacheSize);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        Api api = retrofit.create(Api.class);

        Call<NewsList> call = api.getNews("ecbccb868f16433988500714e9873369");

        call.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                NewsList newsLists = response.body();
                List<NewsList.Datum> datumsList = newsLists.articles;
                for (int i = 0; i < datumsList.size(); i++) {
                    NewsList.Datum.Datums datumlist = datumsList.get(i).source;
                    datum = new NewsList.Datum(datumsList.get(i).title, datumsList.get(i).author, datumsList.get(i).description, datumsList.get(i).url, datumsList.get(i).urlToImage, datumsList.get(i).publishedAt, datumsList.get(i).content);
                    NL.add(datum);
                }
                adapter = new NewsAdapter(NL, getApplicationContext());
                rv.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "1235" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
