package dev.marcello.imusica.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;

import dev.marcello.imusica.R;
import dev.marcello.imusica.ui.ITaskListener;
import dev.marcello.imusica.ui.login.ILoginContract;
import dev.marcello.imusica.ui.register.IRegisterContract;

/**
 * Marcello
 * 2019
 */

public class UserDAO implements IRegisterContract.Model, ILoginContract.Model {

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
    public void DoLogin(String email, String password, boolean remember) {
        UserModel user = new UserModel();
        user.setEmail(email);
        user.setPassword(password);
        Cursor cursor = database.Read(user);
        if (cursor.getCount() == 1) {
            cursor.moveToNext();
            if (cursor.getString(3).equals(user.getPassword())) {
                if (remember){
                    //Save user in SharedPreferences
                    user.setId(cursor.getInt(0));
                    user.setName(cursor.getString(2));
                    DoSaveSharedPreferences(user);
                    taskListener.OnSuccess();
                }else {
                    taskListener.OnSuccess();
                }
            }else {
                taskListener.OnFailure("Wrong password.");
            }
        } else {
            taskListener.OnFailure("This e-mail is not registered.");
        }
    }

    @Override
    public void DoCheckLoggedIn() {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");
        String password = sharedPreferences.getString("password", "");
        if ( !(email.isEmpty()) && !(password.isEmpty()) ){
            taskListener.OnSuccess();
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

}