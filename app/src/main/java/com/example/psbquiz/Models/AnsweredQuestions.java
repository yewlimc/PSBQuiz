package com.example.psbquiz.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class AnsweredQuestions implements Parcelable {

    String question;
    String answered;
    String correctAnswer;

    public AnsweredQuestions(Parcel in) {
        this.question = in.readString();
        this.answered = in.readString();
        this.correctAnswer = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(answered);
        dest.writeString(correctAnswer);
    }

    public static final Creator<AnsweredQuestions> CREATOR = new Creator<AnsweredQuestions>() {
        @Override
        public AnsweredQuestions createFromParcel(Parcel in) {
            return new AnsweredQuestions(in);
        }

        @Override
        public AnsweredQuestions[] newArray(int size) {
            return new AnsweredQuestions[size];
        }
    };

    public AnsweredQuestions(String question, String answered, String correctAnswer) {
        this.question = question;
        this.answered = answered;
        this.correctAnswer = correctAnswer;
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswered() {
        return answered;
    }

    public void setAnswered(String answered) {
        this.answered = answered;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }


}
