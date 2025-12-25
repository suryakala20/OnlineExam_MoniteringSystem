CREATE DATABASE online_exam;
USE online_exam;
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(100),
    role ENUM('ADMIN','STUDENT')
);
CREATE TABLE exams (
    exam_id INT AUTO_INCREMENT PRIMARY KEY,
    exam_name VARCHAR(100),
    duration INT, -- minutes
    total_marks INT
);
CREATE TABLE questions (
    question_id INT AUTO_INCREMENT PRIMARY KEY,
    exam_id INT,
    question TEXT,
    option_a VARCHAR(200),
    option_b VARCHAR(200),
    option_c VARCHAR(200),
    option_d VARCHAR(200),
    correct_option CHAR(1),
    FOREIGN KEY (exam_id) REFERENCES exams(exam_id)
);
CREATE TABLE exam_attempts (
    attempt_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    exam_id INT,
    start_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    end_time TIMESTAMP,
    score INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (exam_id) REFERENCES exams(exam_id)
);
CREATE TABLE activity_logs (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    attempt_id INT,
    activity VARCHAR(100),
    log_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (attempt_id) REFERENCES exam_attempts(attempt_id)
);
CREATE TABLE admin_logs (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    admin_id INT,
    action VARCHAR(100),
    log_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO users (username, password, role) VALUES
('admin2', 'admin@123', 'ADMIN'),
('admin', 'admin123', 'ADMIN'),
('student1', 'stud123', 'STUDENT');

INSERT INTO exams (exam_name, duration, total_marks) VALUES
('Java Test', 30, 100);

INSERT INTO questions (exam_id, question, option_a, option_b, option_c, option_d, correct_option) VALUES
(1, 'What is JVM?', 'Java Virtual Machine', 'Java Very Much', 'Just Virtual Machine', 'None', 'A'),
(1, 'Which keyword is used to inherit class?', 'this', 'super', 'extends', 'implements', 'C');

SELECT * FROM exam_attempts;
SELECT * FROM activity_logs;
SELECT * FROM questions;
SELECT * FROM admin_logs;



