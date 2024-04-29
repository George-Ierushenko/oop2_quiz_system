-- Create schema for Quiz System
CREATE DATABASE IF NOT EXISTS quiz_system;
USE quiz_system;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,

    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,

    user_type ENUM('instructor', 'student') NOT NULL
);

CREATE TABLE quizzes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    instructor_id INT NOT NULL,

    title VARCHAR(100) NOT NULL,
    time_limit INT,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (instructor_id) REFERENCES users(id)
);

CREATE TABLE quiz_questions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    quiz_id INT NOT NULL,

    question_text TEXT NOT NULL,

    option_a VARCHAR(100) NOT NULL,
    option_b VARCHAR(100) NOT NULL,
    option_c VARCHAR(100),
    option_d VARCHAR(100),
    correct_option ENUM('A', 'B', 'C', 'D') NOT NULL,

    FOREIGN KEY (quiz_id) REFERENCES quizzes(id)
);

CREATE TABLE student_results (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    quiz_id INT NOT NULL,

    score INT NOT NULL,
    completion_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (quiz_id) REFERENCES quizzes(id)
);

CREATE TABLE student_assignments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    quiz_id INT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (quiz_id) REFERENCES quizzes(id)
);
