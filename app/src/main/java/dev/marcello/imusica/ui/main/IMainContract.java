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

        void OnDestroy(boolean session);

    }

    interface Model {

        void DoLogout();

        void DoForceLogout();

        Map<String, String> DoGetUserData();

    }

}