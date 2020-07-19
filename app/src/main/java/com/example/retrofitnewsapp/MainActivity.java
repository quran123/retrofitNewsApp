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

    //    int cacheSize = 10 * 1024 * 1024; // 10 MB
//    Cache cache = new Cache(getApplicationContext().getCacheDir(), cacheSize);
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

    //    static OkCacheControl.NetworkMonitor networkMonitor=new
//            OkCacheControl.NetworkMonitor() {
//                @Override
//                public boolean isOnline() {
//                    return isInternetAvailable();
//                }
//            };
//    OkHttpClient okHttpClient = OkCacheControl.on(new OkHttpClient.Builder())
//            .overrideServerCachePolicy(1, MINUTES)
//            .forceCacheWhenOffline(networkMonitor)
//            .apply() // return to the OkHttpClient.Builder instance
//            //.addInterceptor(provideHttpLoggingInterceptor())
//            .cache(cache)
//            .build();
    private void getNews() {


//        OkHttpClient client = new OkHttpClient();
//
//        client.interceptors().add(new Interceptor() {
//            @Override
//            public Response intercept(Interceptor.Chain chain) throws IOException {
//                Request original = chain.request();
//                Request.Builder requestBuilder = original.newBuilder()
//                .addHeader("apiKey","ecbccb868f16433988500714e9873369")
//                       // .header("Authorization", token)
//                        .method(original.method(), original.body());
//
//                Request request = requestBuilder.build();
////                HttpUrl url = request.url().newBuilder().addQueryParameter("apiKey","ecbccb868f16433988500714e9873369").build();
////                request = request.newBuilder().url(url).build();
//                return chain.proceed(request);
//            }
//        });        Retrofit retrofit=new Retrofit.Builder().baseUrl(Api.BASE_URL)
//                .client(client).addConverterFactory(GsonConverterFactory.create())
//                .build();
        int cacheSize = 10 * 1024 * 1024; // 10 MB
        Cache cache = new Cache(getCacheDir(), cacheSize);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .client(okHttpClient)
                .build();

        Api api = retrofit.create(Api.class);

        Call<NewsList> call = api.getNews("ecbccb868f16433988500714e9873369");

        call.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                NewsList newsLists = response.body();
                Toast.makeText(getApplicationContext(), "" + newsLists, Toast.LENGTH_LONG).show();
                List<NewsList.Datum> datumsList = newsLists.articles;
                for (int i = 0; i < datumsList.size(); i++) {
                    NewsList.Datum.Datums datumlist = datumsList.get(i).source;
                    // listNews=new ListNews(datumsList.get(i).title,datumsList.get(i).author,datumsList.get(i).description,datumsList.get(i).url,datumsList.get(i).urlToImage,datumsList.get(i).publishedAt,datumsList.get(i).content);
                    datum = new NewsList.Datum(datumsList.get(i).title, datumsList.get(i).author, datumsList.get(i).description, datumsList.get(i).url, datumsList.get(i).urlToImage, datumsList.get(i).publishedAt, datumsList.get(i).content);
                    NL.add(datum);
                    //                listNews.add(newsLists.articles.get(i));
                    Toast.makeText(getApplicationContext(), "" + newsLists.articles.get(i), Toast.LENGTH_LONG).show();
                }
                adapter = new NewsAdapter(NL, getApplicationContext());
                rv.setAdapter(adapter);
                // adapter.setOnItemClickListener(onItemClickListener);

                ////                List<NewsList.Datum.Datums> datumList = datumsList.source;
//
//                //List<NewsList.Datum> datumList = newsLists.articles;
//
//                //Creating an String array for the ListView
//                String[] News = new String[datumsList.size()];
//
//                //looping through all the News and inserting the names inside the string array
//                for (int i = 0; i < datumsList.size(); i++) {
//                    if(datumsList.get(i).author!=null)
//                        if(!datumsList.get(i).author.equalsIgnoreCase("")){
//                    News[i] = datumsList.get(i).author;
//                   Toast.makeText(getApplicationContext(),i+""+datumsList.get(i).author,Toast.LENGTH_LONG).show();
//                }}
//                for (NewsList.Datum datum : datumsList) {
//                    Toast.makeText(getApplicationContext(), "23"+datum.getAuthor(), Toast.LENGTH_SHORT).show();
//                }
//
//                //displaying the string array into listview
//          //      listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, News));

            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "1235" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
