package com.example.quizbuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.*;

import android.util.Log;
import android.view.*;
import android.widget.*;

/*
 * Resources:
 * -Brightspace videos from you David
 * -https://stackoverflow.com/questions/4228975/how-to-randomize-two-arraylists-in-the-same-fashion
 * -https://stackoverflow.com/questions/4228975/how-to-randomize-two-arraylists-in-the-same-fashion
 * -https://stackoverflow.com/questions/24610527/how-do-i-get-a-button-to-open-another-activity
 * -https://stackoverflow.com/questions/2832017/what-is-the-difference-between-loose-coupling-and-tight-coupling-in-the-object-o
 * -https://android.jlelse.eu/passing-data-between-activities-using-intent-in-android-85cb097f3016
 * -https://androideverywhere.com/tutorial/rounded-button-in-android-studio
 * -https://stackoverflow.com/questions/47954072/splitting-text-file-entries-to-add-into-arraylists/47954428
 * -https://stackoverflow.com/questions/55085792/separate-comma-delimited-text-file-to-array
 * -https://www.w3schools.com/java/java_hashmap.asp
 * -https://stackoverflow.com/questions/4172940/how-to-set-background-color-of-a-button-in-java-gui
 * -https://www.geeksforgeeks.org/how-to-remove-duplicates-from-arraylist-in-java/
 * -https://stackoverflow.com/questions/6330200/how-to-quit-android-application-programmatically
 * -https://www.flaticon.com/authors/freepik
 * -https://www.storyboardthat.com/portal/dashboard2
 * */

public class MainActivity extends AppCompatActivity {

    //region Global variables
    Button btnStartQuiz;
    String msg, TAG = "Error";
    EditText userName;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //region Name Error Toast
        final String nameError = "Please input a Name!";
        final Context context = getApplicationContext();
        final int duration = Toast.LENGTH_SHORT;
        //endregion

        //region Connect screen with variables
        btnStartQuiz = findViewById(R.id.btnStartQuiz);
        userName = findViewById(R.id.userName);
        //endregion

        //region Intent and Bundle for Question Screen
        final Bundle bundle = new Bundle();
        final Intent quizActivity = new Intent(this, QuizActivity.class);
        //endregion

        //region Start button click listener
        btnStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //check if string is empty
                    if (userName.getText().toString().equals("")) {
                        //Display a Toast message that asks for user name Input
                        Toast error = Toast.makeText(context, nameError, duration);
                        error.show();
                    } else {
                        //save Name to bundle
                        bundle.putString("NameOfUser", userName.getText().toString());
//                    quizActivity.putExtra("NameOfUser",userName.getText().toString());
                        quizActivity.putExtras(bundle);
                        //switch to Quiz Window
                        startActivity(quizActivity);
                        System.out.println(userName.getText().toString());
                    }
                } catch (Exception e) {
                    //log
                    msg = e.getLocalizedMessage();
                    Log.e(TAG, msg != null ? msg : "Get Name Error");
                }
            }
        });//endregion
    }
}