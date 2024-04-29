package quizsystem.controller;

import quizsystem.model.Quiz;
import quizsystem.model.QuizQuestion;
import quizsystem.model.Session;
import quizsystem.model.StudentResult;
import quizsystem.service.QuestionService;
import quizsystem.service.QuizService;
import quizsystem.service.StudentResultService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class QuizController extends Controller {
    private QuizService quizService;
    private QuestionService questionService;
    private StudentResultService studentResultService;

    public QuizController(QuizService quizService, QuestionService questionService, StudentResultService studentResultService) {
        super();

        this.quizService = quizService;
        this.questionService = questionService;
        this.studentResultService = studentResultService;
    }

    public void createQuiz() {
        System.out.println("Enter quiz title:");
        String title = scanner.nextLine();
        System.out.println("Enter instructor ID:");
        int instructorId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter time limit (in minutes, enter 0 for no limit):");
        int timeLimit = Integer.parseInt(scanner.nextLine());

        Quiz quiz = new Quiz();

        quiz.setTitle(title);
        quiz.setInstructorId(instructorId);
        quiz.setTimeLimit(timeLimit);
        quiz.setCreateDate(new Timestamp(System.currentTimeMillis()));

        if (quizService.createQuiz(quiz)) {
            System.out.println("Quiz created successfully.");
        } else {
            System.out.println("Failed to create quiz.");
        }
    }

    public void updateQuiz() {
        System.out.println("Enter quiz ID to update:");
        int id = Integer.parseInt(scanner.nextLine());
        Quiz existingQuiz = quizService.getQuiz(id);
        if (existingQuiz == null) {
            System.out.println("Quiz not found.");
            return;
        }

        System.out.println("Enter new title (leave blank to keep current):");
        String title = scanner.nextLine();
        if (!title.isEmpty()) {
            existingQuiz.setTitle(title);
        }

        System.out.println("Update time limit (in minutes, enter -1 to keep current):");
        int timeLimit = Integer.parseInt(scanner.nextLine());
        if (timeLimit >= 0) {
            existingQuiz.setTimeLimit(timeLimit);
        }

        if (quizService.updateQuiz(existingQuiz)) {
            System.out.println("Quiz updated successfully.");
        } else {
            System.out.println("Failed to update quiz.");
        }
    }

     public void takeQuiz(int quizId, int userId) {
        Quiz quiz = quizService.getQuiz(quizId);

        if (quiz == null) {
            System.out.println("Quiz not found.");
            return;
        }

        List<QuizQuestion> questions = questionService.getQuestions(quizId);
        if (questions == null || questions.isEmpty()) {
            System.out.println("No questions found in this quiz.");
            return;
        }

         System.out.println("Successfully started quiz - " + quiz.getTitle());
        int score = 0;
        for (QuizQuestion question : questions) {
            System.out.println(question.getQuestionText());

            System.out.println("A: " + question.getOptionA());
            System.out.println("B: " + question.getOptionB());
            System.out.println("C: " + question.getOptionC());
            System.out.println("D: " + question.getOptionD());
            System.out.print("Enter your answer (A, B, C, D): ");
            String userAnswer = scanner.nextLine().toUpperCase();

            if (userAnswer.equals(question.getCorrectOption())) {
                score++;
            }
        }

        System.out.println("\nTest is finished!");
        System.out.println("You scored: " + score + "/" + questions.size());

        StudentResult result = new StudentResult();

        result.setUserId(userId);
        result.setQuizId(quizId);
        result.setScore(score);
        result.setCompletionDate(new Timestamp(System.currentTimeMillis()));

        studentResultService.recordResult(result);
    }

    public void deleteQuiz() {
        System.out.println("Enter quiz ID to delete:");
        int id = Integer.parseInt(scanner.nextLine());
        if (quizService.deleteQuiz(id)) {
            System.out.println("Quiz deleted successfully.");
        } else {
            System.out.println("Failed to delete quiz.");
        }
    }

    public void listQuizzes() {
        List<Quiz> quizzes = quizService.listQuizzes();
        if (quizzes.isEmpty()) {
            System.out.println("No quizzes available.");
        } else {
            quizzes.forEach(quiz -> System.out.println("ID: " + quiz.getId() + ", Title: " + quiz.getTitle()));
        }
    }

    public int promptQuizId() {
        List<Quiz> quizzes = quizService.listQuizzes();

        if (quizzes.isEmpty()) {
            System.out.println("No quizzes available.");
            return -1;
        }

        while (true) {
            System.out.println("Available Quizzes:");
            quizzes.forEach(quiz -> System.out.println("ID: " + quiz.getId() + ", Title: " + quiz.getTitle()));

            System.out.println("Enter Quiz Id to Take(-1 to go back):");
            int choice = scanner.nextInt();
            if (choice < 0) return -1;

            Quiz selectedQuiz = quizzes.stream()
                    .filter(quiz -> quiz.getId() == choice)
                    .findFirst()
                    .orElse(null);

            if (selectedQuiz != null) return choice;
            System.out.println("Quiz not Found. Please try correcting the ID");
        }
    }

    public void getQuizDetails() {
        System.out.println("Enter quiz ID to view details:");
        int id = Integer.parseInt(scanner.nextLine());
        Quiz quiz = quizService.getQuiz(id);
        if (quiz != null) {
            System.out.println("Quiz ID: " + quiz.getId() + ", Title: " + quiz.getTitle() + ", Time Limit: " + quiz.getTimeLimit() + " minutes");
        } else {
            System.out.println("Quiz not found.");
        }
    }
}
