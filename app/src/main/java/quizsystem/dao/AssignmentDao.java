package quizsystem.dao;

import quizsystem.model.Assignment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssignmentDao {
    private Connection connection;

    public AssignmentDao(Connection connection) {
        this.connection = connection;
    }

    public boolean insertAssignment(Assignment assignment) throws SQLException {
        String sql = "INSERT INTO student_assignments (user_id, quiz_id, status) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, assignment.getUserId());
            statement.setInt(2, assignment.getQuizId());
            statement.setString(3, assignment.getStatus());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                return false;
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    assignment.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Creating assignment failed, no ID obtained.");
                }
            }
            return true;
        }
    }

    public Assignment getAssignment(int id) throws SQLException {
        String sql = "SELECT * FROM student_assignments WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Assignment(
                    resultSet.getInt("id"),
                    resultSet.getInt("user_id"),
                    resultSet.getInt("quiz_id"),
                    resultSet.getString("status")
                );
            }
        }
        return null;
    }

    public List<Assignment> findAssignmentsByUserId(int userId) throws SQLException {
        List<Assignment> assignments = new ArrayList<>();
        String sql = "SELECT * FROM student_assignments WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                assignments.add(new Assignment(
                    resultSet.getInt("id"),
                    resultSet.getInt("user_id"),
                    resultSet.getInt("quiz_id"),
                    resultSet.getString("status")
                ));
            }
        }
        return assignments;
    }

    public List<Assignment> findAssignmentsByQuizId(int quizId) throws SQLException {
        List<Assignment> assignments = new ArrayList<>();
        String sql = "SELECT * FROM student_assignments WHERE quiz_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, quizId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                assignments.add(new Assignment(
                    resultSet.getInt("id"),
                    resultSet.getInt("user_id"),
                    resultSet.getInt("quiz_id"),
                    resultSet.getString("status")
                ));
            }
        }
        return assignments;
    }

    public boolean updateAssignment(Assignment assignment) throws SQLException {
        String sql = "UPDATE student_assignments SET user_id = ?, quiz_id = ?, status = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, assignment.getUserId());
            statement.setInt(2, assignment.getQuizId());
            statement.setString(3, assignment.getStatus());
            statement.setInt(4, assignment.getId());
            return statement.executeUpdate() > 0;
        }
    }

    public boolean deleteAssignment(int id) throws SQLException {
        String sql = "DELETE FROM student_assignments WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }
}
