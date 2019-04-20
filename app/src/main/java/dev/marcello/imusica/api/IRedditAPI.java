package dev.marcello.imusica.api;

import dev.marcello.imusica.model.Listing;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Marcello
 * 2019
 */

public interface IRedditAPI {

    @GET("artificial/hot")
    Call<Listing> Read();

}