package dev.marcello.imusica.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Marcello
 * 2019
 */

public class Post implements Parcelable {

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

    public Post() {
    }

    public Post(String author, String title, int ups, int comments, long created) {
        this.author = author;
        this.title = title;
        this.ups = ups;
        this.comments = comments;
        this.created = created;
    }

    protected Post(Parcel in) {
        author = in.readString();
        title = in.readString();
        ups = in.readInt();
        comments = in.readInt();
        created = in.readLong();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(author);
        dest.writeString(title);
        dest.writeInt(ups);
        dest.writeInt(comments);
        dest.writeLong(created);
    }

}