package dev.marcello.imusica.ui.register;

import android.content.Context;
import android.util.Patterns;

import java.util.HashMap;
import java.util.Map;

import dev.marcello.imusica.model.UserDAO;

/**
 * Marcello
 * 2019
 */

public class RegisterPresenter implements IRegisterContract.Presenter {

    private IRegisterContract.View view;
    private IRegisterContract.Model model;
    private Map<String, String> user;

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

    @Override
    public void OnEditRequest(String email, String name, String password1, String password2) {
        if (FormValidator(email, name, password1, password2)){
            if (ChangesValidator(name, password1)){
                view.ShowProgress();
                Map<String, String> updatedUser = new HashMap<>();
                updatedUser.put("id", user.get("id"));
                updatedUser.put("email", email);
                updatedUser.put("name", name);
                updatedUser.put("password", password1);
                model.DoUpdate(updatedUser);
            }else {
                view.OnEditRequestFailure();
            }
        }
    }

    @Override
    public void OnVerifyEditMode(boolean isEditMode) {
        if (isEditMode){
            user = new HashMap<>();
            user = model.DoGetUserData();
            view.OnEditMode(
                    user.get("email"),
                    user.get("name"),
                    user.get("password")
            );
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

    private boolean ChangesValidator(String name, String password){
        return !(name.equals(user.get("name"))) || !(password.equals(user.get("password")));
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