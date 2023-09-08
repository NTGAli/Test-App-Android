package com.ntg.testapp.remote

import com.ntg.testapp.model.res.*
import retrofit2.Response
import retrofit2.http.*

interface UnsplashApi {

    @GET("/photos")
    suspend fun getAllImages(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): Response<List<Feed>>


    @GET("/photos/{id}")
    suspend fun getSpecificImage(
        @Path("id") id: String
    ): Response<ImageDetailsRes>


    @GET("/users/{username}")
    suspend fun getUserByUsername(
        @Path("username") username: String
    ): Response<UnsplashUser>

    @GET("/users/{username}/photos")
    suspend fun getUserPhotosByUsername(
        @Path("username") username: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): Response<List<Feed>>

    @GET("/topics")
    suspend fun getTopics(
        @Query("per_page") per_page: Int
    ): Response<List<Topic>>

    @GET("/topics/{id}")
    suspend fun getTopicById(
        @Path("id") topicID: String
    ): Response<PreviewRes>

    @GET("/topics/{id}/photos")
    suspend fun getTopicPhotosById(
        @Path("id") topicID: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): Response<List<Feed>>


    @GET("/photos")
    suspend fun getAllImagesByOrder(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int,
        @Query("order_by") order_by: String
    ): Response<List<Feed>>

    @GET("/search/photos")
    suspend fun searchInImages(
        @Query("query") query: String,
        @Query("page") page: Int
    ): Response<ResultImageRes>

    @GET("/search/users")
    suspend fun searchInUsers(
        @Query("query") query: String
    ): Response<ResultUser>

}