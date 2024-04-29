package quizsystem.cli;

import java.util.Scanner;
import quizsystem.controller.*;
import quizsystem.model.Session;
import quizsystem.model.User;

public class CLI {
    private UserController userController;
    private QuizController quizController;
    private QuestionController questionController;
    private AssignmentController assignmentController;
    private StudentResultController studentResultController;
    private Scanner scanner;

    public CLI(UserController userController, QuizController quizController,
               QuestionController questionController, AssignmentController assignmentController,
               StudentResultController studentResultController) {
        this.userController = userController;
        this.quizController = quizController;
        this.questionController = questionController;
        this.assignmentController = assignmentController;
        this.studentResultController = studentResultController;

        this.scanner = new Scanner(System.in);
    }

    public void runApplication() {
        User user = startSession();

        if (user == null) return;

        showStartingMenu();
    }

    private User startSession() {
        while (true) {
            System.out.println("Welcome to the Quiz System!");
            System.out.println("1. Login\n2. Register\n3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            scanner.nextLine();

            switch (choice) {
                case 1:
                    return userController.login();
                case 2:
                    return userController.register();
                case 3:
                    System.out.println("Exiting the system...");
                    System.exit(0);
                    return null;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void showStartingMenu() {
        User.UserType userType = Session.getCurrentUser().getUserType();
        if (userType.equals(User.UserType.STUDENT)) {
            showStudentMenu();
        }
    }

    private void showStudentMenu() {
        String name = Session.getCurrentUser().getUsername();
        int userId = Session.getCurrentUser().getId();

        System.out.println("\nHello, " + name + "! Welcome to the Student Menu.");

        while (true) {
            System.out.println("\nStudent Menu:");
            System.out.println("1. List My Assigned Quizzes");
            System.out.println("2. Take a Quiz");
            System.out.println("3. View My Past Results");
            System.out.println("4. Logout");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    assignmentController.listAssignmentsForUser(userId);
                    break;

                case 2:
                    int quizId = quizController.promptQuizId();
                    if (quizId < 0) break;
                    quizController.takeQuiz(quizId, userId);
                    break;
                case 3:
                    studentResultController.listResultsByUser(userId);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
