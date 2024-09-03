package com.example.to_do_list;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;


public class MainActivity extends AppCompatActivity{

    private TextInputEditText maddEditText;
    private MaterialButton mAddList;
    private MaterialButton mSave;
    private static final String TAG = MainActivity.class.getName();
    private ListViewModel mListViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    public static final String EXTRA_REPLY = "com.example.android.listsql.REPLY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_main);


        maddEditText = findViewById(R.id.list_name_input);
        mSave = findViewById(R.id.save_btn);
        maddEditText.setVisibility(View.GONE);
        mSave.setVisibility(View.GONE);

        mAddList = findViewById(R.id.add_list_btn);
        mAddList.setOnClickListener(view -> {
            maddEditText.setVisibility(View.VISIBLE);
            mSave.setVisibility(View.VISIBLE);
        });

        mSave.setOnClickListener(view -> saveList());

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final  TaskListAdapter adapter = new TaskListAdapter(new TaskListAdapter.ListDiff());
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        int spaceInPixels = getResources().getDimensionPixelSize(R.dimen.recycler_item_space);
        recyclerView.addItemDecoration(new SpaceItemDecoration(spaceInPixels));
        mListViewModel =new ViewModelProvider(this).get(ListViewModel.class);
        mListViewModel.getmAllList().observe(this, adapter::submitList);

        adapter.setOnDeleteClickListener(listEntity -> {
            mListViewModel.delete(listEntity);
            Toast.makeText(MainActivity.this, "List deleted", Toast.LENGTH_SHORT).show();
        });
        Toast.makeText(MainActivity.this, "Working main here", Toast.LENGTH_LONG).show();
    }

    private void saveList() {
        String list_name = maddEditText.getText().toString().trim();
        Log.d(TAG, "List name: " + list_name);
        // If list name is empty, show a toast and return
        if (list_name.isEmpty()) {
            Toast.makeText(this, "List name cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }else {
            ListEntity listEntity =new ListEntity(list_name);
            mListViewModel.insert(listEntity);
        }

        // Clear the EditText and hide it after saving
        maddEditText.setText("");
        maddEditText.setVisibility(View.GONE);
        mSave.setVisibility(View.GONE);
    }

    private void deleteList(ListEntity listEntity) {
        mListViewModel.delete(listEntity);
    }

}
