package dev.marcello.imusica.ui;

/**
 * Marcello
 * 2019
 */

public interface ITaskListener {

    interface User {

        void OnSuccess();

        void OnFailure(String message);

    }

}