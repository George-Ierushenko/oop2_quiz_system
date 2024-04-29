package quizsystem;

import java.sql.Connection;

import quizsystem.cli.CLI;
import quizsystem.config.DatabaseConfig;
import quizsystem.controller.AssignmentController;
import quizsystem.controller.QuestionController;
import quizsystem.controller.QuizController;
import quizsystem.controller.StudentResultController;
import quizsystem.controller.UserController;
import quizsystem.dao.AssignmentDao;
import quizsystem.dao.QuizDao;
import quizsystem.dao.QuizQuestionsDao;
import quizsystem.dao.StudentResultsDao;
import quizsystem.dao.UserDao;
import quizsystem.service.AssignmentService;
import quizsystem.service.AuthenticationService;
import quizsystem.service.QuestionService;
import quizsystem.service.QuizService;
import quizsystem.service.StudentResultService;
import quizsystem.service.UserService;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DatabaseConfig.getConnection();

            // DAOs
            UserDao userDao = new UserDao(connection);
            QuizDao quizDao = new QuizDao(connection);
            QuizQuestionsDao QuizQuestionsDao = new QuizQuestionsDao(connection);
            StudentResultsDao studentResultsDao = new StudentResultsDao(connection);
            AssignmentDao assignmentDao = new AssignmentDao(connection);

            // Services
            UserService userService = new UserService(userDao);
            AuthenticationService authService = new AuthenticationService(userService);
            QuizService quizService = new QuizService(quizDao);
            QuestionService questionService = new QuestionService(QuizQuestionsDao);
            StudentResultService studentResultService = new StudentResultService(studentResultsDao);
            AssignmentService assignmentService = new AssignmentService(assignmentDao);

            // Controllers
            UserController userController = new UserController(userService, authService);
            QuizController quizController = new QuizController(quizService, questionService, studentResultService);
            QuestionController questionController = new QuestionController(questionService);
            StudentResultController studentResultController = new StudentResultController(studentResultService);
            AssignmentController assignmentController = new AssignmentController(assignmentService);

            CLI cli = new CLI(userController, quizController, questionController, assignmentController, studentResultController);
            cli.runApplication();
        } catch (Error e) {
            System.out.println("Error in the main thread: " + e.getMessage());
        }
    }
}
