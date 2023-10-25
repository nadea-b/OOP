import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileChangeChecker {

    public static void main(String[] args) {
        FileLister.main(new String[0]);
        long snapshotTimestamp = loadSnapshotTimestamp("snapshot.txt");

        if (snapshotTimestamp == -1) {
            System.err.println("Snapshot file not found or contains an invalid timestamp.");
            return;
        }

        checkFileChanges(snapshotTimestamp, "file_list.txt");
    }

    private static long loadSnapshotTimestamp(String snapshotFile) {
        try {
            BufferedReader snapshotReader = new BufferedReader(new FileReader(snapshotFile));
            String snapshotTimestampStr = snapshotReader.readLine();
            snapshotReader.close();

            return Long.parseLong(snapshotTimestampStr);
        } catch (FileNotFoundException e) {
            return -1;  // Snapshot file not found.
        } catch (IOException | NumberFormatException e) {
            return -1;  // Invalid snapshot timestamp.
        }
    }

    private static void checkFileChanges(long snapshotTimestamp, String fileListPath) {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileListPath)) ) {
            String line;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Pattern pattern = Pattern.compile("Modification Date: (\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2})");
            boolean isAnyFileModified = false;

            while ((line = fileReader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String modificationDateStr = matcher.group(1);
                    Date modificationDate = dateFormat.parse(modificationDateStr);
                    long modificationTimestamp = modificationDate.getTime();

                    if (modificationTimestamp > snapshotTimestamp) {
                        System.out.println(line.split(",")[0].trim() + " - Changed");
                        isAnyFileModified = true;
                    } else {
                        System.out.println(line.split(",")[0].trim() + " - Not Changed");
                    }
                }
            }

            if (!isAnyFileModified) {
                System.out.println("No files have been modified since the snapshot.");
            }

        } catch (FileNotFoundException e) {
            System.err.println("File list not found: " + fileListPath);
        } catch (IOException | ParseException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
