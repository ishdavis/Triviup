package cs1635.triviup;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionTimerFragment extends DialogFragment implements NumberPicker.OnValueChangeListener {
    private OnCompleteListener mListener;

    NumberPicker minuteNumberPicker;
    NumberPicker secondsNumberPicker;
    TextView currentMinutesSelected;
    TextView currentSecondsSelected;
    EditText questionTimerText;

    public QuestionTimerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

        if (picker == minuteNumberPicker) {
            String newMinutes = String.valueOf(newVal);
            currentMinutesSelected.setText(newMinutes);
        } else if (picker == secondsNumberPicker) {
            String newSeconds = String.valueOf(newVal);
            currentSecondsSelected.setText(newSeconds);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_question_timer, null);
        final View roundCreationView = getActivity().getLayoutInflater().inflate(R.layout.activity_round_creation, null);

        questionTimerText = (EditText) roundCreationView.findViewById(R.id.question_timer);
        currentMinutesSelected = (TextView) view.findViewById(R.id.current_minutes_selected);
        currentSecondsSelected = (TextView) view.findViewById(R.id.current_seconds_selected);

        minuteNumberPicker = (NumberPicker) view.findViewById(R.id.minute_number_picker);
        minuteNumberPicker.setMaxValue(10);
        minuteNumberPicker.setMinValue(0);
        minuteNumberPicker.setWrapSelectorWheel(false);
        minuteNumberPicker.setOnValueChangedListener(this);

        secondsNumberPicker = (NumberPicker) view.findViewById(R.id.second_number_picker);
        secondsNumberPicker.setMinValue(0);
        secondsNumberPicker.setMaxValue(59);
        secondsNumberPicker.setWrapSelectorWheel(false);
        secondsNumberPicker.setOnValueChangedListener(this);
        builder.setView(view);

        builder.setTitle("Question Timer");

        builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int button) {
                String minutes = (String) currentMinutesSelected.getText();
                String seconds = (String) currentSecondsSelected.getText();

                if (Integer.parseInt(seconds) < 10) {
                    seconds = "0" + seconds;
                }

                String timerTime = minutes + ":" + seconds;
                mListener.onComplete("QuestionTimer", timerTime);
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
