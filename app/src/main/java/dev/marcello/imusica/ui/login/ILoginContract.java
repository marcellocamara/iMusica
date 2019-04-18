package dev.marcello.imusica.ui.login;

import dev.marcello.imusica.ui.IProgress;
import dev.marcello.imusica.ui.ITaskListener;

/**
 * Marcello
 * 2019
 */

public interface ILoginContract {

    interface View extends IProgress {

        void OnEmailEmpty();

        void OnPasswordEmpty();

        void OnEmailInvalid();

        void OnLoginSuccess();

        void OnLoginFailure(String message);

    }

    interface Presenter extends ITaskListener.User {

        void OnLoginRequest(String email, String password);

        void OnCheckLoggedIn();

        void OnDestroy();

    }

    interface Model {

        void DoLogin(String email, String password);

        void DoCheckLoggedIn();

    }

}