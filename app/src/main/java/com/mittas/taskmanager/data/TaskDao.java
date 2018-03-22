package com.mittas.taskmanager.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM Task")
    LiveData<List<Task>> getAllTasks();

    @Query("SELECT * FROM Task WHERE id = :id")
    LiveData<List<Task>> getTaskById(int id);

    @Query("SELECT * FROM Task WHERE status = Task.PENDING")
    LiveData<List<Task>> getPendingTasks();

    // TODO query for filepath

    // TODO query for time



    @Query("SELECT * FROM Task WHERE status = Task.COMPLETED")
    LiveData<List<Task>> getCompletedTasks();

    @Insert(onConflict = REPLACE)
    void addTask(Task task);

    @Delete
    void deleteTask(Task task);

}
