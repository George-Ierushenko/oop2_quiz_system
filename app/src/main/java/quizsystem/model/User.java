package quizsystem.model;

public class User {
    private int id;
    private String username;
    private String password;
    private UserType userType;

    public enum UserType {
        INSTRUCTOR, STUDENT;

        public static UserType fromString(String type) {
            for (UserType userType : UserType.values()) {
                if (userType.name().equalsIgnoreCase(type)) {
                    return userType;
                }
            }
            throw new IllegalArgumentException("No constant with text " + type + " found");
        }
    }

    public User() {}

    public User(int id, String username, String password, String userType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userType = UserType.fromString(userType);
    }

    public User(int id, String username, String password, UserType userType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = UserType.fromString(userType);
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
