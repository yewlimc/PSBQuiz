package com.example.psbquiz.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.psbquiz.R;

public class QuizPreparationActivity extends AppCompatActivity {

    String module;
    String course;

    private TextView moduleText;
    private Button startButton;

    public static final String courseKey = "courseKey";
    public static final String moduleKey = "moduleKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_preparation);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        course = intent.getStringExtra(QuizMenuActivity.userCourse);
        module = intent.getStringExtra(QuizMenuActivity.moduleKey);
        Log.v("Module: ", module);

        moduleText = findViewById(R.id.moduleText);
        moduleText.setText(module);

        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), QuizGameActivity.class);

                startIntent.putExtra(moduleKey, module);
                startIntent.putExtra(courseKey, course);
                Bundle bundle = new Bundle();
                bundle.putString(moduleKey, module);
                bundle.putString(courseKey, course);
                startIntent.putExtras(bundle);

                startActivity(startIntent);
                finish();
            }
        });
    }
}
