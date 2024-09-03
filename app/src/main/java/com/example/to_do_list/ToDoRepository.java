package com.example.to_do_list;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ToDoRepository {
    private ListDao mListDao;
    private LiveData<List<ListEntity>> mAllLists;


    ToDoRepository(Application application) {
        ToDoRoomDatabase db = ToDoRoomDatabase.getDatabase(application);
        mListDao = db.listDao();
        mAllLists = mListDao.getAllLists();

    }

    LiveData<List<ListEntity>> getAllWords() {
        return mAllLists;
    }

    void insert(ListEntity word) {
        ToDoRoomDatabase.databaseWriteExecutor.execute(() -> {
            mListDao.insert(word);
        });
    }

    public void delete(ListEntity listEntity) {
        ToDoRoomDatabase.databaseWriteExecutor.execute(() -> mListDao.delete(listEntity));
    }
}
