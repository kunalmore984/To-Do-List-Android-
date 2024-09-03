package com.example.to_do_list;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;


public class TasksActivity extends AppCompatActivity{

    private FloatingActionButton mAddTaskBtn;
    private TextInputEditText mTaskEditText;
    private Button mSaveTaskBtn;
    private MaterialTextView mTitle;
    public static final String EXTRA_LIST_NAME = "com.example.to_do_list.EXTRA_LIST_NAME";
    private TaskViewModel mTaskViewModel;
    private FloatingActionButton mFab;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        mAddTaskBtn = findViewById(R.id.Add_task_btn);

        mTaskEditText = findViewById(R.id.Add_task_edit);
        Toast.makeText(TasksActivity.this, "Working here", Toast.LENGTH_LONG).show();
        mSaveTaskBtn =findViewById(R.id.save_task_btn);

        mSaveTaskBtn.setVisibility(View.GONE);
        mTaskEditText.setVisibility(View.GONE);

        Intent intent = getIntent();
        String listName = intent.getStringExtra(EXTRA_LIST_NAME);

        mTitle =(MaterialTextView) findViewById(R.id.List_name);
        mTitle.setText(listName);

        mAddTaskBtn.setOnClickListener(view -> {
            mSaveTaskBtn.setVisibility(View.VISIBLE);
            mTaskEditText.setVisibility(View.VISIBLE);
        });

        mSaveTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTask();
            }
        });

        Toast.makeText(TasksActivity.this, "Working here", Toast.LENGTH_SHORT).show();
        RecyclerView recyclerView =findViewById(R.id.Task_recyclerview);
        final TaskAdapter taskAdapter =new TaskAdapter(new TaskAdapter.ListDiff());
        recyclerView.setAdapter(taskAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        int spaceInPixels = getResources().getDimensionPixelSize(R.dimen.recycler_item_space);
        recyclerView.addItemDecoration(new SpaceItemDecoration(spaceInPixels));
        mTaskViewModel =new ViewModelProvider(this).get(TaskViewModel.class);
        mTaskViewModel.getmAllTask().observe(this, taskAdapter::submitList);

        taskAdapter.setOnDeleteClickListener(taskEntity -> {
            mTaskViewModel.delete(taskEntity);
            Toast.makeText(TasksActivity.this, "List deleted", Toast.LENGTH_SHORT).show();
        });
    }

    private void saveTask() {

        String taskName = mTaskEditText.getText().toString().trim();
        if (taskName.isEmpty()) {
            Toast.makeText(this, "Task name cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }else {
            TaskEntity taskEntity =new TaskEntity(taskName);
            mTaskViewModel.insert(taskEntity);
        }

        mTaskEditText.setText("");
        mTaskEditText.setVisibility(View.GONE);
        mSaveTaskBtn.setVisibility(View.GONE);

    }

    private void deleteList(TaskEntity taskEntity) {
        mTaskViewModel.delete(taskEntity);
    }

}
