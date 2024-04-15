package org.sanapplications.Services;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileService {

    private static final String CSV_FILE_PATH = "data/reports.csv";

    public static void writeReport(String report) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(new Date());

        try (FileWriter writer = new FileWriter(CSV_FILE_PATH, true)) {
            // Append the data to the CSV file
            writer.append(currentTime);
            writer.append(",");
            writer.append(report);
            writer.append("\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getDataFromCSV() {
        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.toString();
    }

    public static String[][] loadDataFromCSV(boolean isTodayReport) {
        List<String[]> rows = new ArrayList<>();

        Calendar today = Calendar.getInstance();

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                // Check if the report's date matches today's date
                if (!isTodayReport || isReportToday(data[0])) {
                    rows.add(data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rows.toArray(new String[0][]);
    }

    private static boolean isReportToday(String dateString) {
        Calendar today = Calendar.getInstance();
        Calendar reportDate = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            reportDate.setTime(sdf.parse(dateString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return reportDate.get(Calendar.YEAR) == today.get(Calendar.YEAR) &&
                reportDate.get(Calendar.MONTH) == today.get(Calendar.MONTH) &&
                reportDate.get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH);
    }

}
