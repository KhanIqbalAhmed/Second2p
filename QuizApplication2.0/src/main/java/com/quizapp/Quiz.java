 package com.quizapp;

import java.util.List;

public class Quiz {
    private List<Question> questions;
    private User user;
    private int currentQuestionIndex;

    public Quiz(List<Question> questions, User user) {
        this.questions = questions;
        this.user = user;
        this.currentQuestionIndex = 0;
    }

    public Question getCurrentQuestion() {
        if (currentQuestionIndex < questions.size()) {
            return questions.get(currentQuestionIndex);
        }
        return null; // No more questions
    }

    public void nextQuestion() {
        if (currentQuestionIndex < questions.size() - 1) {
            currentQuestionIndex++;
        }
    }

    public boolean isFinished() {
        return currentQuestionIndex >= questions.size();
    }

    public User getUser() {
        return user;
    }

    public void answerCurrentQuestion(int answer) {
        Question currentQuestion = getCurrentQuestion();
        if (currentQuestion != null && currentQuestion.isCorrect(answer)) {
            user.incrementScore();
        }
        nextQuestion();
    }
}
