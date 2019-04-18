package dev.marcello.imusica.ui.register;

import android.content.Context;
import android.util.Patterns;

import dev.marcello.imusica.model.UserDAO;

/**
 * Marcello
 * 2019
 */

public class RegisterPresenter implements IRegisterContract.Presenter {

    private IRegisterContract.View view;
    private UserDAO model;

    public RegisterPresenter(IRegisterContract.View view, Context context) {
        this.view = view;
        this.model = new UserDAO(context, this);
    }

    @Override
    public void OnRegisterRequest(String email, String name, String password1, String password2) {
        if (FormValidator(email, name, password1, password2)) {
            view.ShowProgress();
            model.DoRegister(email, name, password1);
        }
    }

    private boolean FormValidator(String email, String name, String password1, String password2) {

        if (email.isEmpty() || name.isEmpty() || password1.isEmpty() || password2.isEmpty()) {

            if (email.isEmpty()) {
                view.OnEmailEmpty();
            } else if (name.isEmpty()) {
                view.OnNameEmpty();
            } else if (password1.isEmpty()) {
                view.OnPassword1Empty();
            } else {
                view.OnPassword2Empty();
            }
            return false;

        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            view.OnEmailInvalid();
            return false;

        } else if (!password1.equals(password2)) {

            view.OnPasswordNotMatch();
            return false;

        } else {
            return true;
        }

    }

    @Override
    public void OnSuccess() {
        if (view != null) {
            view.HideProgress();
            view.OnRegisterSuccess();
        }
    }

    @Override
    public void OnFailure(String message) {
        if (view != null) {
            view.HideProgress();
            view.OnRegisterFailure(message);
        }
    }

}