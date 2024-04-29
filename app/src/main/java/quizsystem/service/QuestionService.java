package quizsystem.service;

import quizsystem.dao.*;
import quizsystem.model.*;

import java.util.List;

public class QuestionService {
    private QuizQuestionsDao quizQuestionsDao;

    public QuestionService(QuizQuestionsDao quizQuestionsDao) {
        this.quizQuestionsDao = quizQuestionsDao;
    }

    public boolean addQuestionToQuiz(QuizQuestion question, int quizId) {
        try {
            question.setQuizId(quizId);
            return quizQuestionsDao.insertQuestion(question);
        } catch (Exception e) {
            System.out.println("Error adding question to quiz: " + e.getMessage());
            return false;
        }
    }

    public boolean updateQuestion(QuizQuestion question) {
        try {
            // Ensure the question exists before attempting update
            if (quizQuestionsDao.getQuestion(question.getId()) == null) {
                System.out.println("No such question exists to update.");
                return false;
            }
            return quizQuestionsDao.updateQuestion(question);
        } catch (Exception e) {
            System.out.println("Error updating question: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteQuestion(int questionId) {
        try {
            return quizQuestionsDao.deleteQuestion(questionId);
        } catch (Exception e) {
            System.out.println("Error deleting question: " + e.getMessage());
            return false;
        }
    }

    public QuizQuestion getQuestion(int questionId) {
        try {
            return quizQuestionsDao.getQuestion(questionId);
        } catch (Exception e) {
            System.out.println("Error retrieving question: " + e.getMessage());
            return null;
        }
    }

    public List<QuizQuestion> getQuestions(int quizId) {
        try {
            return quizQuestionsDao.findQuestionsByQuizId(quizId);
        } catch (Exception e) {
            System.out.println("Error listing questions for quiz: " + e.getMessage());
            return null;
        }
    }
}
