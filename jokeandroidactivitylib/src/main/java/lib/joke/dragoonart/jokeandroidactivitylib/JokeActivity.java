package lib.joke.dragoonart.jokeandroidactivitylib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String JOKE_EXTRA_ID = "JOKE_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            String joke = extras.getString(JOKE_EXTRA_ID);
            TextView textView = (TextView)findViewById(R.id.jokeTextView);
            textView.setText(joke);
        }
    }
}
