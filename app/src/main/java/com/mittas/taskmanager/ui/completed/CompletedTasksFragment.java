package com.mittas.taskmanager.ui.completed;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mittas.taskmanager.R;
import com.mittas.taskmanager.data.Task;
import com.mittas.taskmanager.ui.gestures.SimpleItemTouchHelperCallback;
import com.mittas.taskmanager.ui.pending.PendingTaskAdapter;

import java.util.ArrayList;
import java.util.List;

public class CompletedTasksFragment extends Fragment implements CompletedTaskAdapter.AdapterCallback, View.OnLongClickListener{
    private CompletedTaskViewModel viewModel;
    private CompletedTaskAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(CompletedTaskViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_completed_tasks, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        adapter = new CompletedTaskAdapter(new ArrayList<Task>(), this, this);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);

        viewModel.getCompletedTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable List<Task> tasks) {
                adapter.setTasks(tasks);
            }
        });

        return rootView;
    }

    @Override
    public void onSwipeTaskCallback(Task task, int direction) {
        task.setStatus(Task.pending);
        viewModel.updateTasks(task);
    }

    @Override
    public boolean onLongClick(View v) {
        Task task = (Task) v.getTag();
        // TODO open edit screen
        return true;
    }


}
