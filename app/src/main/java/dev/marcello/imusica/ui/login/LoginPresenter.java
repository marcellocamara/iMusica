package dev.marcello.imusica.ui.login;

import android.content.Context;
import android.util.Patterns;

import dev.marcello.imusica.model.UserDAO;

/**
 * Marcello
 * 2019
 */

public class LoginPresenter implements ILoginContract.Presenter {

    private ILoginContract.View view;
    private ILoginContract.Model model;

    public LoginPresenter(ILoginContract.View view, Context context){
        this.view = view;
        model = new UserDAO(context, this);
    }

    @Override
    public void OnLoginRequest(String email, String password, boolean remember) {
        if (FormValidator(email, password)){
            view.ShowProgress();
            model.DoLogin(email, password, remember);
        }
    }

    @Override
    public void OnCheckLoggedIn() {
        model.DoCheckLoggedIn();
    }

    private boolean FormValidator(String email, String password) {

        if (email.isEmpty() || password.isEmpty()) {

            if (email.isEmpty()) {
                view.OnEmailEmpty();
            } else {
                view.OnPasswordEmpty();
            }
            return false;

        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            view.OnEmailInvalid();
            return false;

        } else {
            return true;
        }

    }

    @Override
    public void OnDestroy() {
        this.view = null;
    }

    @Override
    public void OnSuccess() {
        if (view != null){
            view.HideProgress();
            view.OnLoginSuccess();
        }
    }

    @Override
    public void OnFailure(String message) {
        if (view != null){
            view.HideProgress();
            view.OnLoginFailure(message);
        }
    }

}