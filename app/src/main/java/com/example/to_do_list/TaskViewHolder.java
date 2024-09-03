package com.example.to_do_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

public class TaskViewHolder extends RecyclerView.ViewHolder {

    private final MaterialTextView taskname;
    private final RadioButton taskdel;

    private TaskEntity taskEntity;
    private TaskAdapter.OnDeleteClickListener onDeleteClickListener;

    private TaskViewHolder(View item, TaskAdapter.OnDeleteClickListener onDeleteClickListener){
        super(item);
        this.taskname = item.findViewById(R.id.task_name);
        this.taskdel = item.findViewById(R.id.tsk_del_button);
        this.onDeleteClickListener = onDeleteClickListener;

        taskdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onDeleteClickListener != null && taskEntity != null) {
                    onDeleteClickListener.onDeleteClicked(taskEntity);
                }
            }
        });
    }

    public void bind(TaskEntity taskEntity, TaskAdapter.OnDeleteClickListener onDeleteClickListener) {
        this.taskEntity = taskEntity;
        taskname.setText(taskEntity.getTaskName());
        this.onDeleteClickListener = onDeleteClickListener;
    }

    public static TaskViewHolder create(ViewGroup parent, TaskAdapter.OnDeleteClickListener onDeleteClickListener) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_list_item, parent, false);
        return new TaskViewHolder(view, onDeleteClickListener);
    }
}
