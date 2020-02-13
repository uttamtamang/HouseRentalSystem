package com.example.werablemodule.Interfaces;

import com.example.werablemodule.Models.User;
import com.example.werablemodule.ServerResponse.ImageResponse;
import com.example.werablemodule.ServerResponse.SignUpResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface UserApi {

    @POST("users/signup")
    Call<SignUpResponse> signup(@Body User user);

    @POST("users/login")
    Call<SignUpResponse> login(@Body User user);

    @Multipart
    @POST("/upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part profileimage);

    @GET("users/myProfile")
    Call<User> retrievUserdetail(@Header("Authorization") String token);

    @PUT("users/myProfile")
    Call<User> updateUserProfile(@Header("Authorization") String token, @Body User user);

}
