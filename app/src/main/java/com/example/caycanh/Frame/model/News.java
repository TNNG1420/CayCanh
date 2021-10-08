package com.example.caycanh.Frame.model;

public class News {
    private String id_news;
    private String name_news;
    private String image_news;
    private String descript_news;
    private String link_news;

    public News(String id_news, String name_news, String image_news, String descript_news, String link_news) {
        this.id_news = id_news;
        this.name_news = name_news;
        this.image_news = image_news;
        this.descript_news = descript_news;
        this.link_news = link_news;
    }

    public String getId_news() {
        return id_news;
    }

    public void setId_news(String id_news) {
        this.id_news = id_news;
    }

    public String getName_news() {
        return name_news;
    }

    public void setName_news(String name_news) {
        this.name_news = name_news;
    }

    public String getImage_news() {
        return image_news;
    }

    public void setImage_news(String image_news) {
        this.image_news = image_news;
    }

    public String getDescript_news() {
        return descript_news;
    }

    public void setDescript_news(String descript_news) {
        this.descript_news = descript_news;
    }

    public String getLink_news() {
        return link_news;
    }

    public void setLink_news(String link_news) {
        this.link_news = link_news;
    }
}
