package org.sanapplications.GUI;

import org.sanapplications.Services.FileService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ReportsHistoryPage extends JFrame {

    boolean isTodayReport;
    private JTable table;


    public ReportsHistoryPage(boolean isToday) {

        isTodayReport = isToday;

        setTitle("Reports History");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose the frame only, not the entire application
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window

        initComponents();
    }

    private void initComponents() {
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Define table column names
        String[] columnNames = {"Time", "Report"};

        // Load data from CSV
        String[][] data = FileService.loadDataFromCSV(isTodayReport);

        // Create table model with data and column names
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // Create JTable with the model
        table = new JTable(model);

        // Add table to scroll pane
        scrollPane.setViewportView(table);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ReportsHistoryPage reportsHistory = new ReportsHistoryPage(false);
            reportsHistory.setVisible(true);
        });
    }
}
