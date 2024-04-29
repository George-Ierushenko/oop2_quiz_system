package quizsystem.controller;

import quizsystem.model.*;
import quizsystem.service.*;

import java.util.List;

public class QuestionController extends Controller {
    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        super();

        this.questionService = questionService;
    }

    public void addQuestionToQuiz() {
        System.out.println("Enter quiz ID where to add the question:");
        int quizId = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter question text:");
        String questionText = scanner.nextLine();

        System.out.println("Enter option A:");
        String optionA = scanner.nextLine();
        System.out.println("Enter option B:");
        String optionB = scanner.nextLine();
        System.out.println("Enter option C:");
        String optionC = scanner.nextLine();
        System.out.println("Enter option D:");
        String optionD = scanner.nextLine();

        System.out.println("Enter the correct option (A, B, C, or D):");
        char correctOption = scanner.nextLine().toUpperCase().charAt(0);

        QuizQuestion question = new QuizQuestion();
        question.setQuizId(quizId);
        question.setQuestionText(questionText);
        question.setOptionA(optionA);
        question.setOptionB(optionB);
        question.setOptionC(optionC);
        question.setOptionD(optionD);
        question.setCorrectOption(correctOption);

        if (questionService.addQuestionToQuiz(question, quizId)) {
            System.out.println("Question added successfully.");
        } else {
            System.out.println("Failed to add question.");
        }
    }

    public void updateQuestion() {
        System.out.println("Enter question ID to update:");
        int questionId = Integer.parseInt(scanner.nextLine());
        QuizQuestion existingQuestion = questionService.getQuestion(questionId);
        if (existingQuestion == null) {
            System.out.println("Question not found.");
            return;
        }

        System.out.println("Enter new question text (leave blank to keep current):");
        String questionText = scanner.nextLine();
        if (!questionText.isEmpty()) {
            existingQuestion.setQuestionText(questionText);
        }

        System.out.println("Update options (leave blank to keep current):");
        System.out.print("Option A: ");
        String optionA = scanner.nextLine();
        if (!optionA.isEmpty()) existingQuestion.setOptionA(optionA);
        System.out.print("Option B: ");
        String optionB = scanner.nextLine();
        if (!optionB.isEmpty()) existingQuestion.setOptionB(optionB);
        System.out.print("Option C: ");
        String optionC = scanner.nextLine();
        if (!optionC.isEmpty()) existingQuestion.setOptionC(optionC);
        System.out.print("Option D: ");
        String optionD = scanner.nextLine();
        if (!optionD.isEmpty()) existingQuestion.setOptionD(optionD);

        System.out.println("Enter the correct option (A, B, C, or D):");
        String correctOption = scanner.nextLine().toUpperCase();
        if (!correctOption.isEmpty()) existingQuestion.setCorrectOption(correctOption.charAt(0));

        if (questionService.updateQuestion(existingQuestion)) {
            System.out.println("Question updated successfully.");
        } else {
            System.out.println("Failed to update question.");
        }
    }

    public void deleteQuestion() {
        System.out.println("Enter question ID to delete:");
        int questionId = Integer.parseInt(scanner.nextLine());
        if (questionService.deleteQuestion(questionId)) {
            System.out.println("Question deleted successfully.");
        } else {
            System.out.println("Failed to delete question.");
        }
    }

    public void listQuestions() {
        System.out.println("Enter quiz ID to list questions:");
        int quizId = Integer.parseInt(scanner.nextLine());
        List<QuizQuestion> questions = questionService.getQuestions(quizId);
        if (questions.isEmpty()) {
            System.out.println("No questions found for this quiz.");
        } else {
            questions.forEach(q -> System.out.println("ID: " + q.getId() + ", Text: " + q.getQuestionText()));
        }
    }
}
