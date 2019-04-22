package dev.marcello.imusica.ui.home;

import android.content.Context;

import java.util.List;

import dev.marcello.imusica.model.Post;
import dev.marcello.imusica.model.PostDAO;
import dev.marcello.imusica.model.PostsModel;

/**
 * Marcello
 * 2019
 */

public class HomePresenter implements IHome.Presenter {

    private IHome.View view;
    private IHome.Model model;
    private String created_title = "", created_author = "";

    public HomePresenter(IHome.View view, Context context) {
        this.view = view;
        this.model = new PostDAO(context, this);
    }

    @Override
    public void DoCheckUrlRequest() {
        view.ShowProgress();
        model.DoCheckUrl();
    }

    @Override
    public void DoGetUserNameRequest() {
        view.DoGetUserNameRequestSuccess(model.DoGetUserName());
    }

    @Override
    public void DoGetPostsRequest() {
        view.ShowProgress();
        model.DoReadPosts();
    }

    @Override
    public void DoCreatePostRequest(String title, String author, long created) {
        if (title.isEmpty()){
            view.OnCreatePostRequestFailure();
        }else {
            view.ShowProgress();
            this.created_author = author;
            this.created_title = title;
            model.DoCreatePost(title, author, created);
        }
    }

    @Override
    public void DoUpdatePostRequest(String postTitle, String newTitle, String author, long created, int ups, int comments) {
        if (newTitle.isEmpty()) {
            view.OnUpdatePostRequestFailure();
        } else if (postTitle.equals(newTitle)) {
            view.OnUpdatePostRequestNoChanges();
        } else {
            view.ShowProgress();
            model.DoUpdatePost(newTitle, author, created, ups, comments);
        }
    }

    @Override
    public void DoDeletePostRequest(long created) {
        view.ShowProgress();
        model.DoDeletePost(created);
    }

    @Override
    public void OnDestroy() {
        this.view = null;
    }

    @Override
    public void OnReadSuccess(List<PostsModel> list) {
        if (view != null) {
            view.HideProgress();
            view.OnGetPostsRequestSuccess(list);
        }
    }

    @Override
    public void OnCreateSuccess(Post post) {
        if (view != null) {
            view.HideProgress();
            //The mocked API always returns the same created post as json response because it's a fake API
            post.setTitle(created_title);
            post.setAuthor(created_author);
            view.OnCreatePostRequestSuccess(post);
        }
    }

    @Override
    public void OnUpdateSuccess(Post post) {
        if (view != null) {
            view.HideProgress();
            //The mocked API always returns the same updated post as json response because it's a fake API
            view.OnUpdatePostRequestSuccess(post);
        }
    }

    @Override
    public void OnDeleteSuccess() {
        if (view != null) {
            view.HideProgress();
            //The mocked API don't real delete the post because it's a fake API
            view.OnDeletePostRequestSuccess();
        }
    }

    @Override
    public void OnOkHttpResponse(boolean value) {
        if (view != null) {
            view.HideProgress();
            if (value) {
                view.OnCheckUrlRequestSuccess();
            } else {
                view.OnCheckUrlRequestFailure();
            }
        }
    }

    @Override
    public void OnFailure(String message) {
        if (view != null) {
            view.HideProgress();
            view.OnGetPostsRequestFailure(message);
        }
    }

}