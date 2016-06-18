package com.adammcneilly.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.List;

/**
 * MainActivity that displays the Todo list items.
 *
 * Created by adam.mcneilly on 6/17/16.
 */
public class MainActivity extends AppCompatActivity {
    /**
     * String key for an Intent extra for Task descriptions.
     */
    public static final String EXTRA_DESCRIPTION = "description";

    /**
     * Request code for the AddTask intent.
     */
    private static final int ADD_TASK = 0;

    /**
     * Adapter that displays our list items.
     */
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find toolbar and set it as our support action bar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Find the FAB and set a click listener that starts the AddTaskActivity.
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startAddTaskActivity();
                }
            });
        }

        // Read the stored tasks.
        List<Task> storedTasks = Storage.readData(this);

        // Create the adapter and set it to the ListView.
        taskAdapter = new TaskAdapter(this, storedTasks);
        ListView listView = (ListView) findViewById(R.id.task_list_view);
        if (listView != null) {
            listView.setAdapter(taskAdapter);
        }
    }

    /**
     * Starts the AddTaskActivity by calling startActivityForResult. This means that when the
     * AddTaskActivity calls setResult(), the onActivityResult method in this class will be called.
     */
    private void startAddTaskActivity() {
        Intent addTask = new Intent(this, AddTaskActivity.class);
        startActivityForResult(addTask, ADD_TASK);
    }

    /**
     * This method is called when another Activity, invoked by startActivityForResult, calls setResult().
     *
     * If the result is ok, and the request is coming from the AddTaskActivity, add the new task to our list.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK && requestCode == ADD_TASK) {
            String description = data.getStringExtra(EXTRA_DESCRIPTION);

            Task newTask = new Task(description);
            taskAdapter.addTask(newTask);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // When the Activity is paused, store the todo list.
        Storage.writeData(this, taskAdapter.getTasks());
    }

    @Override
    protected void onResume() {
        super.onResume();

        // When the activity resumes, read the todo list and give it to the adapter, if it is empty.
        if(taskAdapter.getCount() == 0) {
            taskAdapter.addTasks(Storage.readData(this));
        }
    }
}
