package com.adammcneilly.todolist;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the storage of to do list items.
 *
 * Created by adam.mcneilly on 6/17/16.
 */
public class Storage {
    /**
     * Log Tag for this class to easily filter for errors in the logcat.
     */
    private static final String LOG_TAG = Storage.class.getSimpleName();

    /**
     * The name of the file to read from/write to list items.
     */
    private static final String FILE_NAME = "todo_list.ser";

    /**
     * Writes a list of tasks to a file.
     */
    public static void writeData(Context context, List<Task> tasks) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            // Open file and write list
            fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
        } catch(Exception e) {
            Log.e(LOG_TAG, "Could not write to file.");
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch(Exception e) {
                Log.e(LOG_TAG, "Could not close the file.");
                e.printStackTrace();
            }
        }
    }

    /**
     * Reads a list of tasks from a file.
     */
    public static List<Task> readData(Context context) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        List<Task> tasks = new ArrayList<>();

        try {
            // Open file and read list
            fis = context.openFileInput(FILE_NAME);
            ois = new ObjectInputStream(fis);

            //noinspection unchecked
            tasks = (List<Task>) ois.readObject();
        } catch(Exception e ) {
            Log.e(LOG_TAG, "Could not read from file.");
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                assert fis != null;
                fis.close();
            } catch(Exception e) {
                Log.e(LOG_TAG, "Could not close the file.");
                e.printStackTrace();
            }
        }

        return tasks;
    }
}
