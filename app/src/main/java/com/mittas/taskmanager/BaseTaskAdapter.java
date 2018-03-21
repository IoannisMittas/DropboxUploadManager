package com.mittas.taskmanager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class BaseTaskAdapter extends RecyclerView.Adapter<BaseTaskAdapter.RecyclerViewHolder> {

    private List<Task> taskList;
    private View.OnLongClickListener longClickListener;

    public BaseTaskAdapter(List<Task> borrowModelList, View.OnLongClickListener longClickListener) {
        this.taskList = borrowModelList;
        this.longClickListener = longClickListener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
//        BorrowModel borrowModel = taskList.get(position);
//        holder.itemTextView.setText(borrowModel.getItemName());
//        holder.nameTextView.setText(borrowModel.getPersonName());
//        holder.dateTextView.setText(borrowModel.getBorrowDate().toLocaleString().substring(0, 11));
//        holder.itemView.setTag(borrowModel);
//        holder.itemView.setOnLongClickListener(longClickListener);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public void setItems(List<Task> borrowModelList) {
        this.taskList = borrowModelList;
        notifyDataSetChanged();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView itemTextView;
        private TextView nameTextView;
        private TextView dateTextView;

        RecyclerViewHolder(View view) {
            super(view);
            itemTextView = view.findViewById(R.id.itemTextView);
            nameTextView = view.findViewById(R.id.nameTextView);
            dateTextView = view.findViewById(R.id.dateTextView);
        }
    }
}