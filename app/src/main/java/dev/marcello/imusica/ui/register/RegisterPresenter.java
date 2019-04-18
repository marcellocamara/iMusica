package dev.marcello.imusica.ui.register;

import android.util.Patterns;

/**
 * Marcello
 * 2019
 */

public class RegisterPresenter implements IRegisterContract.Presenter {

    private IRegisterContract.View view;

    public RegisterPresenter(IRegisterContract.View view){
        this.view = view;
    }

    @Override
    public void OnRegisterRequest(String email, String name, String password1, String password2) {
        if (FormValidator(email, name, password1, password2)){
            //Allow register
            view.ShowProgress();
            OnSuccess();
        }
    }

    private boolean FormValidator(String email, String name, String password1, String password2){

        if (email.isEmpty() || name.isEmpty() || password1.isEmpty() || password2.isEmpty()){

            if (email.isEmpty()){
                view.OnEmailEmpty();
            }else if (name.isEmpty()){
                view.OnNameEmpty();
            }else if (password1.isEmpty()){
                view.OnPassword1Empty();
            }else {
                view.OnPassword2Empty();
            }
            return false;

        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){

            view.OnEmailInvalid();
            return false;

        }else if (!password1.equals(password2)){

            view.OnPasswordNotMatch();
            return false;

        }else {
            return true;
        }

    }

    @Override
    public void OnSuccess() {
        if (view != null){
            view.HideProgress();
            view.OnRegisterSuccess();
        }
    }

    @Override
    public void OnFailure() {
        if (view != null){
            view.HideProgress();
            view.OnRegisterFailure();
        }
    }

}