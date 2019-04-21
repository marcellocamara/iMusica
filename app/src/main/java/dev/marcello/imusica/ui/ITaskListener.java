package dev.marcello.imusica.ui;

import java.util.List;

import dev.marcello.imusica.model.Post;
import dev.marcello.imusica.model.PostsModel;

/**
 * Marcello
 * 2019
 */

public interface ITaskListener {

    interface User {

        void OnSuccess();

        void OnFailure(String message);

    }

    interface Posts {

        void OnReadSuccess(List<PostsModel> list);

        void OnCreateSuccess(Post post);

        void OnUpdateSuccess(Post post);

        void OnDeleteSuccess();

        void OnOkHttpResponse(boolean value);

        void OnFailure(String message);

    }

}