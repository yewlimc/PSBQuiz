package com.example.psbquiz.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.psbquiz.Adapters.QuizCompletedAdapter;
import com.example.psbquiz.Models.AnsweredQuestions;
import com.example.psbquiz.R;

import java.util.ArrayList;

public class QuizCompleteActivity extends AppCompatActivity {

    private ListView listView;
    private Button completed_doneButton;

    ArrayList<AnsweredQuestions> answeredQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_complete);

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
                Intent home = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(home);
                finish();
            }
        });

    }
}
