package dev.marcello.imusica.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Marcello
 * 2019
 */

public class ChildrenModel {

    @SerializedName("children")
    private List<PostsModel> childrenList;

    public List<PostsModel> getChildrenList() {
        return childrenList;
    }

}