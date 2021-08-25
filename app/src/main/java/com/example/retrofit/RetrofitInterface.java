package com.example.retrofit;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface RetrofitInterface {
    String urls="https://jsonplaceholder.typicode.com/";

    @GET("posts")
    Call<List<User1>> getUser();

    @GET("posts/{id}/comments")
    Call<List<User1>> getUser(@Path("id") int userId);


    @POST("posts")
    Call<User1> createUser(@Body User1 user1);

    @POST("posts")
    Call<User1> createUser(@FieldMap Map<String,String> user1);


    @GET("comments")
    Call<User1> getUsers(@Query("postId") int postId);

    @GET("comments")
    Call<List<User1>> getUsers(@Query("postId") int postId1,@Query("postId") int postId2);

    @GET("posts")
    Call<List<User1>> getUsers(@QueryMap Map<String,String> map);

    @Headers({"My-header1: 123","My-header2: 124"})
    @PUT("posts/{id}")
    Call<User1> putpost(@Header("Dynamic-Header") String str, @Path("id") int postid , @Body User1 user);

    @PATCH("posts/{id}")
    Call<User1> patchpost(@Path("id") int postid , @Body User1 user);

}
