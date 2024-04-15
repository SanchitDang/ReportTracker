package org.sanapplications.GUI;

import org.sanapplications.Services.FileService;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomePage extends JFrame {
    private JTextArea reportField;
    private JLabel timeLabel;

    public HomePage() {
        super("Report Entry");
        initialize();
    }

    private void initialize() {
        getContentPane().setLayout(null);

        JButton todayReportButton = new JButton("Today's Report");
        todayReportButton.setBounds(55, 40, 120, 30);
        JButton oldReportsButton = new JButton("Old Reports");
        oldReportsButton.setBounds(185, 40, 120, 30);
        JButton settingsButton = new JButton("Settings");
        settingsButton.setBounds(315, 40, 120, 30);

        settingsButton.addActionListener(e -> showSettingsDialog());

        oldReportsButton.addActionListener(e -> {
            ReportsHistoryPage reportsHistory = new ReportsHistoryPage(false);
            reportsHistory.setVisible(true);
        });

        todayReportButton.addActionListener(e -> {
            ReportsHistoryPage reportsHistory = new ReportsHistoryPage(true);
            reportsHistory.setVisible(true);
        });

        JSeparator topSeparator = new JSeparator(SwingConstants.HORIZONTAL);
        topSeparator.setBounds(55, 80, 380, 10);

        JLabel addReportLabel = new JLabel("ADD REPORT");
        addReportLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addReportLabel.setBounds(55, 100, 380, 30);

        timeLabel = new JLabel();
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setBounds(55, 120, 380, 30);
        updateTimeLabel();

        reportField = new JTextArea();
        reportField.setBorder(new LineBorder(Color.BLACK));
        reportField.setBounds(55, 180, 380, 200);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(250, 400, 120, 30);
        submitButton.addActionListener(e -> {
            String report = reportField.getText();
            System.out.println("Report submitted: " + report);
            FileService.writeReport(report);
            reportField.setText("");
            setVisible(false);
        });

        getContentPane().add(todayReportButton);
        getContentPane().add(oldReportsButton);
        getContentPane().add(settingsButton);
        getContentPane().add(topSeparator);
        getContentPane().add(addReportLabel);
        getContentPane().add(timeLabel);
        getContentPane().add(reportField);
        getContentPane().add(submitButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null); // Center the window
    }

    private void updateTimeLabel() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String currentTime = sdf.format(new Date());
        timeLabel.setText("Current Time: " + currentTime);
    }

    private void showSettingsDialog() {
        JOptionPane.showMessageDialog(this, "Settings Dialog Placeholder", "Settings", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        HomePage homePage = new HomePage();
        homePage.setVisible(true);
    }
}
