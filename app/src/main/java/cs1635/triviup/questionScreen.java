package cs1635.triviup;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import org.parceler.Parcels;

import java.util.ArrayList;

public class questionScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected Button newQuestion;
    protected RecyclerView questions;
    QuestionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        questions = (RecyclerView) findViewById(R.id.questionHolder);
        questions.setHasFixedSize(true);

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        questions.addItemDecoration(itemDecoration);

        //If a question was edited the list will be added to the adapter here
        if(getIntent().getIntExtra("list", -1) != -1){
            Parcelable questions = getIntent().getParcelableExtra("qList");
            ArrayList<Question> questionArrayList = Parcels.unwrap(questions);
            adapter = new QuestionAdapter(questionArrayList, this);
        }
        else{
            adapter = new QuestionAdapter(createQuestions(), this);
        }

        questions.setAdapter(adapter);
        questions.setLayoutManager(new LinearLayoutManager(this));

        newQuestion = (Button)findViewById(R.id.newQuestion);
        newQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(questionScreen.this, addQuestion.class);
                questionScreen.this.startActivityForResult(i, 1);
            }
        });
    }

    //If a new question was added it will be inserted into the current list here
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1){
            String question = data.getStringExtra("question");
            String correct = data.getStringExtra("correct");
            String incorrect1 = data.getStringExtra("incorrect1");
            String incorrect2 = data.getStringExtra("incorrect2");
            String incorrect3 = data.getStringExtra("incorrect3");
            ArrayList<Question> currentQuestions = adapter.getData();

            currentQuestions.add(new Question(question,incorrect2,incorrect1,incorrect3,correct));

            adapter.notifyDataSetChanged();
        }
    }

    //Initially generate some dummy questions
    public ArrayList<Question> createQuestions(){
        ArrayList<Question> questions = new ArrayList<Question>();
        questions.add(new Question("How old am I?", "22", "23", "24", "21"));
        questions.add(new Question("How old am Idddddddddddddddddddddddddddddddddddddddd?", "22", "23", "24", "21"));
        questions.add(new Question("How old am Isssssssssssssssssssssssssssssssssssssssssssssssssss?","22", "23", "24", "21"));
        questions.add(new Question("What is your name?aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","Sam", "Mike", "Allan", "Matt"));
        return questions;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.question_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        Intent i;
        if (id == R.id.nav_camera) {
            //start questions
        } else if (id == R.id.nav_gallery) {
            //start game
            i = new Intent(questionScreen.this, RoundCreationActivity.class);
            questionScreen.this.startActivity(i);
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {
            //start landing
            i = new Intent(questionScreen.this, LandingPageActivity.class);
            i.putExtra("Type", "host");
            questionScreen.this.startActivity(i);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.log_out) {
            i = new Intent(questionScreen.this, LoginActivity.class);
            questionScreen.this.startActivity(i);
        }

        return true;
    }
}
