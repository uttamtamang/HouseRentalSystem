package com.example.meroghar.Interfaces;

import com.example.meroghar.Models.User;
import com.example.meroghar.ServerResponse.ImageResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UserApi {

    @POST("users/signup")
    Call<Void> signup(@Body User user);

    @POST("users/login")
    Call<Void> login(@Body User user);

    @Multipart
    @POST("/upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part Profileimage);

}
