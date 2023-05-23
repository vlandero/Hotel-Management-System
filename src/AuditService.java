import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuditService {
    private static final String CSV_FILE_PATH = "src/audit.csv";
    private static final String CSV_SEPARATOR = ",";
    private static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void logAction(String actionName) {
        String timestamp = TIMESTAMP_FORMAT.format(new Date());
        String csvLine = actionName + CSV_SEPARATOR + timestamp;

        try (PrintWriter pw = new PrintWriter(new FileWriter(CSV_FILE_PATH, true))) {
            pw.println(csvLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
