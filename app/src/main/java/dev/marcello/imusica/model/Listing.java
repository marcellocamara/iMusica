package dev.marcello.imusica.model;

import com.google.gson.annotations.SerializedName;

/**
 * Marcello
 * 2019
 */

public class Listing {

    @SerializedName("data")
    private ChildrenModel childrenArray;

    public ChildrenModel getChildrenArray() {
        return childrenArray;
    }

}