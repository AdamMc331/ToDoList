package com.adammcneilly.todolist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

/**
 * Adapter to display Tasks in a ListView.
 *
 * Created by adam.mcneilly on 6/17/16.
 */
public class TaskAdapter extends BaseAdapter {
    /**
     * The Context the Adapter will display items in.
     */
    private Context context;

    /**
     * The list of tasks this Adapter is responsible for displaying.
     */
    private List<Task> tasks;

    public TaskAdapter(Context context, List<Task> tasks) {
        this.context = context;
        this.tasks = tasks;
    }

    /**
     * Determines the number of items in this adapter.
     */
    @Override
    public int getCount() {
        return tasks.size();
    }

    /**
     * Retrieves an item at a specific position.
     */
    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Adds a new item to the adapter.
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
        notifyDataSetChanged();
    }

    /**
     * Retrieves the list of items in this adapter.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a list of tasks to the adapter.
     */
    public void addTasks(List<Task> newTasks) {
        tasks.addAll(newTasks);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Inflate view
        @SuppressLint("ViewHolder") View view = LayoutInflater.from(context).inflate(R.layout.list_item_task, parent, false);

        // Get elements
        TextView description = (TextView) view.findViewById(R.id.task_description);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.task_completed);

        // Get task
        Task currentTask = tasks.get(position);
        description.setText(currentTask.getDescription());
        checkBox.setChecked(currentTask.isCompleted());

        // When the checkbox changes, change the status of this item.
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tasks.get(position).setCompleted(isChecked);
            }
        });

        // Return view.
        return view;
    }
}
