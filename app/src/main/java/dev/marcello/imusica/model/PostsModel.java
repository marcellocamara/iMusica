package dev.marcello.imusica.model;

import com.google.gson.annotations.SerializedName;

/**
 * Marcello
 * 2019
 */

public class PostsModel {

    @SerializedName("data")
    private Post post;

    public PostsModel(){
    }

    public PostsModel(Post post){
        this.post = post;
    }

    public Post getPost() {
        return post;
    }

}