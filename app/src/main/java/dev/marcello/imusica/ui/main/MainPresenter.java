package dev.marcello.imusica.ui.main;

import android.content.Context;

import java.util.Map;

import dev.marcello.imusica.model.UserDAO;

/**
 * Marcello
 * 2019
 */

public class MainPresenter implements IMainContract.Presenter {

    private IMainContract.View view;
    private IMainContract.Model model;

    public MainPresenter(IMainContract.View view, Context context) {
        this.view = view;
        this.model = new UserDAO(context, this);
    }

    @Override
    public void OnUserDataRequest() {
        Map<String, String> userData = model.DoGetUserData();
        view.OnUserDataRequestSuccess(userData.get("name"), userData.get("email"));
    }

    @Override
    public void OnLogoutRequest() {
        view.ShowProgress();
        model.DoLogout();
    }

    @Override
    public void OnVerifyUserDeleted(boolean value) {
        if (value){
            view.OnLogoutSuccess();
        }
    }

    @Override
    public void OnDestroy() {
        this.view = null;
    }

    @Override
    public void OnSuccess() {
        if (view != null) {
            view.HideProgress();
            view.OnLogoutSuccess();
        }
    }

    @Override
    public void OnFailure(String message) {
        if (view != null) {
            view.HideProgress();
            view.OnLogoutFailure(message);
        }
    }

}