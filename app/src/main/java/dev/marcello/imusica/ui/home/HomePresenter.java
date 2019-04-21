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

    public HomePresenter(IHome.View view, Context context){
        this.view = view;
        this.model = new PostDAO(context, this);
    }

    @Override
    public void DoCheckUrlRequest() {
        view.ShowProgress();
        model.DoCheckUrl();
    }

    @Override
    public void DoGetPostsRequest() {
        view.ShowProgress();
        model.DoReadPosts();
    }

    @Override
    public void DoCreatePostRequest() {
        view.ShowProgress();
        model.DoCreatePost();
    }

    @Override
    public void DoUpdatePostRequest() {
        view.ShowProgress();
        model.DoUpdatePost();
    }

    @Override
    public void DoDeletePostRequest() {
        view.ShowProgress();
        model.DoDeletePost();
    }

    @Override
    public void OnDestroy() {
        this.view = null;
    }

    @Override
    public void OnReadSuccess(List<PostsModel> list) {
        if (view != null){
            view.HideProgress();
            view.OnGetPostsRequestSuccess(list);
        }
    }

    @Override
    public void OnCreateSuccess(Post post) {
        if (view!=null){
            view.HideProgress();
            view.OnCreatePostRequestSuccess(post);
        }
    }

    @Override
    public void OnUpdateSuccess(Post post) {
        if (view!=null){
            view.HideProgress();
            view.OnUpdatePostRequestSuccess(post);
        }
    }

    @Override
    public void OnDeleteSuccess() {
        if (view!=null){
            view.HideProgress();
            view.OnDeletePostRequestSuccess();
        }
    }

    @Override
    public void OnOkHttpResponse(boolean value) {
        if (view != null){
            view.HideProgress();
            if (value){
                view.OnCheckUrlRequestSuccess();
            }else {
                view.OnCheckUrlRequestFailure();
            }
        }
    }

    @Override
    public void OnFailure(String message) {
        if (view != null){
            view.HideProgress();
            view.OnGetPostsRequestFailure(message);
        }
    }

}