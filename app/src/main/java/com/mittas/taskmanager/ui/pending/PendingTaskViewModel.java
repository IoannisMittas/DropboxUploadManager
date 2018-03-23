package com.mittas.taskmanager.ui.pending;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.mittas.taskmanager.data.AppDatabase;
import com.mittas.taskmanager.data.Task;

import java.util.List;

public class PendingTaskViewModel extends AndroidViewModel {

    private final LiveData<List<Task>> pendingTasks;

    private AppDatabase appDatabase;

    public PendingTaskViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getAppDatabase(this.getApplication());

        pendingTasks = appDatabase.taskDao().getPendingTasks();

    }

    public LiveData<List<Task>> getPendingTasks() {
        return pendingTasks;
    }

    public Task getTaskById(int id) {
        return appDatabase.taskDao().getTaskById(id);
    }

    public void updateTasks(final Task task) {
        new updateAsyncTask(appDatabase).execute(task);
    }

    private static class updateAsyncTask extends AsyncTask<Task, Void, Void> {
        private AppDatabase db;

        updateAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final Task... params) {
            db.taskDao().updateTasks(params[0]);
            return null;
        }
    }

}






