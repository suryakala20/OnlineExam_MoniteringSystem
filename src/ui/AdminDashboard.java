package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard extends JFrame {

    public AdminDashboard() {

        setTitle("Admin Dashboard");
        setSize(420, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(20, 40, 40));

        JLabel title = new JLabel("ADMIN PANEL");
        title.setBounds(130, 20, 200, 30);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.WHITE);

        JButton addQ = new JButton("Add Question");
        JButton viewR = new JButton("View Results");
        JButton logout = new JButton("Logout");

        addQ.setBounds(120, 80, 180, 35);
        viewR.setBounds(120, 130, 180, 35);
        logout.setBounds(120, 180, 180, 35);

        addQ.setBackground(new Color(0, 153, 255));
        viewR.setBackground(new Color(0, 204, 102));
        logout.setBackground(new Color(204, 0, 0));

        addQ.setForeground(Color.WHITE);
        viewR.setForeground(Color.WHITE);
        logout.setForeground(Color.WHITE);

        panel.add(title);
        panel.add(addQ);
        panel.add(viewR);
        panel.add(logout);

        add(panel);

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                dispose();
                new LoginFrame().setVisible(true);
            }
        });
    }
}
