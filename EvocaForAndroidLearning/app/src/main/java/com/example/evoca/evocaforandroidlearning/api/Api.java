package com.example.evoca.evocaforandroidlearning.api;

import com.example.evoca.evocaforandroidlearning.Model.Group;
import com.example.evoca.evocaforandroidlearning.Model.ServerResponse;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Evoca-PC on 6/24/2016.
 */
public interface Api {
    @FormUrlEncoded
    @POST("/login")
    void postLoginData(@Field("email") String email,
                  @Field("password") String password,
                  Callback<ServerResponse> serverResponseCallback);


    @FormUrlEncoded
    @POST("/registration")
    void postRegistrationData(@Field("email") String email,
                              @Field("password") String password,
                              @Field("first_name") String firstName,
                              @Field("last_name") String lastName,
                              Callback<ServerResponse> serverResponseCallback);

    @FormUrlEncoded
    @POST("/content")
    void postContent(@Field("postTitle") String postTitle,
                     Callback<List<ServerResponse>> serverResponseCallback);

    @FormUrlEncoded
    @POST("/content")
    void postContentGroup(@Field("postTitle") String postTitle,
                          Callback<List<Group>> serverResponseCallback);


}
