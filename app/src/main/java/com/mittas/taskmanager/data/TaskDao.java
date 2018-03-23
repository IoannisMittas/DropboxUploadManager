package com.mittas.taskmanager.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM Task")
    LiveData<List<Task>> getAllTasks();

    @Query("SELECT * FROM Task WHERE id = :id")
    Task getTaskById(int id);

    @Query("SELECT * FROM Task WHERE status = 'PENDING' ")
    LiveData<List<Task>> getPendingTasks();

    @Query("SELECT * FROM Task WHERE status = 'COMPLETED'")
    LiveData<List<Task>> getCompletedTasks();

    // TODO fix hardcoded statuses

    @Insert(onConflict = REPLACE)
    void addTask(Task task);

    @Update
    void updateTasks(Task... tasks);

    @Delete
    void deleteTask(Task task);

}
