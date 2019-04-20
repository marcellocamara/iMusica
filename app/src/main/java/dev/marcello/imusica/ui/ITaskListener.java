package dev.marcello.imusica.ui;

import java.util.List;

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

    interface Post {

        void OnSuccess(List<PostsModel> list);

        void OnResponse(boolean value);

        void OnFailure(String message);

    }

}