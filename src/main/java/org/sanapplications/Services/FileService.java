package org.sanapplications.Services;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileService {

    private static final String CSV_FILE_PATH = "reports.csv";

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
}
