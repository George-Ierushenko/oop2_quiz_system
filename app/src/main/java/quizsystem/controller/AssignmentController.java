package quizsystem.controller;

import quizsystem.model.Assignment;
import quizsystem.model.Quiz;
import quizsystem.model.Session;
import quizsystem.service.AssignmentService;

import java.util.List;

public class AssignmentController extends Controller {
    private AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        super();

        this.assignmentService = assignmentService;
    }

    public void assignQuizToStudent() {
        System.out.println("Enter quiz ID:");
        int quizId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter user ID:");
        int userId = Integer.parseInt(scanner.nextLine());

        if (assignmentService.assignQuizToStudent(quizId, userId)) {
            System.out.println("Quiz successfully assigned to student.");
        } else {
            System.out.println("Failed to assign quiz to student.");
        }
    }

    public void listAssignmentsForUser(int userId) {
        List<Assignment> assignments = assignmentService.listAssignmentsForUser(userId);

        if (assignments.isEmpty()) {
            System.out.println("No assignments found for this user.");
        } else {
            System.out.println("\n");
            System.out.println("Assignments for user ID " + userId + ":");
            assignments.forEach(assignment -> System.out.println("Assignment ID: " + assignment.getId() + ", Quiz ID: " + assignment.getQuizId()));
        }
    }

    public void updateAssignmentStatus() {
        int assignmentId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter new status (Assigned/Completed):");
        String status = scanner.nextLine();

        if (assignmentService.updateAssignmentStatus(assignmentId, status)) {
            System.out.println("Assignment status updated successfully.");
        } else {
            System.out.println("Failed to update assignment status.");
        }
    }

    public void deleteAssignment() {
        System.out.println("Enter assignment ID to delete:");
        int assignmentId = Integer.parseInt(scanner.nextLine());

        if (assignmentService.deleteAssignment(assignmentId)) {
            System.out.println("Assignment deleted successfully.");
        } else {
            System.out.println("Failed to delete assignment.");
        }
    }
}
