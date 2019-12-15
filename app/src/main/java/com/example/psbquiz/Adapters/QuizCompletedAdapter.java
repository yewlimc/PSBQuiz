package com.example.psbquiz.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.psbquiz.Models.AnsweredQuestions;
import com.example.psbquiz.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class QuizCompletedAdapter extends ArrayAdapter<AnsweredQuestions> {

    private int resource;
    private ArrayList<AnsweredQuestions> answeredQuestions;
    private Context context;

    public QuizCompletedAdapter(@NonNull Context context, int resource, ArrayList<AnsweredQuestions> answeredQuestions) {
        super(context, resource, answeredQuestions);
        this.resource = resource;
        this.answeredQuestions = answeredQuestions;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(resource, parent, false);

        TextView question = v.findViewById(R.id.ADAPTER_questionText);
        TextView answered = v.findViewById(R.id.ADAPTER_answeredText);
        TextView correctAnswer = v.findViewById(R.id.ADAPTER_correctAnswerText);

        question.setText(answeredQuestions.get(position).getQuestion());
        answered.setText(answeredQuestions.get(position).getAnswered());
        correctAnswer.setText(answeredQuestions.get(position).getCorrectAnswer());

        return v;
    }
}
