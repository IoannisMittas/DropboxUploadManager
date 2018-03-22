package com.mittas.taskmanager.ui.pending;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mittas.taskmanager.R;
import com.mittas.taskmanager.data.Task;
import com.mittas.taskmanager.ui.gestures.ItemTouchHelperAdapter;

import java.util.List;

/**
 * Created by John on 22-Mar-18.
 */

public class PendingTaskAdapter extends RecyclerView.Adapter<PendingTaskAdapter.ViewHolder> implements ItemTouchHelperAdapter {

    private List<Task> taskList;
    private View.OnLongClickListener longClickListener;

   /* public PendingTaskAdapter(List<Task> taskList, View.OnLongClickListener longClickListener) {
        this.taskList = taskList;
        this.longClickListener = longClickListener;
    }*/

   // TODO temp
    public PendingTaskAdapter(List<Task> taskList) {
        this.taskList = taskList;
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

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        // do nothing
    }

    // Called on swiping
    @Override
    public void onItemDismiss(int position, int direction) {
        if(direction == ItemTouchHelper.LEFT) {
            // TODO postpone task for 1 minute
        } else if(direction == ItemTouchHelper.RIGHT) {
            // TODO start task immediately
        }

        // TODO set status as UPLOADING

        notifyItemRemoved(position);

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;

        ViewHolder(View view) {
            super(view);
            nameTextView = view.findViewById(R.id.name_textview);
        }
    }


}
