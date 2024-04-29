package quizsystem.dao;

import quizsystem.model.*;
import java.util.ArrayList;
import java.sql.*;
import java.util.List;

public class QuizQuestionsDao {

    private Connection connection;

    public QuizQuestionsDao(Connection connection) {
        this.connection = connection;
    }

    public boolean insertQuestion(QuizQuestion question) throws SQLException {
        String sql = "INSERT INTO quiz_questions (quiz_id, question_text, option_a, option_b, option_c, option_d, correct_option) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, question.getQuizId());
            statement.setString(2, question.getQuestionText());
            statement.setString(3, question.getOptionA());
            statement.setString(4, question.getOptionB());
            statement.setString(5, question.getOptionC());
            statement.setString(6, question.getOptionD());
            statement.setString(7, Character.toString(question.getCorrectOption()));
            return statement.executeUpdate() > 0;
        }
    }

    public QuizQuestion getQuestion(int id) throws SQLException {
        String sql = "SELECT * FROM quiz_questions WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new QuizQuestion(
                    resultSet.getInt("id"),
                    resultSet.getInt("quiz_id"),
                    resultSet.getString("question_text"),
                    resultSet.getString("option_a"),
                    resultSet.getString("option_b"),
                    resultSet.getString("option_c"),
                    resultSet.getString("option_d"),
                    resultSet.getString("correct_option").charAt(0)
                );
            }
        }
        return null;
    }

    public List<QuizQuestion> findQuestionsByQuizId(int quizId) throws SQLException {
        List<QuizQuestion> questions = new ArrayList<>();
        String sql = "SELECT * FROM quiz_questions WHERE quiz_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, quizId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                questions.add(new QuizQuestion(
                    resultSet.getInt("id"),
                    resultSet.getInt("quiz_id"),
                    resultSet.getString("question_text"),
                    resultSet.getString("option_a"),
                    resultSet.getString("option_b"),
                    resultSet.getString("option_c"),
                    resultSet.getString("option_d"),
                    resultSet.getString("correct_option").charAt(0)
                ));
            }
        }
        return questions;
    }

    public boolean updateQuestion(QuizQuestion question) throws SQLException {
        String sql = "UPDATE quiz_questions SET quiz_id = ?, question_text = ?, option_a = ?, option_b = ?, option_c = ?, option_d = ?, correct_option = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, question.getQuizId());
            statement.setString(2, question.getQuestionText());
            statement.setString(3, question.getOptionA());
            statement.setString(4, question.getOptionB());
            statement.setString(5, question.getOptionC());
            statement.setString(6, question.getOptionD());
            statement.setString(7, String.valueOf(question.getCorrectOption()));
            statement.setInt(8, question.getId());
            return statement.executeUpdate() > 0;
        }
    }

    public boolean deleteQuestion(int id) throws SQLException {
        String sql = "DELETE FROM quiz_questions WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }
}
