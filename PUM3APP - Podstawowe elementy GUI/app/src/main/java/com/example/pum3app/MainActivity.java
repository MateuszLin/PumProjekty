package com.example.pum3app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button redBtn;
    Button yellowBtn;
    Button greenBtn;
    ImageView redIV;
    ImageView yellowIV;
    ImageView greenIV;

    TrafficLightOrder prevState = TrafficLightOrder.Red;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        redBtn = findViewById(R.id.redBtn);
        yellowBtn = findViewById(R.id.yellowBtn);
        greenBtn = findViewById(R.id.greenBtn);

        redIV = findViewById(R.id.firstImageView);
        yellowIV = findViewById(R.id.secondImageView);
        greenIV = findViewById(R.id.thirdImageView);

        setRedLightOnImageView(redIV);

        redBtn.setOnClickListener(this);
        greenBtn.setOnClickListener(this);
        yellowBtn.setOnClickListener(this);


    }

    private void changeLightIfPossible(ButtonClicked button) {

        if(prevState == TrafficLightOrder.Red && button == ButtonClicked.Yellow)
        {
            setLightState(TrafficLightOrder.RedAndYellow);
        }
        else if(prevState == TrafficLightOrder.RedAndYellow && button == ButtonClicked.Green)
        {
            setLightState(TrafficLightOrder.Green);
        }
        else if(prevState == TrafficLightOrder.Green && button == ButtonClicked.Yellow)
        {
            setLightState(TrafficLightOrder.Yellow);
        }
        else if (prevState == TrafficLightOrder.Yellow && button == ButtonClicked.Red)
        {
            setLightState(TrafficLightOrder.Red);
        }
        else
        {
            Toast.makeText(getApplicationContext(), R.string.wrongOrderOfLights, Toast.LENGTH_SHORT).show();
        }


    }

    private void setLightState(TrafficLightOrder lightOrder) {

        prevState = lightOrder;

        switch (lightOrder)
        {
            case Red:
                setOffLightOnImageView(yellowIV);
                setOffLightOnImageView(greenIV);
                setRedLightOnImageView(redIV);
                break;
            case RedAndYellow:
                setYellowLightOnImageView(yellowIV);
                break;
            case Green:
                setOffLightOnImageView(redIV);
                setOffLightOnImageView(yellowIV);
                setGreenLightOnImageView(greenIV);
                break;
            case Yellow:
                setOffLightOnImageView(greenIV);
                setYellowLightOnImageView(yellowIV);
                break;
        }
    }

    private void setImageOnImageView(ImageView imageV, int resId)
    {
        imageV.setImageResource(resId);
    }

    private void setRedLightOnImageView(ImageView imageView)
    {
        setImageOnImageView(imageView, R.mipmap.red_on);
    }

    private void setYellowLightOnImageView(ImageView imageView)
    {
        setImageOnImageView(imageView, R.mipmap.yellow_on);
    }

    private void setGreenLightOnImageView(ImageView imageView)
    {
        setImageOnImageView(imageView, R.mipmap.green_on);
    }

    private void setOffLightOnImageView(ImageView imageView)
    {
        setImageOnImageView(imageView, R.mipmap.light_off);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == redBtn.getId())
        {
            changeLightIfPossible(ButtonClicked.Red);
        }
        else if(v.getId() == yellowBtn.getId())
        {
            changeLightIfPossible(ButtonClicked.Yellow);
        }
        else if(v.getId() == greenBtn.getId())
        {
            changeLightIfPossible(ButtonClicked.Green);
        }
    }


    enum TrafficLightOrder
    {
        Red, RedAndYellow, Yellow, Green
    }

    enum ButtonClicked
    {
        Red, Yellow, Green
    }
}
