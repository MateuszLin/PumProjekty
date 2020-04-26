package com.example.pum3app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TicTacToeActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView image11, image23, image31, image32, image33, image12, image13, image21, image22;
    Button resetButton;
    TextView tVXCounter, tVOCounter;

    boolean isEnd = false;
    boolean isXMove = true;
    int[][] board = new int[3][3];
    int XWinCounter, OWinCounter, moveCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        image12 = findViewById(R.id.IV12);
        image11 = findViewById(R.id.IV11);
        image13 = findViewById(R.id.IV13);
        image21 = findViewById(R.id.IV21);
        image22 = findViewById(R.id.IV22);
        image23 = findViewById(R.id.IV23);
        image31 = findViewById(R.id.IV31);
        image32 = findViewById(R.id.IV32);
        image33 = findViewById(R.id.IV33);

        tVXCounter = findViewById(R.id.tVXCounter);
        tVOCounter = findViewById(R.id.tVOCounter);
        resetButton = findViewById(R.id.btnReset);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGaame();
            }
        });
        startGaame();
        image11.setOnClickListener(this);
        image12.setOnClickListener(this);
        image13.setOnClickListener(this);
        image21.setOnClickListener(this);
        image22.setOnClickListener(this);
        image23.setOnClickListener(this);
        image31.setOnClickListener(this);
        image32.setOnClickListener(this);
        image33.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == image11.getId())
        {
            clickedField(0, 0, image11);
        }
        else if(v.getId() == image12.getId())
        {
            clickedField(0, 1, image12);
        }
        else if(v.getId() == image13.getId())
        {
            clickedField(0, 2, image13);
        }
        else if(v.getId() == image21.getId())
        {
            clickedField(1, 0, image21);
        }
        else if(v.getId() == image22.getId())
        {
            clickedField(1, 1, image22);
        }
        else if(v.getId() == image23.getId())
        {
            clickedField(1, 2, image23);
        }
        else if(v.getId() == image31.getId())
        {
            clickedField(2, 0, image31);
        }
        else if(v.getId() == image32.getId())
        {
            clickedField(2, 1, image32);
        }
        else if(v.getId() == image33.getId())
        {
            clickedField(2, 2, image33);
        }
    }

    private void clickedField(int x, int y, ImageView image) {

        if(!isEnd)
        {
            if(board[x][y] == 0)
            {
                moveCounter++;

                setOnBoard(x, y);
                changeImageSource(image);
                checkStatus();
                if(isEnd)
                {
                    addToCounter();
                    setTextToCounters();
                    String winText = isXMove ? getString(R.string.playerXWon) : getString(R.string.playerOWon);
                    Toast.makeText(getApplicationContext(), winText, Toast.LENGTH_LONG).show();
                    resetButton.setVisibility(View.VISIBLE);
                }

                if(moveCounter == 9)
                {
                    resetButton.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), getString(R.string.gameDraw), Toast.LENGTH_LONG).show();
                }
                isXMove = !isXMove;
            }
            else
            {
                Toast.makeText(getApplicationContext(), R.string.wrongField, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setTextToCounters() {
        tVOCounter.setText(Integer.toString(OWinCounter));
        tVXCounter.setText(Integer.toString(XWinCounter));
    }

    private void addToCounter() {
        if(isXMove)
        {
            XWinCounter++;
        }
        else
        {
            OWinCounter++;
        }
    }

    private void checkStatus() {
        int checkingValue = isXMove ? 1 : 2;
        if(didRowWin(checkingValue)
        || didColumnWin(checkingValue)
        || didDiagonalWin(checkingValue))
        {
            isEnd = true;
        }
    }

    private boolean didDiagonalWin(int checkingValue) {
        return (board[0][0] == checkingValue
        && board[1][1] == checkingValue
        && board[2][2] == checkingValue)
        ||
                (board[0][2] == checkingValue
            && board[1][1] == checkingValue
            && board[2][0] == checkingValue);

    }

    private boolean didColumnWin(int checkingValue) {
        for (int i = 0; i < 3; i++)
        {
            if(board[0][i] == checkingValue
                    && board[1][i] == checkingValue
                    && board[2][i] == checkingValue )
            {
                return true;
            }
        }

        return false;
    }

    private boolean didRowWin(int checkingValue) {

        for (int i = 0; i < 3; i++)
        {
            if(board[i][0] == checkingValue
                    && board[i][1] == checkingValue
                    && board[i][2] == checkingValue )
            {
                return true;
            }
        }

        return false;
    }

    private void setOnBoard(int x, int y) {
        if(isXMove)
        {
            board[x][y] = 1;
        }
        else
        {
            board[x][y] = 2;
        }
    }

    private void startGaame() {
        board = new int[][]{
            {0,0,0},
            {0,0,0},
            {0,0,0}
        };
        moveCounter = 0;

        isXMove = true;
        isEnd = false;

        image11.setImageResource(R.drawable.blank);
        image12.setImageResource(R.drawable.blank);
        image13.setImageResource(R.drawable.blank);
        image21.setImageResource(R.drawable.blank);
        image22.setImageResource(R.drawable.blank);
        image23.setImageResource(R.drawable.blank);
        image31.setImageResource(R.drawable.blank);
        image32.setImageResource(R.drawable.blank);
        image33.setImageResource(R.drawable.blank);

        resetButton.setVisibility(View.INVISIBLE);
    }

    private void changeImageSource(ImageView image) {
        if(isXMove)
        {
            image.setImageResource(R.drawable.x);
        }
        else
        {
            image.setImageResource(R.drawable.o);
        }
    }

}
