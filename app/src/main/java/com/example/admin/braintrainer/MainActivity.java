package com.example.admin.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button starButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    TextView sumTextView;
    TextView resultTextView;
    TextView pointsTextView;
    TextView timerTextView;
    ArrayList<Integer> answer = new ArrayList<Integer>();
    int localtionOfCorrectAnswer;
    int score = 0;
    int numberOfQuestion = 0;
    RelativeLayout gameRelativeLayout;

    public void playAgain(View view) {
        score = 0;
        numberOfQuestion = 0;
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        generateQuestion();
        new CountDownTimer(5010, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");

            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your score:  " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestion));

            }
        }.start();
    }

    public void generateQuestion() {
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));

        localtionOfCorrectAnswer = rand.nextInt(4);
        answer.clear();

        int incorrectAnswer;
        for (int i = 0; i < 4; i++) {
            if (localtionOfCorrectAnswer == i) {
                answer.add(a + b);
            } else {
                incorrectAnswer = rand.nextInt(41);
                while (incorrectAnswer == a + b) {
                    incorrectAnswer = rand.nextInt(41);
                }
                answer.add(incorrectAnswer);
            }
        }
        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));
    }

    public void chooseAnswer(View view) {
        //test Button tag tapped Log.i("Tag", (String) view.getTag());
        if (view.getTag().toString().equals(Integer.toString(localtionOfCorrectAnswer))) {
            score++;
            resultTextView.setText("correct!");
        } else {
            resultTextView.setText("Wrong!");
        }
        //du dung hay sai so cau hoi van chay
        numberOfQuestion++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestion));
        generateQuestion();
    }


    public void start(View view) {
        starButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        starButton = (Button) findViewById(R.id.startButton);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        gameRelativeLayout = (RelativeLayout) findViewById(R.id.gameRelativeLayout);

        //  generateQuestion();
        // phuong thuc playAgain bao gom ca phuong thuc generateQuestion
        //playAgain(findViewById(R.id.playAgainButton));


    }
}


