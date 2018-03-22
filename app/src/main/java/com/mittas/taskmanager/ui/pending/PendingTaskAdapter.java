package com.mittas.taskmanager.ui.pending;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mittas.taskmanager.R;
import com.mittas.taskmanager.data.Task;
import com.mittas.taskmanager.ui.gestures.ItemTouchHelperAdapter;

import java.util.List;

public class PendingTaskAdapter extends RecyclerView.Adapter<PendingTaskAdapter.ViewHolder> implements ItemTouchHelperAdapter {

    private List<Task> taskList;
    private AdapterCallback adapterCallback;
    private View.OnLongClickListener longClickListener;

    // Used to communicate with fragment
    public interface AdapterCallback {
        void onSwipeTaskCallback(Task task, int direction);
    }


    public PendingTaskAdapter(List<Task> taskList, AdapterCallback callback,View.OnLongClickListener longClickListener ) {
        this.taskList = taskList;
        this.adapterCallback = callback;
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

        ViewHolder(View view) {
            super(view);
            nameTextView = view.findViewById(R.id.name_textview);
        }
    }


}
