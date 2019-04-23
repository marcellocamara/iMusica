package dev.marcello.imusica.api;

import dev.marcello.imusica.model.Listing;

import dev.marcello.imusica.model.Post;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Marcello
 * 2019
 */

public interface IApiaryApiCRUD {

    @POST("posts")
    Call<Post> Create(@Body Post post);

    @GET("posts")
    Call<Listing> Read();

    @PUT("posts/{created}")
    Call<Post> Update(@Path("created") long created, @Body Post post);

    @DELETE("posts/{created}")
    Call<Void> Delete(@Path("created") long created);

}