package com.example.psbquiz.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.psbquiz.Models.UserInfo;
import com.example.psbquiz.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

    private Button quizButton;
    private Button logoutButton;
    private TextView nameText;
    private TextView courseText;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    public String userName;
    public String course;
    String uid;

    public static final String userNameKey = "userNameKey";
    public static final String courseKey = "courseKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();

        quizButton = findViewById(R.id.quizButton);
        logoutButton = findViewById(R.id.logoutButton);
        nameText = findViewById(R.id.nameText);
        courseText = findViewById(R.id.courseText);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);

        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizMenu();
            }
        });

        FirebaseUser user = mAuth.getCurrentUser();

//        uid = user.getUid();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference database = firebaseDatabase.getReference("UserInfo/"+uid);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserInfo value = dataSnapshot.getValue(UserInfo.class);
                Log.v("Key: ", dataSnapshot.getKey());
//                userName = value.getUserName();
 //               course = value.getCourse();
 //               Log.v("Name: ", userName);

                nameText.setText(userName);
                courseText.setText(course);
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

   public void quizMenu()
   {
       Intent quizmenu = new Intent(this, QuizMenuActivity.class);

       quizmenu.putExtra(userNameKey, userName);
       quizmenu.putExtra(courseKey, course);

       Bundle bundle = new Bundle();
       bundle.putString(userNameKey, userName);
       bundle.putString(courseKey, course);
       quizmenu.putExtras(bundle);

       startActivity(quizmenu);
   }

    // Logout function
    private void logout() {
        // When user is logged out, they will be sent to the login page
        mAuth.signOut();
        Toast.makeText(HomeActivity.this, "Logged out. ", Toast.LENGTH_SHORT).show();
        loginUI();
    }

    // Login intent
    private void loginUI() {
        Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }
}
