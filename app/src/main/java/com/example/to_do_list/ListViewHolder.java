package com.example.to_do_list;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class ListViewHolder extends RecyclerView.ViewHolder {
    private final MaterialTextView listNameView;
    private final RadioButton deleteButton;

    private ListEntity listEntity;
    private TaskListAdapter.OnDeleteClickListener onDeleteClickListener;

    private ListViewHolder(View itemView, TaskListAdapter.OnDeleteClickListener onDeleteClickListener) {
        super(itemView);
        listNameView = itemView.findViewById(R.id.list_name);
        deleteButton = itemView.findViewById(R.id.cancel_button);
        this.onDeleteClickListener = onDeleteClickListener;

        deleteButton.setOnClickListener(v -> {
            if (onDeleteClickListener != null && listEntity != null) {
                onDeleteClickListener.onDeleteClicked(listEntity);
            }
        });

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(view.getContext(),TasksActivity.class);
                intent.putExtra(TasksActivity.EXTRA_LIST_NAME, listEntity.getMListName());
                view.getContext().startActivity(intent);
            }
        });
    }

    public void bind(ListEntity listEntity, TaskListAdapter.OnDeleteClickListener onDeleteClickListener) {
        this.listEntity = listEntity;
        listNameView.setText(listEntity.getMListName());
        this.onDeleteClickListener = onDeleteClickListener;
    }

    public static ListViewHolder create(ViewGroup parent, TaskListAdapter.OnDeleteClickListener onDeleteClickListener) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ListViewHolder(view, onDeleteClickListener);
    }
}
