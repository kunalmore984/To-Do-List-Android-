package com.example.to_do_list;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
@Dao
public interface ListDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ListEntity listname);

    @Query("SELECT * FROM listtable")
    LiveData<List<ListEntity>> getAllLists();

    @Delete
    void delete(ListEntity listEntity);
}
