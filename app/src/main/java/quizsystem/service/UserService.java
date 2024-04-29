package quizsystem.service;

import quizsystem.dao.UserDao;
import quizsystem.model.User;

import java.util.List;

public class UserService {
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean createUser(User user) {
        try {
            if (getUserByUsername(user.getUsername()) != null) {
                System.out.println("Registration failed: Username already exists.");
                return false;
            }

            return userDao.insertUser(user);
        } catch (Exception e) {
            System.out.println("Error creating user: " + e.getMessage());
            return false;
        }
    }

    public User getUser(int id) {
        try {
            return userDao.getUser(id);
        } catch (Exception e) {
            System.out.println("Error retrieving user: " + e.getMessage());
            return null;
        }
    }

    public User getUserByUsername(String username) {
        try {
            return userDao.getUserByUsername(username);
        } catch (Exception e) {
            System.out.println("Error retrieving user by username: " + e.getMessage());
            return null;
        }
    }

    public List<User> getAllUsers() {
        try {
            return userDao.findAll();
        } catch (Exception e) {
            System.out.println("Error listing users: " + e.getMessage());
            return null;
        }
    }

    public boolean updateUser(User user) {
        try {
            User existingUser = userDao.getUser(user.getId());
            if (existingUser == null) {
                System.out.println("Update failed: No such user exists.");
                return false;
            }
            return userDao.updateUser(user);
        } catch (Exception e) {
            System.out.println("Error updating user: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteUser(int id) {
        try {
            return userDao.deleteUser(id);
        } catch (Exception e) {
            System.out.println("Error deleting user: " + e.getMessage());
            return false;
        }
    }
}
