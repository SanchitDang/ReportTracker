package org.sanapplications;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportApp extends JFrame {
    private JTextField reportField;

    public ReportApp() {
        super("Report Entry");

        reportField = new JTextField(20);
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Process the report entry here
                String report = reportField.getText();
                // You can save the report to a file or database
                System.out.println("Report submitted: " + report);
                reportField.setText(""); // Clear the field after submission
            }
        });

        JPanel panel = new JPanel(new FlowLayout());
        panel.add(new JLabel("Enter report: "));
        panel.add(reportField);
        panel.add(submitButton);

        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the window
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ReportApp app = new ReportApp();
                app.setVisible(true);
                // Schedule the task to run every 2 hour
                Timer timer = new Timer(2 * 60 * 60 * 1000, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Show the window
                        app.setVisible(true);
                        // Optionally, bring the window to front
                        app.toFront();
                    }
                });
                timer.setInitialDelay(0); // Start immediately
                timer.start();
            }
        });
    }
}
