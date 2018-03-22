package com.mittas.taskmanager.ui.completed;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mittas.taskmanager.R;
import com.mittas.taskmanager.data.Task;
import com.mittas.taskmanager.ui.MainActivity;
import com.mittas.taskmanager.viewmodel.CompletedTaskViewModel;

import java.util.ArrayList;
import java.util.List;

public class CompletedTasksFragment extends Fragment {
    private Context context = getActivity();
    private CompletedTaskViewModel viewModel;
    private CompletedTaskAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);









    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_completed_tasks, container, false);

        // TODO
        recyclerView = rootView.findViewById(R.id.recyclerView);
        adapter = new CompletedTaskAdapter(new ArrayList<Task>(), context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(CompletedTaskViewModel.class);

        viewModel.getCompletedTasks().observe(context, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable List<Task> tasks) {
                adapter.setTasks(tasks);
            }
        });





        return rootView;
    }






}
