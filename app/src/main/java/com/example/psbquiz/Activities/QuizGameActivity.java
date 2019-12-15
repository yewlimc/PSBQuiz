package com.example.psbquiz.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.psbquiz.Models.AnsweredQuestions;
import com.example.psbquiz.Models.Questions;
import com.example.psbquiz.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class QuizGameActivity extends AppCompatActivity {

    String module;
    String course;

    CountDownTimer countDownTimer;

    private RadioGroup radioGroup;

    private RadioButton buttonA;
    private RadioButton buttonB;
    private RadioButton buttonC;
    private RadioButton buttonD;

    private Button doneButton;
    private TextView answeredTotal;
    private TextView totalQuestions;

    private TextView questionText;

    public ArrayList<AnsweredQuestions> answeredList = new ArrayList<>();

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    public static ArrayList<Questions> questionList = new ArrayList<>();

    int index = 0;
    int thisQ = 0;
    int questionsNum = 10;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_game);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        module = intent.getStringExtra(QuizPreparationActivity.moduleKey);
        course = intent.getStringExtra(QuizPreparationActivity.courseKey);

        Log.v("Module: ", module);
        Log.v("Course: ", course);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Quiz/"+course+"/"+module);
        Log.v("KeyDBREF: ", databaseReference.getKey());

        // textColorDefaultRb = buttonA.getTextColors();

        loadQuestion();

        radioGroup = findViewById(R.id.radio_group);

        questionText = findViewById(R.id.questionText);

        buttonA = findViewById(R.id.answerA);
        buttonB = findViewById(R.id.answerB);
        buttonC = findViewById(R.id.answerC);
        buttonD = findViewById(R.id.answerD);

        answeredTotal = findViewById(R.id.answeredTotal);
        totalQuestions = findViewById(R.id.totalQuestions);
        totalQuestions.setText(Integer.toString(questionsNum));

        // showQuestion(0);

        doneButton = findViewById(R.id.completed_doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(buttonA.isChecked() || buttonB.isChecked() || buttonC.isChecked() || buttonD.isChecked())
                {
                    checkAnswer();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please select an answer. ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void loadQuestion() {

        // questionList.clear();
        Log.v("Key : ", databaseReference.getKey());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snap : dataSnapshot.getChildren())
                {
                    Questions q = snap.getValue(Questions.class);
                    questionList.add(q);
                    Log.v("Question : ", q.getQuestion());
                }

                Log.v("Questions list size loop: ", ""+questionList.size());
                Log.v("Questions list []: ", ""+questionList.toString());

                // Randomize list
                Collections.shuffle(questionList);

                showQuestion(0);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // Randomize list
        Collections.shuffle(questionList);
        Log.v("List: ", questionList.toString());


        // showQuestion(0);
    }

    private void checkAnswer() {

        RadioButton rbSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        int id = radioGroup.indexOfChild(rbSelected);
        String answered = "answer";
        String correctAnswer = questionList.get(index).getCorrectAnswer();
        Log.v("Question check answer: ", questionList.get(index).getQuestion());

        // 0 = A, 1 = B, etc.
        switch(id){
            case 0:
            {
                answered = "answerA";
                break;
            }
            case 1:
            {
                answered = "answerB";
                break;
            }
            case 2:
            {
                answered = "answerC";
                break;
            }
            case 3:
            {
                answered = "answerD";
                break;
            }
        }

        AnsweredQuestions aq = new AnsweredQuestions(questionList.get(index).getQuestion(), answered, correctAnswer);
        answeredList.add(aq);
        Log.v("QuestionsAnswered: ", aq.getQuestion());
        Log.v("QuestionsAnswered: ", aq.getCorrectAnswer());
        Log.v("QuestionsAnswered: ", aq.getAnswered());
        Log.v("answeredList: ", answeredList.toString());

        if (answered.equals(correctAnswer))
        {
            Log.v("Answered: ", "Correct");
            score = score + 1;

            // [question, answered, correctAnswer]
        }
        else
        {
            Log.v("Answered: ", "Incorrect");
        }

        Log.v("Radio Button ID: ", ""+radioGroup.indexOfChild(rbSelected));

        if (index == questionsNum)
        {
            Intent doneIntent = new Intent(getApplicationContext(), QuizCompleteActivity.class);

            Log.v("doneIntentList: ", answeredList.toString());

            doneIntent.putExtra("aL", answeredList);
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("aL", answeredList);

            //doneIntent.putExtra("aL", ArrayList<AnsweredQuestions>answeredList);
//            bundle.putString(courseKey, course);
            doneIntent.putExtras(bundle);

            startActivity(doneIntent);
        }
        else
        {
            // Goes to next question
            showQuestion(++index);
        }


    }

    private void showQuestion(int i) {
        answeredTotal.setText(Integer.toString(index));

        radioGroup.clearCheck();

        if (index < questionsNum)
        {
            Log.v("Questions list size show: ", ""+questionList.size());
            Log.v("Question 1: ", questionList.get(index).getQuestion());
            Log.v("Index: ", ""+index);
            thisQ++;
            questionText.setText(questionList.get(index).getQuestion());
            buttonA.setText(questionList.get(index).getAnswerA());
            buttonB.setText(questionList.get(index).getAnswerB());
            buttonC.setText(questionList.get(index).getAnswerC());
            buttonD.setText(questionList.get(index).getAnswerD());
        }
        else
        {

        }
    }
}
