package com.udacity.gradle.builditbigger.test;

import android.app.Instrumentation;
import android.app.PendingIntent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.UiThreadTest;
import android.widget.Button;
import android.widget.TextView;

import com.example.JokeGenerator;
import com.udacity.gradle.builditbigger.MainActivity;
import com.udacity.gradle.builditbigger.R;

import lib.joke.dragoonart.jokeandroidactivitylib.JokeActivity;

/**
 * Created by DragooNART on 1/2/2016.
 */
public class ApplicationTest_alt extends ActivityInstrumentationTestCase2<MainActivity> {

    public ApplicationTest_alt() {
        super(MainActivity.class);
    }

    private JokeActivity jokeActivity;
    private MainActivity activity;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
    }

    @UiThreadTest
    public void testClickButton() {
        Button jokeButton = (Button)activity.findViewById(R.id.jokeButton);
        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(JokeActivity.class.getName(),
                        null, false);
        TouchUtils.clickView(this,jokeButton);
        jokeActivity = (JokeActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(5000);
        assertNotNull("JokeActivity is null", jokeActivity);
        assertEquals("Monitor for JokeActivity has not been called",
                1, receiverActivityMonitor.getHits());
        assertEquals("Activity is of wrong type",
                JokeActivity.class, jokeActivity.getClass());
        TextView text = (TextView)jokeActivity.findViewById(R.id.jokeTextView);
        assertTrue("Result is not joke! result: "+text.getText(),JokeGenerator.isStringJoke(text.getText().toString()));
        assertNotNull(text.getText());

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        if(activity!=null) {
            activity.finish();
        }
        if(jokeActivity!=null) {
            jokeActivity.finish();
        }
    }
}
