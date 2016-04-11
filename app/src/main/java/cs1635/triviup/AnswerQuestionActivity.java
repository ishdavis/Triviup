package cs1635.triviup;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class AnswerQuestionActivity extends Activity {

    TextView timerText;
    Button submitButton;
    RadioGroup rg;
    boolean submitted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_question);
        submitButton = (Button) findViewById(R.id.submitAnswerButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "Answer Submitted";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                timerText.setText("DONE");
                submitButton.setEnabled(false);
                rg.setEnabled(false);
                submitted = true;
            }
        });
        rg = (RadioGroup) findViewById(R.id.answerRadioGroup);
        timerText = (TextView) findViewById(R.id.timerText);
        cdt().start();
    }

    private CountDownTimer cdt() {
        return new CountDownTimer(90000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(submitted == false) {
                    long min = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                    long sec = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished - TimeUnit.MINUTES.toMillis(min));
                    timerText.setText(String.format("%02d:%02d", min, sec));
                    if (min == 0 && sec <= 30) {
                        timerText.setTextColor(Color.RED);
                    }
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
