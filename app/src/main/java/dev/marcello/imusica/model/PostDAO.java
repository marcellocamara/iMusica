package dev.marcello.imusica.model;

import android.content.Context;

import java.io.IOException;

import dev.marcello.imusica.R;
import dev.marcello.imusica.api.IRedditApiCRUD;
import dev.marcello.imusica.api.RedditApi;
import dev.marcello.imusica.ui.ITaskListener;
import dev.marcello.imusica.ui.home.IHome;
import dev.marcello.imusica.util.CheckUrlAsyncTask;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static dev.marcello.imusica.util.Constants.REDDIT_FULL_URL;

/**
 * Marcello
 * 2019
 */

public class PostDAO implements IHome.Model {

    private IRedditApiCRUD api = RedditApi.getAPIClient().create(IRedditApiCRUD.class);
    private Context context;
    private ITaskListener.Posts taskListener;

    public PostDAO(Context context, ITaskListener.Posts taskListener){
        this.context = context;
        this.taskListener = taskListener;
    }

    @Override
    public void DoCheckUrl() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(REDDIT_FULL_URL)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                new CheckUrlAsyncTask(taskListener, false).execute();
            }
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()){
                    new CheckUrlAsyncTask(taskListener, true).execute();
                }else {
                    new CheckUrlAsyncTask(taskListener, false).execute();
                }
            }
        });
    }

    @Override
    public void DoReadPosts() {
        retrofit2.Call<Listing> posts = api.Read();
        posts.enqueue(new retrofit2.Callback<Listing>() {
            @Override
            public void onResponse(retrofit2.Call<Listing> call, retrofit2.Response<Listing> response) {
                if (response.isSuccessful()){
                    taskListener.OnReadSuccess(response.body().getChildrenArray().getChildrenList());
                }else {
                    taskListener.OnFailure(context.getString(R.string.retrofit_failure));
                }
            }
            @Override
            public void onFailure(retrofit2.Call<Listing> call, Throwable t) {
                taskListener.OnFailure(context.getString(R.string.failure));
            }
        });
    }

    @Override
    public void DoCreatePost() {
        Post post = new Post("New User","iMusica", 150, 150,1555661100);
        retrofit2.Call<Post> call = api.Create(post);
        call.enqueue(new retrofit2.Callback<Post>() {
            @Override
            public void onResponse(retrofit2.Call<Post> call, retrofit2.Response<Post> response) {
                if (response.isSuccessful()){
                    Post result = response.body();
                    taskListener.OnCreateSuccess(result);
                }else {
                    taskListener.OnFailure(context.getString(R.string.retrofit_failure));
                }
            }
            @Override
            public void onFailure(retrofit2.Call<Post> call, Throwable t) {
                taskListener.OnFailure(context.getString(R.string.failure));
            }
        });
    }

    @Override
    public void DoUpdatePost() {
        //simulating an object to update while it's not finished
        Post post = new Post("Author","iMusica", 150, 150,1555661100);
        retrofit2.Call<Post> call = api.Update(post.getCreated(), post);
        call.enqueue(new retrofit2.Callback<Post>() {
            @Override
            public void onResponse(retrofit2.Call<Post> call, retrofit2.Response<Post> response) {
                if (response.isSuccessful()){
                    Post result = response.body();
                    taskListener.OnUpdateSuccess(result);
                }else {
                    taskListener.OnFailure(context.getString(R.string.retrofit_failure));
                }
            }
            @Override
            public void onFailure(retrofit2.Call<Post> call, Throwable t) {
                taskListener.OnFailure(context.getString(R.string.failure));
            }
        });
    }

    @Override
    public void DoDeletePost() {
        //simulating an object to update while it's not finished
        Post post = new Post("Author","iMusica", 150, 150,1555661100);
        retrofit2.Call<Void> call = api.Delete(post.getCreated());
        call.enqueue(new retrofit2.Callback<Void>() {
            @Override
            public void onResponse(retrofit2.Call<Void> call, retrofit2.Response<Void> response) {
                if (response.isSuccessful()){
                    taskListener.OnDeleteSuccess();
                }else {
                    taskListener.OnFailure(context.getString(R.string.retrofit_failure));
                }
            }
            @Override
            public void onFailure(retrofit2.Call<Void> call, Throwable t) {
                taskListener.OnFailure(context.getString(R.string.failure));
            }
        });
    }

}