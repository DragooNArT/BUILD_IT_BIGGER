package com.udacity.gradle.builditbigger.test;

import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityUnitTestCase;
import android.test.TouchUtils;
import android.test.UiThreadTest;
import android.widget.Button;
import android.widget.TextView;

import com.example.JokeGenerator;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.MainActivity;

import java.util.concurrent.ExecutionException;

import lib.joke.dragoonart.jokeandroidactivitylib.JokeActivity;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ActivityInstrumentationTestCase2<MainActivity> {


    public ApplicationTest() {
        super(MainActivity.class);
    }

    @UiThreadTest
    public void testAsyncTask() throws ExecutionException, InterruptedException {
        EndpointsAsyncTask task = new EndpointsAsyncTask(getActivity());
        int jokesCount = JokeGenerator.getInstance().getJokeCount();
        task.execute("tt");
        String result = task.get();
        assertNotNull(result);
        assertNotSame("", result);

    }
}