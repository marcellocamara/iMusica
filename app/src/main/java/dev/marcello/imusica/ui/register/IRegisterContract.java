package dev.marcello.imusica.ui.register;

import dev.marcello.imusica.ui.IProgress;
import dev.marcello.imusica.ui.ITaskListener;

/**
 * Marcello
 * 2019
 */

public interface IRegisterContract {

    interface View extends IProgress {

        void OnEmailInvalid();

        void OnEmailEmpty();

        void OnNameEmpty();

        void OnPassword1Empty();

        void OnPassword2Empty();

        void OnPasswordNotMatch();

        void OnRegisterSuccess();

        void OnRegisterFailure();

    }

    interface Presenter extends ITaskListener {

        void OnRegisterRequest(String email, String name, String password1, String password2);

    }

    interface Model {

        void DoRegister();

    }

}