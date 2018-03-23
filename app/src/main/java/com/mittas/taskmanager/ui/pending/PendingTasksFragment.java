package com.mittas.taskmanager.ui.pending;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

import com.mittas.taskmanager.R;
import com.mittas.taskmanager.data.Task;
import com.mittas.taskmanager.service.UploadService;
import com.mittas.taskmanager.ui.MainActivity;
import com.mittas.taskmanager.ui.gestures.SimpleItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class PendingTasksFragment extends Fragment implements PendingTaskAdapter.AdapterCallback, View.OnLongClickListener {
    private PendingTaskViewModel viewModel;
    private PendingTaskAdapter adapter;
    private RecyclerView recyclerView;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                long time = bundle.getLong(UploadService.TIME);
                int resultCode = bundle.getInt(UploadService.RESULT);
                if (resultCode == RESULT_OK) {
                    // succesful upload
                } else {
                    // failed upload
                }
            }
        }
    };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(PendingTaskViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pending_tasks, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        adapter = new PendingTaskAdapter(new ArrayList<Task>(), this, this);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);

        viewModel.getPendingTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable List<Task> tasks) {
                adapter.setTasks(tasks);
            }
        });

        return rootView;
    }

    @Override
    public void onSwipeTaskCallback(Task task, int direction) {
        if (direction == ItemTouchHelper.LEFT) {
            // TODO postpone task for 1 minute

        } else if (direction == ItemTouchHelper.RIGHT) {


            // TODO start task immediately
        }

        task.setStatus(Task.uploading);
        viewModel.updateTasks(task);
    }

    @Override
    public boolean onLongClick(View v) {
        Task task = (Task) v.getTag();
        // TODO open edit screen
        return true;
    }

}
