package com.adammcneilly.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Activity responsible for adding a new Task.
 *
 * Created by adam.mcneilly on 6/17/16.
 */
public class AddTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        // Find the toolbar and set it as our support action bar.
        // Call setDisplayHomeAsUpEnabled to show back button.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Find the submit button and attach a click listener.
        // When the submit button is clicked, create an Intent that contains the new task
        // description and sets the activity result.
        final Button submitButton = (Button) findViewById(R.id.submit);
        if (submitButton != null) {
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get description
                    EditText description = (EditText) findViewById(R.id.task_description);
                    String descriptionText = "";
                    if (description != null) {
                        descriptionText = description.getText().toString();
                    }

                    Intent data = new Intent();
                    data.putExtra(MainActivity.EXTRA_DESCRIPTION, descriptionText);

                    setResult(RESULT_OK, data);
                    finish();
                }
            });
        }
    }
}
