package com.example.to_do_list;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "listtable")
public class ListEntity {

    @NonNull
    @ColumnInfo(name = "list")
    private String mListName;

    @PrimaryKey(autoGenerate = true)
    public int id;

    // Constructor that Room will use
    public ListEntity(int id, @NonNull String mListName) {
        this.id = id;
        this.mListName = mListName;
    }

    // Ignore this constructor for Room
    @Ignore
    public ListEntity(@NonNull String mListName) {
        this.mListName = mListName;
    }

    // Getter for mListName
    public String getMListName() {
        return mListName;
    }

    // Setter for mListName (if needed)
    public void setMListName(@NonNull String mListName) {
        this.mListName = mListName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
