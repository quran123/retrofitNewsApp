package com.example.retrofitnewsapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {


    private static int currentPosition = 0;
    private List<NewsList.Datum> newsList;
    private Context context;

    public NewsAdapter(List<NewsList.Datum> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_news, parent, false);
        return new NewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final NewsViewHolder holder, final int position) {
        NewsList.Datum news = newsList.get(position);
        holder.title.setText(news.title);
        holder.author.setText("-" + news.author);
        holder.description.setText(news.description);
        holder.publishedAt.setText(news.publishedAt);
        holder.content.setText(news.content);
        holder.url.setText(news.url);
        Glide.with(context).load(news.urlToImage).into(holder.urlToImage);

        holder.linearLayout.setVisibility(View.GONE);

        //if the position is equals to the item position which is to be expanded
        if (currentPosition == position) {
            //creating an animation
            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);

            //toggling visibility
            holder.linearLayout.setVisibility(View.VISIBLE);

            //adding sliding effect 
            holder.linearLayout.startAnimation(slideDown);
        }

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //getting the position of the item to expand it
                currentPosition = position;
                Log.e("2", "onClick: " + position);

                //reloding the list
                notifyDataSetChanged();
            }
        });
        holder.url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, web.class);
                i.putExtra("url", news.url);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);


            }
        });

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView title, author, description, publishedAt, content, url;
        ImageView urlToImage;
        LinearLayout linearLayout;

        NewsViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            author = (TextView) itemView.findViewById(R.id.author);
            description = (TextView) itemView.findViewById(R.id.description);
            publishedAt = (TextView) itemView.findViewById(R.id.publishedAt);
            content = (TextView) itemView.findViewById(R.id.content);
            url = (TextView) itemView.findViewById(R.id.url);
            urlToImage = (ImageView) itemView.findViewById(R.id.urlToImage);
            url.setPaintFlags(url.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }
    }
}