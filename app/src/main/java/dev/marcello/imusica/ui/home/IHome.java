package dev.marcello.imusica.ui.home;

import java.util.List;

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

    }

    interface Presenter extends ITaskListener.Post {

        void DoCheckUrlRequest();

        void DoGetPostsRequest();

        void OnDestroy();

    }

    interface Model {

        void DoCheckUrl();

        void DoGetPosts();

    }

}