package com.mittas.taskmanager.data;

import android.arch.persistence.room.Entity;
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

    private int status;
    private final static int PENDING = 0;
    private final static int COMPLETED = 1;

    public Task(String name, String description, String filePath) {
        this.name = name;
        this.description = description;
        this.filePath = filePath;
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

    public int getStatus() {
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

    public void setStatus(int status) {
        this.status = status;
    }
}
