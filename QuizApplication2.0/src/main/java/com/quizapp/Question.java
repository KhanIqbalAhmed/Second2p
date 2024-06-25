 package com.quizapp;

public class Question {
    private String question;
    private String[] options;
    private int correctOption;

    public Question(String question, String[] options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }

    public boolean isCorrect(int answer) {
        return answer == correctOption;
    }
}
