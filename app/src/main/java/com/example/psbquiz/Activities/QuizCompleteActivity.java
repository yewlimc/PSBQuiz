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
import com.example.psbquiz.R;

import java.util.ArrayList;
import java.util.prefs.Preferences;

public class QuizCompleteActivity extends AppCompatActivity {

    private ListView listView;
    private Button completed_doneButton;
    TextView scores;
    TextView f_scores,s_scores,t_scores;
    int lastscore;
    int best1, best2, best3;

    ArrayList<AnsweredQuestions> answeredQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_complete);

        scores = (TextView) findViewById(R.id.scores);
        f_scores = (TextView) findViewById(R.id.f_scores);
        s_scores = (TextView) findViewById(R.id.s_scores);
        t_scores = (TextView) findViewById(R.id.t_scores);


        int score = getIntent().getIntExtra("RIGHT_ANSWER_COUNT",0);
        SharedPreferences Preferences = getSharedPreferences("QUIZ_DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = Preferences.edit();
        editor.putInt("Score",score);
        editor.apply();
        int scores1 = Preferences.getInt("scores",0);
        scores1 += score;

        scores.setText("Score: "+score+"/100");

        best1 = Preferences.getInt("best1",0);
        best2 = Preferences.getInt("best2",0);
        best3 = Preferences.getInt("best3",0);

        if(score > best3){
            best3 = score;
            SharedPreferences.Editor editor1 = Preferences.edit();
            editor1.putInt("best3",best3);
            editor1.apply();
        }

        if(score > best2){
            int temp = best2;
            best2 = score;
            best3 = temp;
            SharedPreferences.Editor editor2 = Preferences.edit();
            editor2.putInt("best3",best3);
            editor2.putInt("best2",best2);
            editor2.apply();
        }

        if(score > best1){
            int temp = best1;
            best1 = score;
            best2 = temp;
            SharedPreferences.Editor editor3 = Preferences.edit();
            editor3.putInt("best2",best2);
            editor3.putInt("best1",best1);
            editor3.apply();
        }


        f_scores.setText("1st Score: "+best1+"/100");
        s_scores.setText("2nd Score: "+best2+"/100");
        t_scores.setText("3rd Score: "+best3+"/100");


        Intent intent = getIntent();

        Bundle extras = getIntent().getExtras();

//        answeredQuestions = extras.getParcelableArrayList("aL");

//        Log.v("Completed AL", answeredQuestions.toString());

//        Log.v("Completed AL First Question", answeredQuestions.get(0).getQuestion());

//        listView = findViewById(R.id.listView);
 //       listView.setAdapter(new QuizCompletedAdapter(this, R.layout.list_completequiz, answeredQuestions));

        completed_doneButton = findViewById(R.id.completed_doneButton);
        completed_doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(home);
            }
        });

    }
}
