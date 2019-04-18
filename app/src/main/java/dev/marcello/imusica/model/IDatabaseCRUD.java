package dev.marcello.imusica.model;

import android.database.Cursor;

/**
 * Marcello
 * 2019
 */

public interface IDatabaseCRUD<T> {

    long Create(T obj);

    Cursor Read(T obj);

    Integer Update(T obj);

    Integer Delete(T obj);

}