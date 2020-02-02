package com.example.psbquiz.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.psbquiz.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QuizMenuActivity extends AppCompatActivity {

    String userName;
    String course;

    private TextView courseTitle;
    ListView listView;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    public static final String moduleKey = "moduleKey";
    public static final String userCourse = "userCourse";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_menu);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        userName = course = intent.getStringExtra(HomeActivity.userNameKey);
        course = intent.getStringExtra(HomeActivity.courseKey);
        listView = findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

        Log.v("UsernameQMA: ", userName);
        Log.v("CourseQMA: ", course);

        courseTitle = findViewById(R.id.courseTitle);
        courseTitle.setText("Course: " + course);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Quiz");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot snap = dataSnapshot.child(course);

                for (DataSnapshot dataSnap : snap.getChildren())
                {

                    String key = dataSnap.getKey();
                    arrayList.add(key);

                    Log.v("Child: ", key);
                }
                arrayAdapter.notifyDataSetChanged();
                Log.v("ArrList: ", arrayList.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String tapped = arrayList.get(i);

                Log.v("Tapped: ", tapped);

                Intent quizPrepare = new Intent(getApplicationContext(), QuizPreparationActivity.class);

                quizPrepare.putExtra(moduleKey, tapped);
                quizPrepare.putExtra(userCourse, course);
                Bundle bundle = new Bundle();
                bundle.putString(moduleKey, tapped);
                bundle.putString(userCourse, course);
                quizPrepare.putExtras(bundle);
                startActivity(quizPrepare);
            }

        });
    }
}
