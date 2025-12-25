package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentDashboard extends JFrame {

    public StudentDashboard() {

        setTitle("Student Dashboard");
        setSize(420, 380);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(50, 50, 50));

        JLabel title = new JLabel("STUDENT PANEL");
        title.setBounds(120, 20, 200, 30);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.WHITE);

        JButton start = new JButton("Start Exam");
        JButton submit = new JButton("Submit Exam");
        JButton result = new JButton("View Result");
        JButton logout = new JButton("Logout");

        start.setBounds(120, 80, 180, 32);
        submit.setBounds(120, 130, 180, 32);
        result.setBounds(120, 180, 180, 32);
        logout.setBounds(120, 230, 180, 32);

        JButton[] btns = {start, submit, result, logout};
        for (JButton b : btns) {
            b.setBackground(new Color(0, 153, 255));
            b.setForeground(Color.WHITE);
            panel.add(b);
        }

        logout.setBackground(new Color(204, 0, 0));

        panel.add(title);
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
