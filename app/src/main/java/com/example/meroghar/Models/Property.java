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

    private List<Facility> facilityList;
    private List<Room> roomList;

    public Property(String image, String title, String owner, String address, String category, String purpose, String description, String price) {
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
    public Property(String _id, String image, String title, String owner, String address,
                    String category, String purpose, String description, String price, List<Facility> facilityList,
                    List<Room> roomList) {
        this._id = _id;
        this.image = image;
        this.title = title;
        this.owner = owner;
        this.address = address;
        this.category = category;
        this.purpose = purpose;
        this.description = description;
        this.price = price;
        this.facilityList = facilityList;
        this.roomList = roomList;
    }

    //CONSTRUCTIOR FOR POST
    public Property(String image, String title, String owner, String address, String category,
                    String purpose, String description, String price, List<Facility> facilityList,
                    List<Room> roomList) {
        this.image = image;
        this.title = title;
        this.owner = owner;
        this.address = address;
        this.category = category;
        this.purpose = purpose;
        this.description = description;
        this.price = price;
        this.facilityList = facilityList;
        this.roomList = roomList;
    }

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

    public void setPrice(String price) {
        this.price = price;
    }

    public List<Facility> getFacilityList() {
        return facilityList;
    }

    public void setFacilityList(List<Facility> facilityList) {
        this.facilityList = facilityList;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }




}
