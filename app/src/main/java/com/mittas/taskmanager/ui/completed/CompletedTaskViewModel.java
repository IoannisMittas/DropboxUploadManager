package com.mittas.taskmanager.ui.completed;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.mittas.taskmanager.data.AppDatabase;
import com.mittas.taskmanager.data.Task;
import com.mittas.taskmanager.ui.pending.PendingTaskViewModel;

import java.util.List;


public class CompletedTaskViewModel extends AndroidViewModel {

    private final LiveData<List<Task>> completedTasks;

    private AppDatabase appDatabase;

    public CompletedTaskViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getAppDatabase(this.getApplication());

        completedTasks = appDatabase.taskDao().getCompletedTasks();
    }

    public LiveData<List<Task>> getCompletedTasks() {
        return completedTasks;
    }

    public void updateTasks(final Task task) {
        new CompletedTaskViewModel.updateAsyncTask(appDatabase).execute(task);
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
