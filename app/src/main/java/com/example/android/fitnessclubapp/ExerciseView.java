package com.example.android.fitnessclubapp;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ExerciseView extends AppCompatActivity {

    private TextView name;
    private TextView steps;
    private TextView playButton;
    private TextView timer;
    private RelativeLayout layout;
    private Button button;
    private DBHelper helper;
    long startTime = 0;
    Handler timerHandler = new Handler();

    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            timer.setText(String.format("%d:%02d", minutes, seconds));

            timerHandler.postDelayed(this, 500);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_view);
        timer = (TextView) findViewById(R.id.timer);
        button = (Button)findViewById(R.id.startActivity);
        name = (TextView) findViewById(R.id.view_exercise_name);
        steps = (TextView) findViewById(R.id.view_steps);
        playButton = (TextView)findViewById(R.id.video_id);
        helper = new DBHelper(this);
        layout = (RelativeLayout) findViewById(R.id.relativeLayout);
        button.setText("start");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                if (b.getText().equals("stop")) {
                    timerHandler.removeCallbacks(timerRunnable);
                    String Date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
                    HistoryObject object= new HistoryObject(name.getText().toString(),Date,timer.getText().toString());
                    helper.insertRecord(object);
                    finish();

                    b.setText("start");
                } else {
                    startTime = System.currentTimeMillis();
                    timerHandler.postDelayed(timerRunnable, 0);
                    b.setText("stop");
                }
            }
        });
        final Bundle bundle = getIntent().getBundleExtra("exercise");
        name.setText(bundle.getString("name"));
        String[] STEPS = bundle.getStringArray("steps");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String list = "";
        for (int i = 0; i < STEPS.length; i++) {
            list += i + 1 + ". " + STEPS[i] + "\n";
        }
        steps.setText(list);

        layout.setBackground(getDrawable(bundle.getInt("resource")));
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                watchYoutubeVideo(bundle.getString("key"));
            }
        });
    }

    public void watchYoutubeVideo(String id){
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.youtube.com/watch?v=" + id));
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            startActivity(webIntent);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timerHandler.removeCallbacks(timerRunnable);
        Button b = (Button)findViewById(R.id.startActivity);
        b.setText("start");
    }
}
