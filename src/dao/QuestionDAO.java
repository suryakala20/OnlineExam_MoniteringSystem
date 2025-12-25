package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import db.DBConnection;
public class QuestionDAO {
    public static void addQuestion(
            int examId,
            String question,
            String optionA,
            String optionB,
            String optionC,
            String optionD,
            String correctOption) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO questions " +
                         "(exam_id, question, option_a, option_b, option_c, option_d, correct_option) " +
                         "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, examId);
            ps.setString(2, question);
            ps.setString(3, optionA);
            ps.setString(4, optionB);
            ps.setString(5, optionC);
            ps.setString(6, optionD);
            ps.setString(7, correctOption);
            ps.executeUpdate();
            System.out.println("Question added successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
