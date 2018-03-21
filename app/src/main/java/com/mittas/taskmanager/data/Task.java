package com.mittas.taskmanager.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by John on 21-Mar-18.
 */

@Entity
public class Task {

    @PrimaryKey(autoGenerate = true)
    public int id;

    private String name;

    private String description;

    private String fileName;

    private int time;

    private String status;

    public Task(String name, String description, String fileName) {
        this.name = name;
        this.description = description;
        this.fileName = fileName;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getFileName() {
        return fileName;
    }

    public int getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }
}
