package com.example.to_do_list;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class TaskListAdapter extends ListAdapter<ListEntity, ListViewHolder> {

    private OnDeleteClickListener onDeleteClickListener;

    public TaskListAdapter(@NonNull DiffUtil.ItemCallback<ListEntity> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ListViewHolder.create(parent, onDeleteClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        ListEntity current = getItem(position);
        holder.bind(current, onDeleteClickListener);
    }

    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        this.onDeleteClickListener = listener;
    }

    public interface OnDeleteClickListener {
        void onDeleteClicked(ListEntity listEntity);
    }

    static class ListDiff extends DiffUtil.ItemCallback<ListEntity> {
        @Override
        public boolean areItemsTheSame(@NonNull ListEntity oldItem, @NonNull ListEntity newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull ListEntity oldItem, @NonNull ListEntity newItem) {
            return oldItem.getMListName().equals(newItem.getMListName());
        }
    }
}
