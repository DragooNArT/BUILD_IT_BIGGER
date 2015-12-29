package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dragoonart.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import lib.joke.dragoonart.jokeandroidactivitylib.JokeActivity;

class EndpointsAsyncTask extends AsyncTask<String, ProgressBar, String> {
    private static MyApi myApiService = null;
    private Context context;
    private MainActivity activity;ProgressBar spinner;

    public EndpointsAsyncTask(MainActivity activity) {
        this.activity = activity;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        spinner =  (ProgressBar)activity.findViewById(R.id.progressBar1);
        spinner.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(String... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                // options for running against local devappserver
                // - 10.0.2.2 is localhost's IP address in Android emulator
                // - turn off compression when running against local devappserver
                .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                        abstractGoogleClientRequest.setDisableGZipContent(true);
                    }
                });
                // end options for devappserver

            myApiService = builder.build();
        }

       // context = params[0].first;
        String name = params[0];

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Intent intent = new Intent(activity, JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_EXTRA_ID,result);
        spinner.setVisibility(View.GONE);
        activity.startActivity(intent);
    }
}