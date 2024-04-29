package quizsystem.service;

import quizsystem.model.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthenticationService {

    private UserService userService;

    public AuthenticationService(UserService userService) {
        this.userService = userService;
    }

    public User login(String username, String password) {
        try {
            User user = userService.getUserByUsername(username);
            if (user != null && user.getPassword().equals(hashPassword(password))) {
                Session.setCurrentUser(user);
                return user;
            }
        } catch (Exception e) {
            System.out.println("Login error: " + e.getMessage());
        }
        return null;
    }

    public User register(String username, String password, String userType) {
        try {
            User existingUser = userService.getUserByUsername(username);
            if (existingUser != null) {
                return existingUser;
            }

            String hashedPassword = hashPassword(password);
            User newUser = new User(0, username, hashedPassword, User.UserType.fromString(userType));
            Boolean result = userService.createUser(newUser);
            if (result) {
                Session.setCurrentUser(newUser);
                return newUser;
            }
        } catch (Exception e) {
            System.out.println("Registration error: " + e.getMessage());
        }
        return null;
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to hash password", e);
        }
    }
}
