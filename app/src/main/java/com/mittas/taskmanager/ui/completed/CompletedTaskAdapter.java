package com.mittas.taskmanager.ui.completed;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mittas.taskmanager.R;
import com.mittas.taskmanager.data.Task;
import com.mittas.taskmanager.ui.gestures.ItemTouchHelperAdapter;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.lang.String.format;

class CompletedTaskAdapter extends RecyclerView.Adapter<CompletedTaskAdapter.ViewHolder> implements ItemTouchHelperAdapter {

    private List<Task> taskList;
    private CompletedTaskAdapter.AdapterCallback adapterCallback;
    private View.OnLongClickListener longClickListener;

    // Used to communicate with fragment
    public interface AdapterCallback {
        void onSwipeTaskCallback(Task task, int direction);
    }

    public CompletedTaskAdapter(List<Task> taskList, CompletedTaskAdapter.AdapterCallback callback, View.OnLongClickListener longClickListener ) {
        this.taskList = taskList;
        this.adapterCallback = callback;
        this.longClickListener = longClickListener;
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
        String time = getFormattedTime(task.getTime());
        holder.timeTextView.setText(time);
        holder.itemView.setTag(task);
        holder.itemView.setOnLongClickListener(longClickListener);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    private String getFormattedTime(int time) {
        return (new SimpleDateFormat("mm:ss:SSS")).format(new Date(time));
    }

    public void setTasks(List<Task> taskList) {
        this.taskList = taskList;
        notifyDataSetChanged();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        // do nothing
    }

    // Called on swiping
    @Override
    public void onItemDismiss(int position, int direction) {
        Task task = taskList.get(position);

        adapterCallback.onSwipeTaskCallback(task, direction);

        notifyItemRemoved(position);

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