package com.example.quizbuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {

    TextView scoreView, lastMessage;
    Button btnExit;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        try {
            //region Get user score from quiz Activity
            Intent intent = getIntent();
            String userScore = intent.getStringExtra("userScore");
            //endregion

            //region Connect screen with variables
            scoreView = findViewById(R.id.scoreView);
            lastMessage = findViewById(R.id.lastMessage);
            btnExit = findViewById(R.id.btnExit);
            //endregion

            //region display score and appropriate message
            scoreView.setText(userScore + "/10");
            int intUserScore = Integer.parseInt(userScore != null ? userScore : "unknown");
            if (intUserScore >= 7) {
                lastMessage.setText("Great Job!!!");
            } else if (intUserScore >= 4) {
                lastMessage.setText("Better luck Next time.");
            } else {
                lastMessage.setText("Geography is not your strongest skill. :(");
            }
            //endregion

            //region Exit button
            btnExit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //close application
                    finishAffinity();
                    System.exit(0);
                }
            });
            //endregion
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}