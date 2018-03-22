package com.mittas.taskmanager.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;

import com.mittas.taskmanager.data.AppDatabase;
import com.mittas.taskmanager.data.Task;

/**
 * Created by John on 22-Mar-18.
 */

public class AddTaskViewModel extends AndroidViewModel {
    private AppDatabase appDatabase;

    public AddTaskViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getAppDatabase(this.getApplication());

    }

    public void addTask(final Task task) {
        new addAsyncTask(appDatabase).execute(task);
    }

    private static class addAsyncTask extends AsyncTask<Task, Void, Void> {
        private AppDatabase db;

        addAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final Task... params) {
            db.taskDao().addTask(params[0]);
            return null;
        }
    }

}
