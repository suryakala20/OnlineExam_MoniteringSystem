package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dao.UserDAO;

public class LoginFrame extends JFrame {

    private JTextField userField;
    private JPasswordField passField;

    public LoginFrame() {

        setTitle("Online Exam Proctoring");
        setSize(400, 260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(30, 30, 60));

        JLabel title = new JLabel("LOGIN");
        title.setBounds(150, 20, 200, 30);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.WHITE);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(60, 70, 100, 25);
        userLabel.setForeground(Color.WHITE);

        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(60, 110, 100, 25);
        passLabel.setForeground(Color.WHITE);

        userField = new JTextField();
        userField.setBounds(160, 70, 160, 25);

        passField = new JPasswordField();
        passField.setBounds(160, 110, 160, 25);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(140, 160, 120, 30);
        loginBtn.setBackground(new Color(0, 153, 255));
        loginBtn.setForeground(Color.WHITE);

        panel.add(title);
        panel.add(userLabel);
        panel.add(passLabel);
        panel.add(userField);
        panel.add(passField);
        panel.add(loginBtn);

        add(panel);

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                String username = userField.getText();
                String password = new String(passField.getPassword());

                String result = UserDAO.login(username, password);

                if (result != null) {
                    JOptionPane.showMessageDialog(null, "Login Successful");
                    dispose();

                    if (result.contains("ADMIN")) {
                        new AdminDashboard().setVisible(true);
                    } else {
                        new StudentDashboard().setVisible(true);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Login");
                }
            }
        });
    }

    public static void main(String[] args) {
        new LoginFrame().setVisible(true);
    }
}
