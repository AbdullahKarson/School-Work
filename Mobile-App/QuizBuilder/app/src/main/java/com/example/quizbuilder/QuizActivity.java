package com.example.quizbuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.*;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.io.*;
import java.util.*;

public class QuizActivity extends AppCompatActivity {

    //region Global variables
    TextView userName, scoreView, questionView, questionNum;
    Button btnOption1, btnOption2, btnOption3, btnOption4;
    int maxScore = 10;
    int currentScore = 0;
    int currentQuestion = 0;
    int questionNumMax = 10;
    String msg, TAG = "Error", str = null, csvRegex = "-";
    BufferedReader br = null;
    //endregion

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //region Connect screen with variables
        scoreView = findViewById(R.id.scoreView);
        questionView = findViewById(R.id.questionView);
        questionNum = findViewById(R.id.questionNum);
        btnOption1 = findViewById(R.id.btnOption1);
        btnOption2 = findViewById(R.id.btnOption2);
        btnOption3 = findViewById(R.id.btnOption3);
        btnOption4 = findViewById(R.id.btnOption4);
        //endregion

        //region Intent and Bundle for End Screen
        final Bundle bundle = new Bundle();
        final Intent endActivity = new Intent(this, EndActivity.class);
        //endregion

        //region Get Name of User from Main Screen
        Intent intent = getIntent();
        String Name = intent.getStringExtra("NameOfUser");
        userName = findViewById(R.id.userName);
        userName.setText(Name);
        //endregion

        //region Array and Hash Map
        final ArrayList<String> questions = new ArrayList<>();
        final ArrayList<String> answers = new ArrayList<>();
        final Map<String, String> solutions = new HashMap<>();
        final ArrayList<String> options = new ArrayList<>();//length 4
        //endregion

        //region Toast for check answer
        final Context context = getApplicationContext();
        final int duration = Toast.LENGTH_SHORT;
        final String correct = "Correct";
        final String Wrong = "Wrong";
        final Toast correctToast = Toast.makeText(context, correct, duration);
        final Toast wrongToast = Toast.makeText(context, Wrong, duration);
        //endregion

        //region At start of quiz screen populate arrays and buttons
        try {
            //region populate arrays with values from file raw.quizList
            InputStream is = getResources().openRawResource(R.raw.quizlist);
            br = new BufferedReader(new InputStreamReader(is));
            System.out.println("File in RAW is open");

            while ((str = br.readLine()) != null) {
                String[] content = str.split(csvRegex);
                questions.add(content[0]);
                answers.add(content[1]);
                solutions.put(content[0], content[1]);
            }
            System.out.println(questions);
            System.out.println(answers); //for checking purposes
            System.out.println(solutions);
            //endregion

            //region populate buttons with first values
            //display score
            scoreView.setText(currentScore + "/" + maxScore);
            //display question number
            questionNum.setText(currentQuestion + "/" + questionNumMax);
            //shuffle questions and answers
            Collections.shuffle(answers);
            Collections.shuffle(questions);
            //add correct solution that corresponds to question to option array
            options.add(solutions.get(questions.get(0)));
            //get 3 wrong values
            for (int i = 1; i < 4; i++) {
                if (!answers.get(i).equals(solutions.get(questions.get(0))) && !options.contains(answers.get(i))) {
                    options.add(answers.get(i));
                } else {
                    options.add(answers.get(i + 1));
                }
            }
            //set Text for view and buttons
            Collections.shuffle(options);
            questionView.setText(questions.get(0));
            btnOption1.setText(options.get(0));
            btnOption2.setText(options.get(1));
            btnOption3.setText(options.get(2));
            btnOption4.setText(options.get(3));
            //endregion
        } catch (IOException e) {
            msg = e.getLocalizedMessage();
            Log.e(TAG, msg != null ? msg : "Couldn't retrieve file Information");
        } catch (Exception e) {
            msg = e.getLocalizedMessage();
            Log.e(TAG, msg != null ? msg : "General error at Array population");
        }
        //endregion

