package cs1635.triviup;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MatchHistoryAdapter extends RecyclerView.Adapter<MatchHistoryAdapter.MatchViewHolder> {
    private String[] matches;

    public static class MatchViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        CardView cv;
        public MatchViewHolder(View v) {
            super(v);
            cv = (CardView) v.findViewById(R.id.card);
            mTextView = (TextView) v.findViewById(R.id.matchTitle);
        }
    }

    public MatchHistoryAdapter(String[] matchHistory) {
        matches = matchHistory;
    }

    @Override
    public MatchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_row, parent, false);
        MatchViewHolder vh = new MatchViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(MatchViewHolder holder, int position) {
        holder.mTextView.setText(matches[position]);
    }

    @Override
    public int getItemCount() {
        return matches.length;
    }
}
