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

    @Query("select * from BorrowModel")
    LiveData<List<BorrowModel>> getAllBorrowedItems();

    @Query("select * from BorrowModel where id = :id")
    BorrowModel getItembyId(String id);

    @Insert(onConflict = REPLACE)
    void addTask(Task task);

    @Delete
    void deleteTask(Task task);

}
