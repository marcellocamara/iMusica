package dev.marcello.imusica.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;

import java.util.HashMap;
import java.util.Map;

import dev.marcello.imusica.R;
import dev.marcello.imusica.ui.ITaskListener;
import dev.marcello.imusica.ui.login.ILoginContract;
import dev.marcello.imusica.ui.main.IMainContract;
import dev.marcello.imusica.ui.register.IRegisterContract;

/**
 * Marcello
 * 2019
 */

public class UserDAO implements IRegisterContract.Model, ILoginContract.Model, IMainContract.Model {

    private ITaskListener.User taskListener;
    private IDatabaseCRUD<UserModel> database;
    private Context context;
    //SharedPreferences
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private final String FILE_NAME = "Auth";

    public UserDAO(Context context, ITaskListener.User iTaskListener) {
        this.taskListener = iTaskListener;
        this.database = new Database(context);
        this.context = context;
    }

    @Override
    public void DoRegister(String email, String name, String password) {
        UserModel user = new UserModel(email, name, password);
        Cursor cursor = database.Read(user);
        if (cursor.getCount() == 1) {
            taskListener.OnFailure(context.getString(R.string.email_already_registered));
        } else {
            if ((database.Create(user)) != -1) {
                taskListener.OnSuccess();
            } else {
                taskListener.OnFailure(context.getString(R.string.failure));
            }
        }
    }

    @Override
    public void DoUpdate(Map<String, String> user) {
        UserModel updatedUser = new UserModel(
                user.get("email"),
                user.get("name"),
                user.get("password")
        );
        updatedUser.setId(Integer.valueOf(user.get("id")));
        if (database.Update(updatedUser) == 1) {
            DoUpdateSharedPreferences(user);
            taskListener.OnSuccess();
        } else {
            taskListener.OnFailure(context.getString(R.string.update_error));
        }
    }

    @Override
    public void DoLogin(String email, String password) {
        UserModel user = new UserModel();
        user.setEmail(email);
        user.setPassword(password);
        Cursor cursor = database.Read(user);
        if (cursor.getCount() == 1) {
            cursor.moveToNext();
            if (cursor.getString(3).equals(user.getPassword())) {
                //Save user in SharedPreferences
                user.setId(cursor.getInt(0));
                user.setName(cursor.getString(2));
                DoSaveSharedPreferences(user);
                taskListener.OnSuccess();
            } else {
                taskListener.OnFailure(context.getString(R.string.password_fail));
            }
        } else {
            taskListener.OnFailure(context.getString(R.string.email_exists));
        }
    }

    @Override
    public void DoCheckLoggedIn() {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");
        String password = sharedPreferences.getString("password", "");
        if (!(email.isEmpty()) && !(password.isEmpty())) {
            taskListener.OnSuccess();
        }
    }

    @Override
    public void DoLogout() {
        try {
            DoDeleteSharedPreferences();
            taskListener.OnSuccess();
        } catch (Exception e) {
            taskListener.OnFailure(e.getMessage());
        }
    }

    @Override
    public Map<String, String> DoGetUserData() {
        UserModel user = DoGetSharedPreferences();
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("id", String.valueOf(user.getId()));
        hashMap.put("email", user.getEmail());
        hashMap.put("name", user.getName());
        hashMap.put("password", user.getPassword());
        return hashMap;
    }

    @Override
    public void DoDelete(String email) {
        UserModel user = new UserModel();
        user.setEmail(email);
        if (database.Delete(user) == 1) {
            //Delete SharedPreferences
            DoDeleteSharedPreferences();
            taskListener.OnFailure("deleted");
        } else {
            taskListener.OnFailure(context.getString(R.string.delete_error));
        }
    }

    private void DoSaveSharedPreferences(UserModel user) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putInt("id", user.getId());
        editor.putString("email", user.getEmail());
        editor.putString("name", user.getName());
        editor.putString("password", user.getPassword());
        editor.apply();
    }

    private void DoDeleteSharedPreferences() {
        context.getSharedPreferences("Auth", Context.MODE_PRIVATE).edit().clear().apply();
    }

    private UserModel DoGetSharedPreferences() {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        UserModel user = new UserModel();
        user.setId(sharedPreferences.getInt("id", -1));
        user.setEmail(sharedPreferences.getString("email", ""));
        user.setName(sharedPreferences.getString("name", ""));
        user.setPassword(sharedPreferences.getString("password", ""));
        return user;
    }

    private void DoUpdateSharedPreferences(Map<String, String> user) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("name", user.get("name"));
        editor.putString("password", user.get("password"));
        editor.apply();
    }

}