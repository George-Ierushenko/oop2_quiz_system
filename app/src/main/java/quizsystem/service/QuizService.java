package quizsystem.service;

import quizsystem.dao.QuizDao;
import quizsystem.model.Quiz;

import java.util.List;

public class QuizService {
    private QuizDao quizDao;

    public QuizService(QuizDao quizDao) {
        this.quizDao = quizDao;
    }

    public boolean createQuiz(Quiz quiz) {
        try {
            return quizDao.insertQuiz(quiz);
        } catch (Exception e) {
            System.out.println("Error creating quiz: " + e.getMessage());
            return false;
        }
    }

    public Quiz getQuiz(int quizId) {
        try {
            return quizDao.getQuiz(quizId);
        } catch (Exception e) {
            System.out.println("Error retrieving quiz: " + e.getMessage());
            return null;
        }
    }

    public List<Quiz> listQuizzes() {
        try {
            return quizDao.findAllQuizzes();
        } catch (Exception e) {
            System.out.println("Error listing quizzes: " + e.getMessage());
            return null;
        }
    }

    public boolean updateQuiz(Quiz quiz) {
        try {
            Quiz existingQuiz = quizDao.getQuiz(quiz.getId());
            if (existingQuiz == null) {
                System.out.println("No such quiz exists to update.");
                return false;
            }
            return quizDao.updateQuiz(quiz);
        } catch (Exception e) {
            System.out.println("Error updating quiz: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteQuiz(int quizId) {
        try {
            return quizDao.deleteQuiz(quizId);
        } catch (Exception e) {
            System.out.println("Error deleting quiz: " + e.getMessage());
            return false;
        }
    }
}
