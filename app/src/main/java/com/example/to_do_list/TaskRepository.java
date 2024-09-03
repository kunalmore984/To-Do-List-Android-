package com.example.to_do_list;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskRepository {
    private TaskDao mTaskDao;
    private LiveData<List<TaskEntity>> mAllTask;


    TaskRepository(Application application) {
        ToDoRoomDatabase db = ToDoRoomDatabase.getDatabase(application);
        mTaskDao = db.taskDao();
        mAllTask = mTaskDao.getAllTask();

    }

    LiveData<List<TaskEntity>> getmAllTask() {
        return mAllTask;
    }

    void insert(TaskEntity task) {
        ToDoRoomDatabase.databaseWriteExecutor.execute(() -> {
            mTaskDao.insert(task);
        });
    }

    public void delete(TaskEntity taskEntity) {
        ToDoRoomDatabase.databaseWriteExecutor.execute(() -> mTaskDao.delete(taskEntity));
    }
}
