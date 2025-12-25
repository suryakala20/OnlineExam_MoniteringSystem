package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import db.DBConnection;
public class UserDAO {
    public static String login(String username, String password) {
        String result = null;
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT user_id, role FROM users WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("user_id");
                String role = rs.getString("role");
                result = userId + ":" + role;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
