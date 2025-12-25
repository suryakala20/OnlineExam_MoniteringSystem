package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import db.DBConnection;
public class LogDAO {
    public static void logActivity(int attemptId, String activity) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO activity_logs (attempt_id, activity) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, attemptId);
            ps.setString(2, activity);
            ps.executeUpdate();
            System.out.println(" Activity Logged: " + activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
