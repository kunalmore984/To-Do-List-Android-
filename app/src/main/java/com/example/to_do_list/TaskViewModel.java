package com.example.to_do_list;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository mTaskRepository;
    private LiveData<List<TaskEntity>> mAllTask;

    public TaskViewModel(Application application){
        super(application);
        this.mTaskRepository =new TaskRepository(application);
        this.mAllTask = mTaskRepository.getmAllTask();
    }

    LiveData<List<TaskEntity>> getmAllTask(){
        return mAllTask;
    }

    public void insert(TaskEntity taskEntity){
        mTaskRepository.insert(taskEntity);
    }

    public void delete(TaskEntity taskEntity) {
        mTaskRepository.delete(taskEntity);
    }
}
