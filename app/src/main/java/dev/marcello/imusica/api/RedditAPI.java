package dev.marcello.imusica.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static dev.marcello.imusica.util.Constants.REDDIT_BASE_URL;

/**
 * Marcello
 * 2019
 */

public class RedditAPI {

    private static Retrofit api;

    public static Retrofit getAPIClient() {

        if (api == null) {
            api = new Retrofit.Builder()
                    .baseUrl(REDDIT_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return api;
    }

}