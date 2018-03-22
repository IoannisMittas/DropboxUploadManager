package com.mittas.taskmanager.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.mittas.taskmanager.data.AppDatabase;
import com.mittas.taskmanager.data.Task;

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

}
