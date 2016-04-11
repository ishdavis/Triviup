package cs1635.triviup;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import java.util.ArrayList;
import org.parceler.Parcel;
import org.parceler.Parcels;


/**
 * Created by Ishdavis on 3/21/2016.
 */
public class QuestionAdapter extends
        RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView question;
        public ImageView delete, edit;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            question = (TextView) itemView.findViewById(R.id.question);
            delete = (ImageView)itemView.findViewById(R.id.delete);
            edit = (ImageView)itemView.findViewById(R.id.edit);
        }
    }

    protected ArrayList<Question> questions;
    Context context;

    public QuestionAdapter(ArrayList<Question> questions, Context context){
        this.questions = questions;
        this.context = context;
    }

    public ArrayList<Question> getData(){
        return questions;
    }

    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.questionrow, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(QuestionAdapter.ViewHolder viewHolder, int position) {
        TextView text = viewHolder.question;
        text.setText(questions.get(position).getQuestion());
        ImageView deleter = viewHolder.delete;
        ImageView editer = viewHolder.edit;
        final int pos = position;
        //allow user to edit the selected question
        editer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, addQuestion.class);
                Question temp = questions.get(pos);

                i.putExtra("position", pos);
                i.putExtra("question", temp.getQuestion());
                i.putExtra("correct", temp.getCorrectAnswer());
                i.putExtra("incorrect1", temp.getIncorrect1());
                i.putExtra("incorrect2", temp.getIncorrect2());
                i.putExtra("incorrect3", temp.getIncorrect3());
                i.putExtra("positionChecked", temp.getPosition());

                Parcelable wrapped = Parcels.wrap(questions);
                i.putExtra("qList", wrapped);
                context.startActivity(i);
            }
        });
        //If delete is selected remove the question
        deleter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questions.remove(pos);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }
}
