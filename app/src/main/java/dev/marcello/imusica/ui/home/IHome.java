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

        void OnGetPostsRequestSuccess(List<PostsModel> list);

        void OnGetPostsRequestFailure(String message);

        void OnCreatePostRequestSuccess(Post post);

        void OnUpdatePostRequestSuccess(Post post);

        void OnDeletePostRequestSuccess();

    }

    interface Presenter extends ITaskListener.Posts {

        void DoCheckUrlRequest();

        void DoGetPostsRequest();

        void DoCreatePostRequest();

        void DoUpdatePostRequest();

        void DoDeletePostRequest();

        void OnDestroy();

    }

    interface Model {

        void DoCheckUrl();

        void DoReadPosts();

        void DoCreatePost();

        void DoUpdatePost();

        void DoDeletePost();

    }

}