package dev.marcello.imusica.model;

import android.content.Context;
import android.database.Cursor;

import dev.marcello.imusica.R;
import dev.marcello.imusica.ui.ITaskListener;
import dev.marcello.imusica.ui.register.IRegisterContract;

/**
 * Marcello
 * 2019
 */

public class UserDAO implements IRegisterContract.Model {

    private ITaskListener.User taskListener;
    private IDatabaseCRUD<UserModel> database;
    private Context context;

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

}