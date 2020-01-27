package com.example.meroghar.ServerResponse;

public class ImageResponse {
    public String getProfileimage() {
        return profileimage;
    }

    public void setProfileimage(String profileimage) {
        this.profileimage = profileimage;
    }

    private String profileimage;

    public ImageResponse(String profileimage) {
        this.profileimage = profileimage;
    }

}
