package com.example.caycanh.Frame.OOP;

public class Care {
    String id_care;
    String title_care;
    String content_care;
    String image_care;

    public Care() {
    }

    public Care(String id_care, String title_care, String content_care, String image_care) {
        this.id_care = id_care;
        this.title_care = title_care;
        this.content_care = content_care;
        this.image_care = image_care;
    }

    public String getId_care() {
        return id_care;
    }

    public void setId_care(String id_care) {
        this.id_care = id_care;
    }

    public String getTitle_care() {
        return title_care;
    }

    public void setTitle_care(String title_care) {
        this.title_care = title_care;
    }

    public String getContent_care() {
        return content_care;
    }

    public void setContent_care(String content_care) {
        this.content_care = content_care;
    }

    public String getImage_care() {
        return image_care;
    }

    public void setImage_care(String image_care) {
        this.image_care = image_care;
    }
}
