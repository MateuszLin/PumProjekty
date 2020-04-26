package com.example.pum3app;

public class QuizHelper {
    private int drawableId;
    private String goodAnswer;

    public void setImage(int id)
    {
        drawableId = id;
    }

    public int getImage()
    {
        return drawableId;
    }

    public void setGoodAnswer(String ans)
    {
        goodAnswer = ans;
    }

    public String getGoodAnswer()
    {
        return goodAnswer;
    }



}
