package cs1635.triviup;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import java.util.Calendar;

public class RoundCreationActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener, OnCompleteListener {

    public EditText startTimeText;
    public EditText questionTimerText;
    public EditText maxTeamCountText;
    public int hour, minute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_creation);

        startTimeText = (EditText) findViewById(R.id.start_time);
        questionTimerText = (EditText) findViewById(R.id.question_timer);
        maxTeamCountText = (EditText) findViewById(R.id.max_team_count);

        startTimeText.setOnClickListener(this);
        questionTimerText.setOnClickListener(this);
        maxTeamCountText.setOnClickListener(this);

        startTimeText.setOnFocusChangeListener(this);
        questionTimerText.setOnFocusChangeListener(this);
        maxTeamCountText.setOnFocusChangeListener(this);
    }

    // When the edit text is clicked on while already focused, handle the appropriate action
    @Override
    public void onClick(View v) {
        if (v == startTimeText && v.isFocused()) {
            handleStartTimePicker();
        } else if (v == questionTimerText && v.isFocused()) {
            handleQuestionTimeLimit();
        } else if (v == maxTeamCountText && v.isFocused()) {
            handleMaxNumberOfTeams();
        }
    }

    // When the edit text is focused for the first time, perform the appropriate action
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (v == startTimeText && hasFocus) {
            handleStartTimePicker();
        } else if (v == questionTimerText && hasFocus) {
            handleQuestionTimeLimit();
        } else if (v == maxTeamCountText && hasFocus) {
            handleMaxNumberOfTeams();
        }
    }

    // Handles the time picker for the round start time
    public void handleStartTimePicker() {
        final Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                setStartTime(hourOfDay, minute);
            }
        }, hour, minute, false);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

    // Create and display the minutes/seconds question timer picker
    public void handleQuestionTimeLimit(){
        QuestionTimerFragment questionTimerFragment = new QuestionTimerFragment();
        questionTimerFragment.show(getFragmentManager(), "JMB");
    }

    public void handleMaxNumberOfTeams(){
        TeamCountFragment teamCountFragment = new TeamCountFragment();
        teamCountFragment.show(getFragmentManager(), "JMB");
    }

    // Converts time to 12 hour format - 22:15 becomes 10:15 PM
    private void setStartTime(int hourOfDay, int minute) {
        String amPm = "AM";
        String minutes;

        if (hourOfDay > 12) {
            hourOfDay -= 12;
            amPm = "PM";
        } else if (hourOfDay == 12) {
            amPm = "PM";
        } else if (hourOfDay == 0) {
            hourOfDay += 12;
        }

        if (minute < 10) {
            minutes = "0" + minute;
        } else {
            minutes = String.valueOf(minute);
        }
        startTimeText.setText(hourOfDay + ":" + minutes + " " + amPm);
    }

    // Receive the selected text to display from the dialog fragments
    @Override
    public void onComplete(String from, String text) {
        if(from.equals("QuestionTimer"))    questionTimerText.setText(text);
        if(from.equals("MaxTeams"))         maxTeamCountText.setText(text);
    }
}
