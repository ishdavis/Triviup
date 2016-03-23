package cs1635.triviup;

import org.parceler.Parcel;

/**
 * Created by Ishdavis on 3/21/2016.
 */
@Parcel
//Encapsulates a question
public class Question {
    String question;
    String incorrect1;
    String incorrect2;
    String incorrect3;
    String correctAnswer;

    public Question(){}

    public Question(String question, String inc1, String inc2, String inc3, String correctAnswer){
        this.question = question;
        this.incorrect1 = inc1;
        this.incorrect2 = inc2;
        this.incorrect3 = inc3;
        this.correctAnswer = correctAnswer;
    }

    public String getIncorrect1() {
        return incorrect1;
    }

    public void setIncorrect1(String incorrect1) {
        this.incorrect1 = incorrect1;
    }

    public String getIncorrect2() {
        return incorrect2;
    }

    public void setIncorrect2(String incorrect2) {
        this.incorrect2 = incorrect2;
    }

    public String getIncorrect3() {
        return incorrect3;
    }

    public void setIncorrect3(String incorrect3) {
        this.incorrect3 = incorrect3;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

}
