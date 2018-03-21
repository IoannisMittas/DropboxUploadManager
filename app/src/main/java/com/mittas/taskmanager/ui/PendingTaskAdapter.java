package com.mittas.taskmanager.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mittas.taskmanager.R;
import com.mittas.taskmanager.data.Task;

import java.util.List;

/**
 * Created by John on 22-Mar-18.
 */

public class PendingTaskAdapter extends RecyclerView.Adapter<PendingTaskAdapter.ViewHolder> {

    private List<Task> taskList;
    private View.OnLongClickListener longClickListener;

    public PendingTaskAdapter(List<Task> taskList, View.OnLongClickListener longClickListener) {
        this.taskList = taskList;
        this.longClickListener = longClickListener;
    }

    @Override
    public PendingTaskAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PendingTaskAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pending_task_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final PendingTaskAdapter.ViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.nameTextView.setText(task.getName());
        holder.itemView.setTag(task);
        holder.itemView.setOnLongClickListener(longClickListener);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public void setTasks(List<Task> taskList) {
        this.taskList = taskList;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;

        ViewHolder(View view) {
            super(view);
            nameTextView = view.findViewById(R.id.name_textview);
        }
    }







}
