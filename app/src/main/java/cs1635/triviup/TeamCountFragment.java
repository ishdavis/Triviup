package cs1635.triviup;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.NumberPicker;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeamCountFragment extends DialogFragment implements NumberPicker.OnValueChangeListener {
    private OnCompleteListener mListener;

    private NumberPicker teamCountNumberPicker;
    private int selectedMaxSize;

    public TeamCountFragment() {
        // Required empty public constructor
    }


    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        selectedMaxSize = newVal;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_team_count, null);

        teamCountNumberPicker = (NumberPicker) view.findViewById(R.id.team_number_picker);
        teamCountNumberPicker.setMinValue(0);
        teamCountNumberPicker.setMaxValue(100);
        teamCountNumberPicker.setWrapSelectorWheel(true);
        teamCountNumberPicker.setOnValueChangedListener(this);
        builder.setView(view);

        builder.setTitle("Maximum Number of Teams");

        builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int button) {
                String maxTeams = String.valueOf(selectedMaxSize);
                mListener.onComplete("MaxTeams", maxTeams);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int button) {
                onCancel(dialog);
            }
        });

        return builder.create();
    }

    // make sure the Activity implemented it
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnCompleteListener) activity;
        } catch (final ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnCompleteListener");
        }
    }
}
