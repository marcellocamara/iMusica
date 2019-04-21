package dev.marcello.imusica.util;

import android.os.AsyncTask;

import dev.marcello.imusica.ui.ITaskListener;

/**
 * Marcello
 * 2019
 */

public class CheckUrlAsyncTask extends AsyncTask<Void, Void, Void> {

    private ITaskListener.Posts taskListener;
    private boolean value;

    public CheckUrlAsyncTask(ITaskListener.Posts taskListener, boolean value){
        this.taskListener = taskListener;
        this.value = value;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        taskListener.OnOkHttpResponse(value);
    }

}