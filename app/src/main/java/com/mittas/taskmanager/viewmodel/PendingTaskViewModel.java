package com.mittas.taskmanager.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

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

}






