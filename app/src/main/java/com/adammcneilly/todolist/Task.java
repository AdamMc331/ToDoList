package com.adammcneilly.todolist;

import java.io.Serializable;

/**
 * Defines a task that is in the to do list.
 *
 * Created by adam.mcneilly on 6/17/16.
 */
public class Task implements Serializable {
    /**
     * The description of the task.
     */
    private String description;

    /**
     * Whether or not the task was completed.
     */
    private boolean completed;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
