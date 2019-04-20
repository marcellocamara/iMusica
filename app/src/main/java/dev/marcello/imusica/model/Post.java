package dev.marcello.imusica.model;

import com.google.gson.annotations.SerializedName;

/**
 * Marcello
 * 2019
 */

public class Post {

    @SerializedName("author_fullname")
    private String author;

    @SerializedName("title")
    private String title;

    @SerializedName("ups")
    private int ups;

    @SerializedName("num_comments")
    private int comments;

    @SerializedName("created")
    private long created;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUps() {
        return ups;
    }

    public void setUps(int ups) {
        this.ups = ups;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

}