package com.mittas.taskmanager.ui.completed;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mittas.taskmanager.R;
import com.mittas.taskmanager.data.Task;

import java.util.List;

class CompletedTaskAdapter extends RecyclerView.Adapter<CompletedTaskAdapter.ViewHolder> {

    private List<Task> taskList;
    private View.OnLongClickListener longClickListener;

      /*  public CompletedTaskAdapter(List<Task> taskList, View.OnLongClickListener longClickListener) {
        this.taskList = taskList;
        this.longClickListener = longClickListener;
    }*/

    // TODO temp
    public CompletedTaskAdapter(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.completed_task_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.nameTextView.setText(task.getName());
        //TODO convert millis to nice format
        //holder.timeTextView.setText(task.getBorrowDate().toLocaleString().substring(0, 11));
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
        private TextView timeTextView;

        ViewHolder(View view) {
            super(view);
            nameTextView = view.findViewById(R.id.name_textview);
            timeTextView = view.findViewById(R.id.time_textview);
        }
    }
}