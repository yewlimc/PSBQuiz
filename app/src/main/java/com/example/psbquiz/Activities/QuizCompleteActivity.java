package com.example.psbquiz.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.example.psbquiz.Adapters.QuizCompletedAdapter;
import com.example.psbquiz.Models.AnsweredQuestions;
import com.example.psbquiz.Activities.history;
import com.example.psbquiz.R;

import java.util.ArrayList;

public class QuizCompleteActivity extends AppCompatActivity {

    private ListView listView;
    private Button completed_doneButton;
    TextView scores,c_scores;

    ArrayList<AnsweredQuestions> answeredQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_complete);

        scores = (TextView) findViewById(R.id.scores);

        int score = getIntent().getIntExtra("RIGHT_ANSWER_COUNT",0);
        SharedPreferences sharePreferences = getSharedPreferences("QUIZ_DATA", Context.MODE_PRIVATE);
        int scores1 = sharePreferences.getInt("scores",0);

        scores1 += score;
        scores.setText("SCORES: "+score+"/10");


        Intent intent = getIntent();

        Bundle extras = getIntent().getExtras();

        answeredQuestions = extras.getParcelableArrayList("aL");

        Log.v("Completed AL", answeredQuestions.toString());

        Log.v("Completed AL First Question", answeredQuestions.get(0).getQuestion());

        listView = findViewById(R.id.listView);
        listView.setAdapter(new QuizCompletedAdapter(this, R.layout.list_completequiz, answeredQuestions));

        completed_doneButton = findViewById(R.id.completed_doneButton);
        completed_doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent history = new Intent(getApplicationContext(), history.class);
                startActivity(history);
            }
        });

    }
}
