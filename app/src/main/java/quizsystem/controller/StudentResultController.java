package quizsystem.controller;

import quizsystem.model.StudentResult;
import quizsystem.service.StudentResultService;

import java.sql.Timestamp;
import java.util.List;

public class StudentResultController extends Controller {
    private StudentResultService studentResultService;

    public StudentResultController(StudentResultService studentResultService) {
        super();
        this.studentResultService = studentResultService;
    }

    public void recordResult() {
        System.out.println("Enter user ID:");
        int userId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter quiz ID:");
        int quizId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter score:");
        int score = Integer.parseInt(scanner.nextLine());

        StudentResult result = new StudentResult();
        result.setUserId(userId);
        result.setQuizId(quizId);
        result.setScore(score);
        result.setCompletionDate(new Timestamp(System.currentTimeMillis()));

        if (studentResultService.recordResult(result)) {
            System.out.println("Result recorded successfully.");
        } else {
            System.out.println("Failed to record result.");
        }
    }

    public void updateResult() {
        System.out.println("Enter result ID to update:");
        int resultId = Integer.parseInt(scanner.nextLine());
        StudentResult result = studentResultService.getResult(resultId);

        if (result == null) {
            System.out.println("Result not found.");
            return;
        }

        System.out.println("Enter new score:");
        int newScore = Integer.parseInt(scanner.nextLine());
        result.setScore(newScore);

        if (studentResultService.updateResult(result)) {
            System.out.println("Result updated successfully.");
        } else {
            System.out.println("Failed to update result.");
        }
    }

    public void deleteResult() {
        System.out.println("Enter result ID to delete:");
        int resultId = Integer.parseInt(scanner.nextLine());

        if (studentResultService.deleteResult(resultId)) {
            System.out.println("Result deleted successfully.");
        } else {
            System.out.println("Failed to delete result.");
        }
    }

    public void listResultsByQuiz() {
        System.out.println("Enter quiz ID:");
        int quizId = Integer.parseInt(scanner.nextLine());
        List<StudentResult> results = studentResultService.getResultsByQuiz(quizId);

        if (results.isEmpty()) {
            System.out.println("No results found for this quiz.");
        } else {
            results.forEach(r -> System.out.println("Result ID: " + r.getId() + ", User ID: " + r.getUserId() + ", Score: " + r.getScore()));
        }
    }

    public void listResultsByUser(int userId) {
        List<StudentResult> results = studentResultService.getResultsByUser(userId);
        if (results.isEmpty()) {
            System.out.println("No results found for this user.");
        } else {
            System.out.println("Quiz results:");
            results.forEach(r -> System.out.println("Result ID: " + r.getId() + ", Quiz ID: " + r.getQuizId() + ", Score: " + r.getScore()));
        }
    }
}
