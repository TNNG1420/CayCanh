package com.example.caycanh.Frame.OOP;

public class Product {
    String msp;
    String name;
    String descript;
    boolean status;
    String images;
    int soLuong = 1;
    int price;

    public Product(String msp, String name, String descript, boolean status, String images, int price) {
        this.msp = msp;
        this.name = name;
        this.descript = descript;
        this.status = status;
        this.images = images;
        this.price = price;
    }

    public Product() {
    }

    public String getMsp() {
        return msp;
    }

    public void setMsp(String msp) {
        this.msp = msp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
