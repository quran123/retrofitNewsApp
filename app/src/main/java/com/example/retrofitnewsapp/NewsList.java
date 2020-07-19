package com.example.retrofitnewsapp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class NewsList {
    public List<NewsList.Datum> articles = new ArrayList();
    String status;
    int totalResults;

    public NewsList() {

    }

    public static class Datum {

        //  @SerializedName("source")
        public NewsList.Datum.Datums source = new NewsList.Datum.Datums();
        @SerializedName("author")
        public String author;
        @SerializedName("title")
        public String title;
        @SerializedName("description")
        public String description;
        @SerializedName("url")
        public String url;
        @SerializedName("urlToImage")
        public String urlToImage;
        @SerializedName("publishedAt")
        public String publishedAt;
        @SerializedName("content")
        public String content;

        public Datum(String author, String title, String description, String url, String urlToImage, String publishedAt, String content) {
            this.author = author;
            this.title = title;
            this.description = description;
            this.url = url;
            this.urlToImage = urlToImage;
            this.publishedAt = publishedAt;
            this.content = content;
        }

        public String getAuthor() {
            return author;
        }

        //        public Integer source;
        // NewsList.Datum.Datums source = gson.fromJson(reader, NewsList.Datum.Datums.class);
        public class Datums {

            @SerializedName("id")
            public String id;
            @SerializedName("name")
            public String name;
        }
    }


//
//    public String getTitle() {
//        return title;
//    }
//    public String getDescription() {
//        return description;
//    }
//    public String getUrl() {
//        return url;
//    }
//    public String getUrlToImage() {
//        return urlToImage;
//    }
//    public String getPublishedAt() {
//        return publishedAt;
//    }
//    public String getContent() {
//        return content;
//    }
//


}
