package com.mittas.taskmanager.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by John on 21-Mar-18.
 */

@Entity
public class Task {


    @PrimaryKey(autoGenerate = true)
    public int id;

    private String name;

    private String description;

    private String filePath;

    private int time;

    private String status;

    @Ignore
    public static final String pending = "PENDING";
    @Ignore
    public static final String completed = "COMPLETED";

    public Task(String name, String description, String filePath, String status) {
        this.name = name;
        this.description = description;
        this.filePath = filePath;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getFilePath() {
        return filePath;
    }

    public int getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
