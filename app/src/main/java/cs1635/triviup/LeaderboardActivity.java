package cs1635.triviup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LeaderboardActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<TeamInfo> teams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        Intent intent = getIntent();
        teams = (ArrayList<TeamInfo>) intent.getSerializableExtra("Teams");

        Collections.sort(teams, new Comparator<TeamInfo>() {
            @Override
            public int compare(TeamInfo lhs, TeamInfo rhs) {
                return rhs.roundPoints - lhs.roundPoints;
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.card_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        myAdapter = new LeaderboardAdapter(this.teams);
        mRecyclerView.setAdapter(myAdapter);
    }
}
