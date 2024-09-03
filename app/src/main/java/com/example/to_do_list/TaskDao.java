package com.example.to_do_list;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(TaskEntity taskEntity);

    @Query("SELECT * FROM task_table")
    LiveData<List<TaskEntity>> getAllTask();

    @Delete
    void delete(TaskEntity taskEntity);
}
