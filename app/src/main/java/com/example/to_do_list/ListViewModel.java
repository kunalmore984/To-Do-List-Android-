package com.example.to_do_list;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ListViewModel extends AndroidViewModel {

    private ToDoRepository mToDoRepository;
    private final LiveData<List<ListEntity>> mAllList;

    public ListViewModel(Application application){
        super(application);
        mToDoRepository =new ToDoRepository(application);
        mAllList = mToDoRepository.getAllWords();
    }

    LiveData<List<ListEntity>> getmAllList() {
        return mAllList;
    }

    public void insert(ListEntity listEntity){
        mToDoRepository.insert(listEntity);
    }

    public void delete(ListEntity listEntity) {
        mToDoRepository.delete(listEntity);
    }
}
