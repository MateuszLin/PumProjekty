package com.example.pum5app;

import java.io.Serializable;

public class LearningHelper implements Serializable {

    public LanguageToLearn learningLanguage;
    public ModeApp modeOfApp;
    public int goodAnswer;
    public int numberOfAnswer;

    public enum LanguageToLearn
    {
        POLISH,
        ENGLISH
    }

    public enum ModeApp
    {
        LEARN,
        TEST
    }
}
