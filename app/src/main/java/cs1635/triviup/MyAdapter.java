package cs1635.triviup;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Jonathan on 3/21/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.TeamViewHolder> {
    private List<TeamInfo> teamList;

    public MyAdapter(List<TeamInfo> contactList) {
        this.teamList = contactList;
    }

    @Override
    public void onBindViewHolder(TeamViewHolder teamViewHolder, int i) {
        TeamInfo teamInfo = teamList.get(i);
        teamViewHolder.vTeamName.setText(teamInfo.teamName);
        teamViewHolder.vTeamSize.setText(String.valueOf(teamInfo.teamSize));

    }

    @Override
    public TeamViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_view, viewGroup, false);

        return new TeamViewHolder(itemView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return teamList.size();
    }

    public static class TeamViewHolder extends RecyclerView.ViewHolder {
        protected TextView vTeamName;
        protected TextView vTeamSize;

        public TeamViewHolder(View v) {
            super(v);
            vTeamName = (TextView) v.findViewById(R.id.team_name);
            vTeamSize = (TextView) v.findViewById(R.id.team_size_points);
        }
    }
}
