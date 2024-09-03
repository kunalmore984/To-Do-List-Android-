package com.example.to_do_list;

import static java.security.AccessController.getContext;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;


public class TaskAdapter extends ListAdapter<TaskEntity,TaskViewHolder> {

    private TaskAdapter.OnDeleteClickListener onDeleteClickListener;

    public TaskAdapter(@NonNull DiffUtil.ItemCallback<TaskEntity> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return TaskViewHolder.create(parent, onDeleteClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        TaskEntity current = getItem(position);
        holder.bind(current, onDeleteClickListener);
    }

    public void setOnDeleteClickListener(TaskAdapter.OnDeleteClickListener listener) {
        this.onDeleteClickListener = listener;
    }

    public interface OnDeleteClickListener {
        void onDeleteClicked(TaskEntity taskEntity);
    }

    static class ListDiff extends DiffUtil.ItemCallback<TaskEntity> {
        @Override
        public boolean areItemsTheSame(@NonNull TaskEntity oldItem, @NonNull TaskEntity newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull TaskEntity oldItem, @NonNull TaskEntity newItem) {
            return oldItem.getTaskName().equals(newItem.getTaskName());
        }
    }
}
