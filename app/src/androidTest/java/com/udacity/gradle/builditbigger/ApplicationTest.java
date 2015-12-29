package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.UiThreadTest;
import android.widget.Button;

import com.example.JokeGenerator;

import java.util.concurrent.ExecutionException;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ActivityUnitTestCase {

    private MainActivity activity;

    public ApplicationTest() {
        super(MainActivity.class);
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        startActivity(new Intent(getInstrumentation().getTargetContext(), MainActivity.class), null, null);
        activity = (MainActivity) getActivity();
    }
    @UiThreadTest
    public void testClickButton() {
        Button jokeButton = (Button)activity.findViewById(R.id.jokeButton);
        jokeButton.performClick();
        int currentJokeCount  = JokeGenerator.getInstance().getJokeCount();
        assertEquals(currentJokeCount+1,JokeGenerator.getInstance().getJokeCount());

    }

    public void testAsyncTask() throws ExecutionException, InterruptedException {
        EndpointsAsyncTask task = new EndpointsAsyncTask(activity);
        task.execute("tt");
        String result = task.get();
        assertNotNull(result);
        assertNotSame("",result);

    }
}