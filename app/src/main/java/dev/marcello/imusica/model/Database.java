package dev.marcello.imusica.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

/**
 * Marcello
 * 2019
 */

public class Database extends SQLiteOpenHelper implements IDatabaseCRUD<UserModel> {

    private static final String database = "iMusica";
    private static final String users = "users";
    private SQLiteDatabase sqLiteDatabase;

    public Database(@Nullable Context context) {
        super(context, database, null, 1);
        sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + users + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "email VARCHAR(50) NOT NULL, " +
                "name VARCHAR(50) NOT NULL, " +
                "password VARCHAR(20) NOT NULL" + " )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    @Override
    public long Create(UserModel obj) {
        ContentValues values = new ContentValues();
        values.put("email", obj.getEmail());
        values.put("name", obj.getName());
        values.put("password", obj.getPassword());
        return sqLiteDatabase.insert(users, null, values);
    }

    @Override
    public Cursor Read(UserModel obj) {
        String[] args = {obj.getEmail()};
        return sqLiteDatabase.query(users, null, "email = ?", args, null, null, null);
    }

}