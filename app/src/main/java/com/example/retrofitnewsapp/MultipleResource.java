package com.example.retrofitnewsapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MultipleResource {
    public List<Datum> articles = null;
    String status;
    String totalResults;

    public class Datum {

        @SerializedName("source")
        public List<Datums> source = null;
        @SerializedName("author")
        private String author;
        @SerializedName("title")
        private String title;
        @SerializedName("description")
        private String description;
        @SerializedName("url")
        private String url;
        @SerializedName("urlToImage")
        private String urlToImage;
        @SerializedName("publishedAt")
        private String publishedAt;
        @SerializedName("content")
        private String content;

        public class Datums {

            @SerializedName("id")
            public Integer id;
            @SerializedName("name")
            public String name;
        }
    }
}
