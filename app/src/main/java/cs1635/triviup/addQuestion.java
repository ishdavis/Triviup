package cs1635.triviup;

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
import org.parceler.Parcel;
import org.parceler.Parcels;

import java.util.ArrayList;


public class addQuestion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText correct = (EditText)findViewById(R.id.correct);
        final EditText question = (EditText)findViewById(R.id.questionFinished);
        final EditText ic1 = (EditText)findViewById(R.id.incorrect1);
        final EditText ic2 = (EditText)findViewById(R.id.incorrect2);
        final EditText ic3 = (EditText)findViewById(R.id.incorrect3);

        //If this activity was started to edit a question
        if(getIntent().getIntExtra("position", -1) != -1){
            correct.setText(getIntent().getStringExtra("correct"));
            correct.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            question.setText(getIntent().getStringExtra("question"));
            question.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            ic1.setText(getIntent().getStringExtra("incorrect1"));
            ic1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            ic2.setText(getIntent().getStringExtra("incorrect2"));
            ic2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            ic3.setText(getIntent().getStringExtra("incorrect3"));
            ic3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }

        Button finished = (Button)findViewById(R.id.Save);
        finished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    setResult(1, returnIntent);
                    finish();
                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
