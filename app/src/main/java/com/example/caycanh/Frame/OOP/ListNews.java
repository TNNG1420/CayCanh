package com.example.caycanh.Frame.OOP;

import java.util.List;

public class ListNews {
    List<News> news;

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public ListNews(List<News> news) {
        this.news = news;
    }
}
