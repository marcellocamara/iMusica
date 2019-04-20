package dev.marcello.imusica.model;

import android.content.Context;

import java.io.IOException;

import dev.marcello.imusica.R;
import dev.marcello.imusica.api.IRedditAPI;
import dev.marcello.imusica.api.RedditAPI;
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

    private Context context;
    private ITaskListener.Post taskListener;

    public PostDAO(Context context, ITaskListener.Post taskListener){
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
    public void DoGetPosts() {
        IRedditAPI api = RedditAPI.getAPIClient().create(IRedditAPI.class);
        retrofit2.Call<Listing> posts = api.Read();
        posts.enqueue(new retrofit2.Callback<Listing>() {
            @Override
            public void onResponse(retrofit2.Call<Listing> call, retrofit2.Response<Listing> response) {
                if (response.isSuccessful()){
                    taskListener.OnSuccess(response.body().getChildrenArray().getChildrenList());
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

}