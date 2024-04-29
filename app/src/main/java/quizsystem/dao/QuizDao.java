package quizsystem.dao;

import quizsystem.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizDao {

    private Connection connection;

    public QuizDao(Connection connection) {
        this.connection = connection;
    }

    public boolean insertQuiz(Quiz quiz) throws SQLException {
        String sql = "INSERT INTO quizzes (instructor_id, title, time_limit, create_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, quiz.getInstructorId());
            statement.setString(2, quiz.getTitle());
            statement.setInt(3, quiz.getTimeLimit());
            statement.setTimestamp(4, quiz.getCreateDate());
            return statement.executeUpdate() > 0;
        }
    }

    public Quiz getQuiz(int id) throws SQLException {
        String sql = "SELECT * FROM quizzes WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Quiz(
                    resultSet.getInt("id"),
                    resultSet.getInt("instructor_id"),
                    resultSet.getString("title"),
                    resultSet.getInt("time_limit"),
                    resultSet.getTimestamp("create_date")
                );
            }
        }
        return null;
    }

    public List<Quiz> findAllQuizzes() throws SQLException {
        List<Quiz> quizzes = new ArrayList<>();
        String sql = "SELECT * FROM quizzes";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                quizzes.add(new Quiz(
                        resultSet.getInt("id"),
                        resultSet.getInt("instructor_id"),
                        resultSet.getString("title"),
                        resultSet.getInt("time_limit"),
                        resultSet.getTimestamp("create_date")
                ));
            }
        }
        return quizzes;
    }

    public boolean updateQuiz(Quiz quiz) throws SQLException {
        String sql = "UPDATE quizzes SET instructor_id = ?, title = ?, time_limit = ?, create_date = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, quiz.getInstructorId());
            statement.setString(2, quiz.getTitle());
            statement.setInt(3, quiz.getTimeLimit());
            statement.setTimestamp(4, quiz.getCreateDate());
            statement.setInt(5, quiz.getId());
            return statement.executeUpdate() > 0;
        }
    }

    public boolean deleteQuiz(int id) throws SQLException {
        String sql = "DELETE FROM quizzes WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }
}
