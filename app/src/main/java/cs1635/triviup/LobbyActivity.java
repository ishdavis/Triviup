package cs1635.triviup;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class LobbyActivity extends AppCompatActivity {

    TextView timeUntilStart;
    public int roundStartHour;
    public int roundStartMinute;
    public long remainingMillis;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        timeUntilStart = (TextView) findViewById(R.id.time_until_start);

        Intent intent = getIntent();
        roundStartHour = intent.getIntExtra("StartHour", -1);
        roundStartMinute = intent.getIntExtra("StartMinute", -1);

        generateTime();
        startCounter();

        mRecyclerView = (RecyclerView) findViewById(R.id.card_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        MyAdapter myAdapter = new MyAdapter(createList());
        mRecyclerView.setAdapter(myAdapter);
    }

    // Calculates the time until round start in H:mm:ss
    public void generateTime() {
        final Calendar calendar = Calendar.getInstance();       // Grab the current hours, minutes, and seconds
        int seconds = calendar.get(Calendar.SECOND);
        int minute = calendar.get(Calendar.MINUTE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        SimpleDateFormat df = new SimpleDateFormat("H:mm:ss");  // Convert all of the times to Strings
        String startMinutes = String.valueOf(roundStartMinute);
        String startHours = String.valueOf(roundStartHour);

        String currentMinutes = String.valueOf(minute);
        String currentHours = String.valueOf(hour);
        String currentSeconds = String.valueOf(seconds);

        if (roundStartMinute < 10)
            startMinutes = "0" + roundStartMinute;  // Pad on a 0 if necessary
        if (minute < 10) currentMinutes = "0" + minute;

        // Generate the start time milliseconds and current time milliseconds
        try {
            Date startTime, currentTime;
            startTime = df.parse(startHours + ":" + startMinutes + ":00");
            currentTime = df.parse(currentHours + ":" + currentMinutes + ":" + currentSeconds);
            remainingMillis = startTime.getTime() - currentTime.getTime();

            updateDisplayTimer();

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    // Starts the actual counter
    public void startCounter() {
        new CountDownTimer(remainingMillis, 1000) {
            public void onTick(long millisecondsUntilFinished) {
                remainingMillis = millisecondsUntilFinished;
                updateDisplayTimer();
            }

            @Override
            public void onFinish() {
                timeUntilStart.setText("0:00:00");
            }
        }.start();
    }

    // Updates the
    public void updateDisplayTimer() {
        int remainingHours = (int) (remainingMillis / (1000 * 60 * 60)) % 24;
        int remainingMinutes = (int) (remainingMillis / (1000 * 60)) % 60;
        int remainingSeconds = (int) (remainingMillis / 1000) % 60;

        String timerText = String.valueOf(remainingHours) + ":";

        // Format the minutes section (add a 0 if under 10 minutes)
        if (remainingMinutes < 10) {
            timerText += "0" + String.valueOf(remainingMinutes);
        } else {
            timerText += String.valueOf(remainingMinutes);
        }

        // Format the seconds section (add a 0 if under 10 seconds)
        if (remainingSeconds < 10) {
            timerText += ":0" + String.valueOf(remainingSeconds);
        } else {
            timerText += ":" + String.valueOf(remainingSeconds);
        }

        timeUntilStart.setText(timerText);
    }


    private List<TeamInfo> createList() {

        List<String> teamNames = new ArrayList<>();
        teamNames.addAll(Arrays.asList("Les Quizerables", "The Quizzard of Oz", "Suck It, Trebek", "Team Awesome", "You're a Quizard, Harry", "E=MC Hammer", "We R Smart", "Let's Get Quizzical", "Cunning Linguists", "Colors That End in Urple", "The Quizzinators", "Show Me the Monet"));

        List<TeamInfo> result = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < teamNames.size(); i++) {
            TeamInfo teamInfo = new TeamInfo();
            teamInfo.teamName = teamNames.get(i);
            teamInfo.teamSize = rand.nextInt(4) + 2;

            result.add(teamInfo);
        }

        return result;
    }
}
