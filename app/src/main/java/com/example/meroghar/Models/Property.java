package com.example.meroghar.Models;

import java.util.List;

public class Property {



    private String _id;
    private String image;
    private String title;
    private String owner;
    private String address;
    private String category;
    private String purpose;
    private String description;
    private String price;

//    private List<Facility> facilityList;
//    private List<Room> roomList;


    //Updated DB Collections
    private String facility1;
    private String facility2;
    private String facility3;
    private String facility4;

    private String kitchen;
    private String bedroom;
    private String livingroom;
    private String bathroom;

    public Property(String image, String title, String owner, String address, String category,
                    String purpose, String description, String price, String facility1, String facility2,
                    String facility3, String facility4, String kitchen, String bedroom, String livingroom,
                    String bathroom) {
        this.image = image;
        this.title = title;
        this.owner = owner;
        this.address = address;
        this.category = category;
        this.purpose = purpose;
        this.description = description;
        this.price = price;
        this.facility1 = facility1;
        this.facility2 = facility2;
        this.facility3 = facility3;
        this.facility4 = facility4;
        this.kitchen = kitchen;
        this.bedroom = bedroom;
        this.livingroom = livingroom;
        this.bathroom = bathroom;
    }

    public Property(String _id, String image, String title, String owner, String address, String category,
                    String purpose, String description, String price, String facility1, String facility2,
                    String facility3, String facility4, String kitchen, String bedroom, String livingroom,
                    String bathroom) {
        this._id = _id;
        this.image = image;
        this.title = title;
        this.owner = owner;
        this.address = address;
        this.category = category;
        this.purpose = purpose;
        this.description = description;
        this.price = price;
        this.facility1 = facility1;
        this.facility2 = facility2;
        this.facility3 = facility3;
        this.facility4 = facility4;
        this.kitchen = kitchen;
        this.bedroom = bedroom;
        this.livingroom = livingroom;
        this.bathroom = bathroom;
    }


    public Property(String image, String title, String owner, String address, String category,
                    String purpose, String description, String price) {
        this.image = image;
        this.title = title;
        this.owner = owner;
        this.address = address;
        this.category = category;
        this.purpose = purpose;
        this.description = description;
        this.price = price;
    }

    //CONSTRUCTIOR FOR CRUD
//    public Property(String _id, String image, String title, String owner, String address,
//                    String category, String purpose, String description, String price, List<Facility> facilityList,
//                    List<Room> roomList) {
//        this._id = _id;
//        this.image = image;
//        this.title = title;
//        this.owner = owner;
//        this.address = address;
//        this.category = category;
//        this.purpose = purpose;
//        this.description = description;
//        this.price = price;
//        this.facilityList = facilityList;
//        this.roomList = roomList;
//    }

    //CONSTRUCTIOR FOR POST
//    public Property(String image, String title, String owner, String address, String category,
//                    String purpose, String description, String price, List<Facility> facilityList,
//                    List<Room> roomList) {
//        this.image = image;
//        this.title = title;
//        this.owner = owner;
//        this.address = address;
//        this.category = category;
//        this.purpose = purpose;
//        this.description = description;
//        this.price = price;
//        this.facilityList = facilityList;
//        this.roomList = roomList;
//    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

//    public void setPrice(String price) {
//        this.price = price;
//    }
//
//    public List<Facility> getFacilityList() {
//        return facilityList;
//    }
//
//    public void setFacilityList(List<Facility> facilityList) {
//        this.facilityList = facilityList;
//    }
//
//    public List<Room> getRoomList() {
//        return roomList;
//    }
//
//    public void setRoomList(List<Room> roomList) {
//        this.roomList = roomList;
//    }

    //new getter setter

    public String getFacility1() {
        return facility1;
    }

    public void setFacility1(String facility1) {
        this.facility1 = facility1;
    }

    public String getFacility2() {
        return facility2;
    }

    public void setFacility2(String facility2) {
        this.facility2 = facility2;
    }

    public String getFacility3() {
        return facility3;
    }

    public void setFacility3(String facility3) {
        this.facility3 = facility3;
    }

    public String getFacility4() {
        return facility4;
    }

    public void setFacility4(String facility4) {
        this.facility4 = facility4;
    }

    public String getKitchen() {
        return kitchen;
    }

    public void setKitchen(String kitchen) {
        this.kitchen = kitchen;
    }

    public String getBedroom() {
        return bedroom;
    }

    public void setBedroom(String bedroom) {
        this.bedroom = bedroom;
    }

    public String getLivingroom() {
        return livingroom;
    }

    public void setLivingroom(String livingroom) {
        this.livingroom = livingroom;
    }

    public String getBathroom() {
        return bathroom;
    }

    public void setBathroom(String bathroom) {
        this.bathroom = bathroom;
    }




}
