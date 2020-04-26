package com.example.pum3app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class  QuizActivity extends AppCompatActivity {

    Button checkButton;
    EditText answerEditText;
    List<QuizHelper> quizQuestion = new ArrayList<QuizHelper>();
    ImageView image;
    QuizHelper question;
    TextView textPoints;
    int questionCount = 0;
    int points = 0;
    boolean gameOver = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        checkButton = findViewById(R.id.button);
        answerEditText = findViewById(R.id.eTAnswer);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
        image = findViewById(R.id.imageView3);
        textPoints = findViewById(R.id.textViewUserPoints);
        startGame();
    }

    private void startGame()
    {
        points = questionCount = 0;
        quizQuestion.clear();
        addQuestion(R.drawable.arsenal, "Arsenal");
        addQuestion(R.drawable.chelsea, "Chelsea");
        addQuestion(R.drawable.city, "Manchester City");
        addQuestion(R.drawable.everton, "Everton");
        addQuestion(R.drawable.leicester, "Leicester");
        addQuestion(R.drawable.liverpool, "Liverpool");
        addQuestion(R.drawable.newcastle, "Newcastle");

        Collections.shuffle(quizQuestion);
        setQuestion(questionCount);
        setPointsText(0);
        gameOver = false;
    }

    private void setQuestion(int questionCount) {
        question = quizQuestion.get(questionCount);
        image.setImageResource(question.getImage());
        answerEditText.setText("");
    }

    private void addQuestion(int drawable, String answer)
    {
        QuizHelper helper = new QuizHelper();
        helper.setGoodAnswer(answer);
        helper.setImage(drawable);
        quizQuestion.add(helper);
    }

    private void checkAnswer() {
        if(!gameOver)
        {
            String answer = answerEditText.getText().toString();

            if(!answer.equals(""))
            {
                answer = answer.toLowerCase();
                String goodAnswer = question.getGoodAnswer().toLowerCase();

                if(answer.equals(goodAnswer))
                {
                    Toast.makeText(getApplicationContext(), R.string.goodAnswer, Toast.LENGTH_SHORT).show();
                    points++;
                }
                else
                {
                    String text = getString(R.string.badAnswer) + question.getGoodAnswer();
                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                }

                setPointsText(points);
                questionCount++;

                if(questionCount == quizQuestion.size())
                {
                    gameOver = true;
                    image.setImageResource(R.drawable.end);
                    Toast.makeText(getApplicationContext(), getString(R.string.theEnd) + points, Toast.LENGTH_LONG ).show();
                }
                else
                {
                    setQuestion(questionCount);
                }
            }
        }
    }

    private void setPointsText(int points) {
        textPoints.setText(Integer.toString(points));
    }
}
