package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import db.DBConnection;
public class AdminLogDAO {
    public static void logAdminAction(int adminId, String action) {
        try {
            Connection con = DBConnection.getConnection();
            String sql =
                "INSERT INTO admin_logs (admin_id, action) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, adminId);
            ps.setString(2, action);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
