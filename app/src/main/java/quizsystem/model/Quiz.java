package quizsystem.model;

import java.sql.Timestamp;

public class Quiz {
    private int id;
    private int instructorId;
    private String title;
    private int timeLimit;
    private Timestamp createDate;

    public Quiz() {
    }

    public Quiz(int id, int instructorId, String title, int timeLimit, Timestamp createDate) {
        this.id = id;
        this.instructorId = instructorId;
        this.title = title;
        this.timeLimit = timeLimit;
        this.createDate = createDate;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}
