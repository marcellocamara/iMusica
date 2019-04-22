package dev.marcello.imusica.ui.home;

import java.util.List;

import dev.marcello.imusica.model.Post;
import dev.marcello.imusica.model.PostsModel;
import dev.marcello.imusica.ui.IProgress;
import dev.marcello.imusica.ui.ITaskListener;

/**
 * Marcello
 * 2019
 */

public interface IHome {

    interface View extends IProgress {

        void OnCheckUrlRequestSuccess();

        void OnCheckUrlRequestFailure();

        void DoGetUserNameRequestSuccess(String name);

        void OnGetPostsRequestSuccess(List<PostsModel> list);

        void OnGetPostsRequestFailure(String message);

        void OnCreatePostRequestFailure();

        void OnCreatePostRequestSuccess(Post post);

        void OnUpdatePostRequestSuccess(Post post);

        void OnUpdatePostRequestNoChanges();

        void OnUpdatePostRequestFailure();

        void OnDeletePostRequestSuccess();

    }

    interface Presenter extends ITaskListener.Posts {

        void DoCheckUrlRequest();

        void DoGetUserNameRequest();

        void DoGetPostsRequest();

        void DoCreatePostRequest(String title, String author, long created);

        void DoUpdatePostRequest(String postTitle, String newTitle, String author, long created, int ups, int comments);

        void DoDeletePostRequest(long created);

        void OnDestroy();

    }

    interface Model {

        void DoCheckUrl();

        String DoGetUserName();

        void DoReadPosts();

        void DoCreatePost(String title, String author, long created);

        void DoUpdatePost(String title, String author, long created, int ups, int comments);

        void DoDeletePost(long created);

    }

}