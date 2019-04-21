package dev.marcello.imusica.ui.main;

import java.util.Map;

import dev.marcello.imusica.ui.IProgress;
import dev.marcello.imusica.ui.ITaskListener;

/**
 * Marcello
 * 2019
 */

public interface IMainContract {

    interface View extends IProgress {

        void OnUserDataRequestSuccess(String name, String email);

        void OnLogoutSuccess();

        void OnLogoutFailure(String message);

    }

    interface Presenter extends ITaskListener.User {

        void OnUserDataRequest();

        void OnLogoutRequest();

        void OnVerifyUserDeleted(boolean value);

        void OnDestroy();

    }

    interface Model {

        void DoLogout();

        Map<String, String> DoGetUserData();

    }

    interface ScreenTitle {

        void SetTitle(String title);

    }

}