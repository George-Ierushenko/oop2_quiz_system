package quizsystem.controller;

import quizsystem.model.User;
import quizsystem.service.UserService;
import quizsystem.service.AuthenticationService;

public class UserController extends Controller {
    private UserService userService;
    private AuthenticationService authService;

    public UserController(UserService userService, AuthenticationService authService) {
        super();

        this.userService = userService;
        this.authService = authService;
    }

    public User login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = authService.login(username, password);
        if (user != null) {
            System.out.println("Login successful. Welcome, " + user.getUsername() + "!");
            return user;
        } else {
            System.out.println("Login failed. Please check your username and password.");
            return null;
        }
    }

    public User register() {
        System.out.print("Enter new username: ");
        String username = scanner.nextLine();
        System.out.print("Enter new password: ");
        String password = scanner.nextLine();
        System.out.print("Enter user type (instructor/student): ");
        String userType = scanner.nextLine();

        User user = authService.register(username, password, userType);
        if (user != null) {
            System.out.println("Registration successful.");
        } else {
            System.out.println("Registration failed. Username might be taken, or data is invalid.");
        }

        return user;
    }

    public void updateUserDetails() {
        System.out.print("Enter user ID: ");
        int userId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter new username (leave blank to not change): ");
        String newUsername = scanner.nextLine();
        System.out.print("Enter new password (leave blank to not change): ");
        String newPassword = scanner.nextLine();

        User user = userService.getUser(userId);
        if (user == null) {
            System.out.println("User not found!");
            return;
        }

        if (!newUsername.isEmpty()) user.setUsername(newUsername);
        if (!newPassword.isEmpty()) user.setPassword(newPassword);

        boolean success = userService.updateUser(user);
        if (success) {
            System.out.println("User updated successfully.");
        } else {
            System.out.println("Failed to update user.");
        }
    }

    public void deleteUser() {
        System.out.print("Enter user ID to delete: ");

        int userId = Integer.parseInt(scanner.nextLine());
        boolean success = userService.deleteUser(userId);
        if (success) {
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("Failed to delete user.");
        }
    }
}
