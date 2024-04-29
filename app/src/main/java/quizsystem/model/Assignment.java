package quizsystem.model;

public class Assignment {
    private int id;
    private int userId;
    private int quizId;
    private String status;

    public Assignment() {
    }

    public Assignment(int id, int userId, int quizId, String status) {
        this.id = id;
        this.userId = userId;
        this.quizId = quizId;
        this.status = status;
    }

    // Getters and setters
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
