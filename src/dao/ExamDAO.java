package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import db.DBConnection;
public class ExamDAO {
    public static int startExam(int userId, int examId) {
        int attemptId = -1;
        try {
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO exam_attempts (user_id, exam_id) VALUES (?, ?)";
            PreparedStatement ps =
                    con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, userId);
            ps.setInt(2, examId);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                attemptId = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return attemptId;
    }
    public static void submitExam(int attemptId, int score) {
        try {
            Connection con = DBConnection.getConnection();
            String sql =
                "UPDATE exam_attempts SET end_time = NOW(), score = ? WHERE attempt_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, score);
            ps.setInt(2, attemptId);
            ps.executeUpdate();
            System.out.println("Exam submitted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void viewStudentResults(int userId) {
        try {
            Connection con = DBConnection.getConnection();
            String sql =
                "SELECT exam_id, score, start_time, end_time " +
                "FROM exam_attempts WHERE user_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            System.out.println("\n----- Your Exam Results -----");
            while (rs.next()) {
                System.out.println(
                    "Exam ID: " + rs.getInt("exam_id") +
                    " | Score: " + rs.getInt("score") +
                    " | Start Time: " + rs.getTimestamp("start_time") +
                    " | End Time: " + rs.getTimestamp("end_time")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void viewAllResults() {
        try {
            Connection con = DBConnection.getConnection();
            String sql =
                "SELECT u.username, e.exam_name, a.score " +
                "FROM exam_attempts a " +
                "JOIN users u ON a.user_id = u.user_id " +
                "JOIN exams e ON a.exam_id = e.exam_id";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            System.out.println("\n----- All Student Results -----");
            while (rs.next()) {
                System.out.println(
                    "Student: " + rs.getString("username") +
                    " | Exam: " + rs.getString("exam_name") +
                    " | Score: " + rs.getInt("score")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
