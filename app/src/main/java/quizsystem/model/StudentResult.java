package quizsystem.model;

import java.sql.Timestamp;

public class StudentResult {
    private int id;
    private int userId;
    private int quizId;
    private int score;
    private Timestamp completionDate;

    public StudentResult() {
    }

    public StudentResult(int id, int userId, int quizId, int score, Timestamp completionDate) {
        this.id = id;
        this.userId = userId;
        this.quizId = quizId;
        this.score = score;
        this.completionDate = completionDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Timestamp getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Timestamp completionDate) {
        this.completionDate = completionDate;
    }
}
