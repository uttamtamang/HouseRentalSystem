package com.example.meroghar.Models;

public class Wishlist {

    private String title;
    private String price;
    private String location;
    private int image;


    public Wishlist(String title, String price, String location, int image) {
        this.title = title;
        this.price = price;
        this.location = location;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }




}
