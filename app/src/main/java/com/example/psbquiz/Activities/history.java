package com.example.psbquiz.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;

import com.example.psbquiz.Activities.QuizCompleteActivity;
import com.example.psbquiz.Activities.QuizGameActivity;


import android.os.Bundle;

import com.example.psbquiz.R;

public class history extends AppCompatActivity {

    public TextView c_scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        c_scores = (TextView) findViewById(R.id.c_scores);

        int score = getIntent().getIntExtra("RIGHT_ANSWER_COUNT",0);
        SharedPreferences sharePreferences = getSharedPreferences("QUIZ_DATA", Context.MODE_PRIVATE);
        int scores1 = sharePreferences.getInt("c_scores",0);

        scores1 += score;
        c_scores.setText("SCORES: "+score+"/10");

    }
}
