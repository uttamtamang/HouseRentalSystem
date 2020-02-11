package com.example.meroghar.Interfaces;

import com.example.meroghar.Models.Facility;
import com.example.meroghar.Models.Property;
import com.example.meroghar.Models.Room;
import com.example.meroghar.ServerResponse.IdResponse;
import com.example.meroghar.ServerResponse.ImageResponse;

import java.util.List;
import java.util.Properties;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface PropertyApi {
    @POST("/properties")
    Call<IdResponse> postProperty(@Header("Authorization") String token, @Body Property property);


    @Multipart
    @POST("/upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part propertyImage);

    @POST("/properties/{pid}/rooms")
    Call<Void> postPropertyRoom(@Header("Authorization") String token, @Path("pid") String pid, @Body Room room);


    @POST("/properties/{pid}/facilities")
    Call<Void> postPropertyFacilities(@Header("Authorization") String token, @Path("pid") String pid, @Body Facility facility);

//    @GET("/properties")
//    Call<List<Property>> property

}
