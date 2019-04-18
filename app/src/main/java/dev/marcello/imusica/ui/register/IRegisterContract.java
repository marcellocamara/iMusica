package dev.marcello.imusica.ui.register;

import java.util.Map;

import dev.marcello.imusica.ui.IProgress;
import dev.marcello.imusica.ui.ITaskListener;

/**
 * Marcello
 * 2019
 */

public interface IRegisterContract {

    interface View extends IProgress {

        void OnEditMode(String email, String name, String password);

        void OnEmailInvalid();

        void OnEmailEmpty();

        void OnNameEmpty();

        void OnPassword1Empty();

        void OnPassword2Empty();

        void OnPasswordNotMatch();

        void OnRegisterSuccess();

        void OnRegisterFailure(String message);

        void OnEditRequestFailure();

        void OnAccountDeleted();

    }

    interface Presenter extends ITaskListener.User {

        void OnRegisterRequest(String email, String name, String password1, String password2);

        void OnEditRequest(String email, String name, String password1, String password2);

        void OnDeleteRequest(String email);

        void OnVerifyEditMode(boolean isEditMode);

    }

    interface Model {

        void DoRegister(String email, String name, String password);

        void DoUpdate(Map<String, String> user);

        void DoDelete(String email);

        Map<String, String> DoGetUserData();

    }

}