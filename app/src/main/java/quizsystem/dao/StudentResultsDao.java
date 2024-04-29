package quizsystem.dao;

import quizsystem.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentResultsDao {
    private Connection connection;

    public StudentResultsDao(Connection connection) {
        this.connection = connection;
    }

    public boolean insertStudentResult(StudentResult result) throws SQLException {
        String sql = "INSERT INTO student_results (user_id, quiz_id, score, completion_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, result.getUserId());
            statement.setInt(2, result.getQuizId());
            statement.setInt(3, result.getScore());
            statement.setTimestamp(4, result.getCompletionDate());
            return statement.executeUpdate() > 0;
        }
    }

    public StudentResult getStudentResult(int id) throws SQLException {
        String sql = "SELECT * FROM student_results WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new StudentResult(
                    resultSet.getInt("id"),
                    resultSet.getInt("user_id"),
                    resultSet.getInt("quiz_id"),
                    resultSet.getInt("score"),
                    resultSet.getTimestamp("completion_date")
                );
            }
        }
        return null;
    }

    public List<StudentResult> findResultsByQuizId(int quizId) throws SQLException {
        List<StudentResult> results = new ArrayList<>();
        String sql = "SELECT * FROM student_results WHERE quiz_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, quizId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(new StudentResult(
                    resultSet.getInt("id"),
                    resultSet.getInt("user_id"),
                    resultSet.getInt("quiz_id"),
                    resultSet.getInt("score"),
                    resultSet.getTimestamp("completion_date")
                ));
            }
        }
        return results;
    }

    public List<StudentResult> findResultsByUserId(int userId) throws SQLException {
        List<StudentResult> results = new ArrayList<>();
        String sql = "SELECT * FROM student_results WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(new StudentResult(
                    resultSet.getInt("id"),
                    resultSet.getInt("user_id"),
                    resultSet.getInt("quiz_id"),
                    resultSet.getInt("score"),
                    resultSet.getTimestamp("completion_date")
                ));
            }
        }
        return results;
    }

    public boolean updateStudentResult(StudentResult result) throws SQLException {
        String sql = "UPDATE student_results SET user_id = ?, quiz_id = ?, score = ?, completion_date = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, result.getUserId());
            statement.setInt(2, result.getQuizId());
            statement.setInt(3, result.getScore());
            statement.setTimestamp(4, result.getCompletionDate());
            statement.setInt(5, result.getId());
            return statement.executeUpdate() > 0;
        }
    }

    public boolean deleteStudentResult(int id) throws SQLException {
        String sql = "DELETE FROM student_results WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }
}
