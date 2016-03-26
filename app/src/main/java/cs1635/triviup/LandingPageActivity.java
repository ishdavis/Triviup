package cs1635.triviup;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class LandingPageActivity extends FragmentActivity
    implements LandingPageInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        Intent myIntent = getIntent();
        String landingType = myIntent.getStringExtra("Type");
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if (landingType.equals("user")) {
            UserLandingFragment mainFragment = new UserLandingFragment();
            ft.add(R.id.bodyfragment, mainFragment);
            ft.commit();
        } else {
            HostLandingFragment mainFragment = new HostLandingFragment();
            ft.add(R.id.bodyfragment, mainFragment);
            ft.commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void tempStartAnswerActivity(View v) {
        Intent intent = new Intent(this, AnswerQuestionActivity.class);
        startActivity(intent);
    }

    public void editQuestionListener(View v) {
        Intent intent = new Intent(this, questionScreen.class);
        startActivity(intent);
    }

    public void newGameListener(View v) {
        Intent intent = new Intent(this, RoundCreationActivity.class);
        startActivity(intent);
    }
}
