package com.example.meroghar.Models;

public class User {

    private String fullName;
    private String address;
    private String phone;
    private String email;
    private String password;
    private String profilePicture;

    public User(String fullName, String address, String phone, String email, String password, String profilePicture) {
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.profilePicture = profilePicture;
    }

//    public User(String fullName, String address, String phone, String email, String password) {
//        this.fullName = fullName;
//        this.address = address;
//        this.phone = phone;
//        this.email = email;
//        this.password = password;
//    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }


}
