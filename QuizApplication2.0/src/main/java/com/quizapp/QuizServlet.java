 package com.quizapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/quiz")
public class QuizServlet extends HttpServlet {
    private Quiz quiz;

    @Override
    public void init() throws ServletException {
        List<Question> questions = List.of(
            new Question("What is the capital of France?", new String[]{"Paris", "London", "Berlin", "Madrid"}, 0),
            new Question("What is 2 + 2?", new String[]{"3", "4", "5", "6"}, 1)
            // Add more questions as needed
        );
        User user = new User("John Doe");  // Initialize the user
        quiz = new Quiz(questions, user);  // Initialize the quiz
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!quiz.isFinished()) {
            request.setAttribute("question", quiz.getCurrentQuestion());
            request.getRequestDispatcher("/question.jsp").forward(request, response);
        } else {
            request.setAttribute("user", quiz.getUser());
            request.getRequestDispatcher("/result.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (quiz.isFinished()) {
            // If the quiz is finished, forward to the result page
            request.getRequestDispatcher("/result.jsp").forward(request, response);
            return;
        }

        // Parse the answer from the request
        int answer;
        try {
            answer = Integer.parseInt(request.getParameter("answer"));
        } catch (NumberFormatException e) {
            // If answer is not a number, return an error
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid answer format");
            return;
        }

        // Answer the current question
        quiz.answerCurrentQuestion(answer);

        // Forward to the next question or result page
        doGet(request, response);  // Load next question or result page
    }
}
