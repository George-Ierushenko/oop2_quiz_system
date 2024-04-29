package quizsystem.service;

import quizsystem.dao.StudentResultsDao;
import quizsystem.model.StudentResult;

import java.util.List;

public class StudentResultService {
    private StudentResultsDao studentResultsDao;

    public StudentResultService(StudentResultsDao studentResultsDao) {
        this.studentResultsDao = studentResultsDao;
    }

    public boolean recordResult(StudentResult result) {
        try {
            return studentResultsDao.insertStudentResult(result);
        } catch (Exception e) {
            System.out.println("Error recording result: " + e.getMessage());
            return false;
        }
    }

    public StudentResult getResult(int resultId) {
        try {
            return studentResultsDao.getStudentResult(resultId);
        } catch (Exception e) {
            System.out.println("Error retrieving result: " + e.getMessage());
            return null;
        }
    }

    public List<StudentResult> getResultsByQuiz(int quizId) {
        try {
            return studentResultsDao.findResultsByQuizId(quizId);
        } catch (Exception e) {
            System.out.println("Error retrieving results for quiz: " + e.getMessage());
            return null;
        }
    }

    public List<StudentResult> getResultsByUser(int userId) {
        try {
            return studentResultsDao.findResultsByUserId(userId);
        } catch (Exception e) {
            System.out.println("Error retrieving results for user: " + e.getMessage());
            return null;
        }
    }

    public boolean updateResult(StudentResult result) {
        try {
            return studentResultsDao.updateStudentResult(result);
        } catch (Exception e) {
            System.out.println("Error updating result: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteResult(int resultId) {
        try {
            return studentResultsDao.deleteStudentResult(resultId);
        } catch (Exception e) {
            System.out.println("Error deleting result: " + e.getMessage());
            return false;
        }
    }
}
