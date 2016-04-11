package cs1635.triviup;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import org.parceler.Parcel;
import org.parceler.Parcels;

import java.util.ArrayList;


public class addQuestion extends AppCompatActivity {

    public RadioButton r1, r2, r3, r4;
    public EditText correct, ic1, ic2, ic3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        correct = (EditText) findViewById(R.id.correct);
        final EditText question = (EditText)findViewById(R.id.questionFinished);
        ic1 = (EditText)findViewById(R.id.incorrect1);
        ic2 = (EditText)findViewById(R.id.incorrect2);
        ic3 = (EditText)findViewById(R.id.incorrect3);

         r1 = (RadioButton)findViewById(R.id.correctRadio);
         r2 = (RadioButton)findViewById(R.id.incorrect1Radio);
         r3 = (RadioButton)findViewById(R.id.incorrect2Radio);
         r4 = (RadioButton)findViewById(R.id.incorrect3Radio);

        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseRadio(0);
            }
        });

        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseRadio(1);
            }
        });

        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseRadio(2);
            }
        });

        r4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseRadio(3);
            }
        });

        //If this activity was started to edit a question
        if(getIntent().getIntExtra("position", -1) != -1){
            correct.setText(getIntent().getStringExtra("correct"));

            question.setText(getIntent().getStringExtra("question"));
            question.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            ic1.setText(getIntent().getStringExtra("incorrect1"));

            ic2.setText(getIntent().getStringExtra("incorrect2"));

            ic3.setText(getIntent().getStringExtra("incorrect3"));

            chooseRadio(getIntent().getIntExtra("positionChecked", -1));
        }

        Button finished = (Button)findViewById(R.id.Save);
        finished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!checkAnySelected()){
                    Context context = getApplicationContext();
                    CharSequence text = "Please select a correct answer";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return;
                }
                if(!checkAnswers()){
                    Context context = getApplicationContext();
                    CharSequence text = "Please give 4 answer options";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return;
                }


                String sQuestion = question.getText().toString();
                String sCorrect = correct.getText().toString();
                String sIC1 = ic1.getText().toString();
                String sIC2 = ic2.getText().toString();
                String sIC3 = ic3.getText().toString();

                //If this activity was started to edit a question
                if(getIntent().getIntExtra("position", -1) != -1){
                    Parcelable wrapped = getIntent().getParcelableExtra("qList");
                    ArrayList<Question> q = Parcels.unwrap(wrapped);
                    Question temp = q.get(getIntent().getIntExtra("position", -1));

                    temp.setQuestion(sQuestion);
                    temp.setCorrectAnswer(sCorrect);
                    temp.setIncorrect2(sIC2);
                    temp.setIncorrect1(sIC1);
                    temp.setIncorrect3(sIC3);
                    temp.setPosition(getChecked());

                    Intent i = new Intent(addQuestion.this, questionScreen.class);
                    i.putExtra("list", 1);
                    Parcelable qList = Parcels.wrap(q);
                    i.putExtra("qList", qList);
                    addQuestion.this.startActivity(i);
                }
                //if this activity was started to add a question
                else {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("question", sQuestion);
                    returnIntent.putExtra("correct", sCorrect);
                    returnIntent.putExtra("incorrect1", sIC1);
                    returnIntent.putExtra("incorrect2", sIC2);
                    returnIntent.putExtra("incorrect3", sIC3);
                    returnIntent.putExtra("positionChecked", getChecked());
                    setResult(1, returnIntent);
                    finish();
                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    boolean checkAnySelected()
    {
        if(r1.isChecked()){return true;}
        else if(r2.isChecked()){return true;}
        else if(r3.isChecked()){return true;}
        else if(r4.isChecked()){return true;}
        return false;
    }

    boolean checkAnswers(){
        if(correct.getText().length() == 0 || ic1.getText().length() == 0 || ic2.getText().length() == 0 || ic3.getText().length() == 0){
            return false;
        }
        return true;
    }


    public int getChecked(){
        if(r1.isChecked()){return 0;}
        else if(r2.isChecked()){return 1;}
        else if(r3.isChecked()){return 2;}
        else if(r4.isChecked()){return 3;}
        return -1;
    }

    public void chooseRadio(int buttonNum){
        if(buttonNum == 0){
            r1.setChecked(true);
            r2.setChecked(false);
            r3.setChecked(false);
            r4.setChecked(false);
        }
        else if(buttonNum == 1){
            r1.setChecked(false);
            r2.setChecked(true);
            r3.setChecked(false);
            r4.setChecked(false);
        }
        else if(buttonNum == 2){
            r1.setChecked(false);
            r2.setChecked(false);
            r3.setChecked(true);
            r4.setChecked(false);
        }
        else if(buttonNum == 3){
            r1.setChecked(false);
            r2.setChecked(false);
            r3.setChecked(false);
            r4.setChecked(true);
        }
    }

}