        //region Button 1
        btnOption1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                try {
                    //go to end screen when question score equals 10
                    currentQuestion++;
                    if (currentQuestion >= 10 && btnOption1.getText().equals(solutions.get(questions.get(0)))) {
                        currentScore++;
                        bundle.putString("userScore", String.valueOf(currentScore));
                        endActivity.putExtras(bundle);
                        startActivity(endActivity);
                    // check if user choose the correct answer
                    }else if (currentQuestion >= 10) {
                        bundle.putString("userScore", String.valueOf(currentScore));
                        endActivity.putExtras(bundle);
                        startActivity(endActivity);
                        // check if user choose the correct answer
                    } else if (btnOption1.getText().equals(solutions.get(questions.get(0)))) {
                        correctToast.show();
                        currentScore++;
                        scoreView.setText(currentScore + "/" + maxScore);
                    //Toast a wrong answer
                    } else wrongToast.show();

                    //display question Number
                    questionNum.setText(currentQuestion + "/" + questionNumMax);
                    //remove question from array list and clear option array list
                    questions.remove(0);
                    options.clear();
                    //shuffle questions again to get a new question on position 0
                    Collections.shuffle(questions);
                    Collections.shuffle(answers);
                    //add correct answer to options
                    options.add(solutions.get(questions.get(0)));
                    //ass 3 false options to the options list
                    for (int i = 1; i < 4; i++) {
                        if (!answers.get(i).equals(solutions.get(questions.get(0))) && !options.contains(answers.get(i))) {
                            options.add(answers.get(i));
                        } else {
                            options.add(answers.get(i + 1));
                        }
                    }
                    //set Text for view and buttons
                    Collections.shuffle(options);
                    questionView.setText(questions.get(0));
                    btnOption1.setText(options.get(0));
                    btnOption2.setText(options.get(1));
                    btnOption3.setText(options.get(2));
                    btnOption4.setText(options.get(3));
                } catch (Exception e) {
                    msg = e.getLocalizedMessage();
                    Log.e(TAG, msg != null ? msg : "Button 1 error");
                }
            }
        });//endregion

        //region Button 2
        btnOption2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                try {
                    currentQuestion++;
                    if (currentQuestion >= 10 && btnOption2.getText().equals(solutions.get(questions.get(0)))) {
                        currentScore++;
                        bundle.putString("userScore", String.valueOf(currentScore));
                        endActivity.putExtras(bundle);
                        startActivity(endActivity);
                        // check if user choose the correct answer
                    }else if (currentQuestion >= 10) {
                        bundle.putString("userScore", String.valueOf(currentScore));
                        endActivity.putExtras(bundle);
                        startActivity(endActivity);
                        // check if user choose the correct answer
                    } else if (btnOption2.getText().equals(solutions.get(questions.get(0)))) {
                        correctToast.show();
                        currentScore++;
                        scoreView.setText(currentScore + "/" + maxScore);
                    } else {
                        wrongToast.show();
                    }

                    questionNum.setText(currentQuestion + "/" + questionNumMax);
                    questions.remove(0);
                    options.clear();
                    Collections.shuffle(questions);
                    options.add(solutions.get(questions.get(0)));
                    for (int i = 1; i < 4; i++) {
                        if (!answers.get(i).equals(solutions.get(questions.get(0))) && !options.contains(answers.get(i))) {
                            options.add(answers.get(i));
                        } else {
                            options.add(answers.get(i + 1));
                        }
                    }
                    //set Text for view and buttons
                    Collections.shuffle(options);
                    questionView.setText(questions.get(0));
                    btnOption1.setText(options.get(0));
                    btnOption2.setText(options.get(1));
                    btnOption3.setText(options.get(2));
                    btnOption4.setText(options.get(3));
                } catch (Exception e) {
                    msg = e.getLocalizedMessage();
                    Log.e(TAG, msg != null ? msg : "Button 2 error");
                }
            }
        });//endregion

        //region Button 3
        btnOption3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                try {
                    currentQuestion++;
                    if (currentQuestion >= 10 && btnOption3.getText().equals(solutions.get(questions.get(0)))) {
                        currentScore++;
                        bundle.putString("userScore", String.valueOf(currentScore));
                        endActivity.putExtras(bundle);
                        startActivity(endActivity);
                        // check if user choose the correct answer
                    }else if (currentQuestion >= 10) {
                        bundle.putString("userScore", String.valueOf(currentScore));
                        endActivity.putExtras(bundle);
                        startActivity(endActivity);
                        // check if user choose the correct answer
                    } else if (btnOption3.getText().equals(solutions.get(questions.get(0)))) {
                        correctToast.show();
                        currentScore++;
                        scoreView.setText(currentScore + "/" + maxScore);
                    } else {
                        wrongToast.show();
                    }

                    questionNum.setText(currentQuestion + "/" + questionNumMax);
                    questions.remove(0);
                    options.clear();
                    Collections.shuffle(questions);
                    options.add(solutions.get(questions.get(0)));
                    for (int i = 1; i < 4; i++) {
                        if (!answers.get(i).equals(solutions.get(questions.get(0))) && !options.contains(answers.get(i))) {
                            options.add(answers.get(i));
                        } else {
                            options.add(answers.get(i + 1));
                        }
                    }
                    //set Text for view and buttons
                    Collections.shuffle(options);
                    questionView.setText(questions.get(0));
                    btnOption1.setText(options.get(0));
                    btnOption2.setText(options.get(1));
                    btnOption3.setText(options.get(2));
                    btnOption4.setText(options.get(3));
                } catch (Exception e) {
                    msg = e.getLocalizedMessage();
                    Log.e(TAG, msg != null ? msg : "Button 3 error");
                }
            }
        });//endregion

        //region Button 4
        btnOption4.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                try {
                    currentQuestion++;
                    if (currentQuestion >= 10 && btnOption4.getText().equals(solutions.get(questions.get(0)))) {
                        currentScore++;
                        bundle.putString("userScore", String.valueOf(currentScore));
                        endActivity.putExtras(bundle);
                        startActivity(endActivity);
                        // check if user choose the correct answer
                    }else if (currentQuestion >= 10) {
                        bundle.putString("userScore", String.valueOf(currentScore));
                        endActivity.putExtras(bundle);
                        startActivity(endActivity);
                        // check if user choose the correct answer
                    } else if (btnOption4.getText().equals(solutions.get(questions.get(0)))) {
                        correctToast.show();
                        currentScore++;
                        scoreView.setText(currentScore + "/" + maxScore);
                    } else {
                        wrongToast.show();
                    }

                    questionNum.setText(currentQuestion + "/" + questionNumMax);
                    questions.remove(0);
                    options.clear();
                    Collections.shuffle(questions);
                    options.add(solutions.get(questions.get(0)));
                    for (int i = 1; i < 4; i++) {
                        if (!answers.get(i).equals(solutions.get(questions.get(0))) && !options.contains(answers.get(i))) {
                            options.add(answers.get(i));
                        } else {
                            options.add(answers.get(i + 1));
                        }
                    }
                    //set Text for view and buttons
                    Collections.shuffle(options);
                    questionView.setText(questions.get(0));
                    btnOption1.setText(options.get(0));
                    btnOption2.setText(options.get(1));
                    btnOption3.setText(options.get(2));
                    btnOption4.setText(options.get(3));
                } catch (Exception e) {
                    msg = e.getLocalizedMessage();
                    Log.e(TAG, msg != null ? msg : "Button 4 error");
                }
            }
        });//endregion
    }
}