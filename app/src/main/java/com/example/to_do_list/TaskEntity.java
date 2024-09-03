package com.example.to_do_list;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "task_table")
public class TaskEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "task")
    private String mTaskName;

    // No-argument constructor required by Room
    public TaskEntity() {}

    public TaskEntity(@NonNull String taskName) {
        this.mTaskName = taskName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getTaskName() {
        return mTaskName;
    }

    public void setTaskName(@NonNull String taskName) {
        this.mTaskName = taskName;
    }
}
