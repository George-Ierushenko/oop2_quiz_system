package quizsystem.service;

import quizsystem.dao.AssignmentDao;
import quizsystem.model.Assignment;

import java.util.List;

public class AssignmentService {
    private AssignmentDao assignmentDao;

    public AssignmentService(AssignmentDao assignmentDao) {
        this.assignmentDao = assignmentDao;
    }

    public boolean assignQuizToStudent(int quizId, int userId) {
        try {
            Assignment assignment = new Assignment();
            assignment.setQuizId(quizId);
            assignment.setUserId(userId);
            return assignmentDao.insertAssignment(assignment);
        } catch (Exception e) {
            System.out.println("Error assigning quiz to student: " + e.getMessage());
            return false;
        }
    }

    public List<Assignment> listAssignmentsForUser(int userId) {
        try {
            return assignmentDao.findAssignmentsByUserId(userId);
        } catch (Exception e) {
            System.out.println("Error listing assignments for user: " + e.getMessage());
            return null;
        }
    }

    public List<Assignment> listAssignmentsForQuiz(int quizId) {
        try {
            return assignmentDao.findAssignmentsByQuizId(quizId);
        } catch (Exception e) {
            System.out.println("Error listing assignments for quiz: " + e.getMessage());
            return null;
        }
    }

    public Assignment getAssignmentDetails(int assignmentId) {
        try {
            return assignmentDao.getAssignment(assignmentId);
        } catch (Exception e) {
            System.out.println("Error retrieving assignment details: " + e.getMessage());
            return null;
        }
    }

    public boolean updateAssignmentStatus(int assignmentId, String status) {
        try {
            Assignment assignment = assignmentDao.getAssignment(assignmentId);
            if (assignment == null) {
                System.out.println("No such assignment exists.");
                return false;
            }
            assignment.setStatus(status);
            return assignmentDao.updateAssignment(assignment);
        } catch (Exception e) {
            System.out.println("Error updating assignment status: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteAssignment(int assignmentId) {
        try {
            return assignmentDao.deleteAssignment(assignmentId);
        } catch (Exception e) {
            System.out.println("Error deleting assignment: " + e.getMessage());
            return false;
        }
    }
}
