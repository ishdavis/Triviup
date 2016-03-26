package cs1635.triviup;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class AnswerQuestionActivity extends Activity {

    TextView timerText;
    Button submitButton;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_question);
        submitButton = (Button) findViewById(R.id.submitAnswerButton);
        rg = (RadioGroup) findViewById(R.id.answerRadioGroup);
        timerText = (TextView) findViewById(R.id.timerText);
        cdt().start();
    }

    private CountDownTimer cdt() {
        return new CountDownTimer(90000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long min = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                long sec = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished - TimeUnit.MINUTES.toMillis(min));
                timerText.setText(String.format("%02d:%02d", min, sec));
                if (min == 0 && sec <= 30) {
                    timerText.setTextColor(Color.RED);
                }
            }

            @Override
            public void onFinish() {
                timerText.setText("DONE");
                submitButton.setEnabled(false);
                rg.setEnabled(false);
            }
        };
    }
}
