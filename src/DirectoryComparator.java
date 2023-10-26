import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DirectoryComparator {

    public static void main(String[] args) {
        long snapshotTimestamp = loadSnapshotTimestamp("snapshot.txt");

        if (snapshotTimestamp == -1) {
            System.err.println("Snapshot file not found or contains an invalid timestamp.");
            return;
        }

        compareFilesWithSnapshot(snapshotTimestamp, "file_list.txt", "D:\\exemplu");
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

    private static void compareFilesWithSnapshot(long snapshotTimestamp, String fileListPath, String directoryPath) {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileListPath)) ) {
            String line;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Pattern pattern = Pattern.compile("File: (.+?),");
            boolean isAnyFileModified = false;

            // Create a set to keep track of files in file_list.txt
            Set<String> fileSet = new HashSet<>();

            while ((line = fileReader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String fileName = matcher.group(1);

                    fileSet.add(fileName);

                    File directory = new File(directoryPath);
                    File[] files = directory.listFiles();

                    if (files != null) {
                        boolean fileFound = false;

                        for (File file : files) {
                            if (file.getName().equals(fileName)) {
                                fileFound = true;

                                if (file.lastModified() > snapshotTimestamp) {
                                    System.out.println(fileName + " - Changed");
                                } else {
                                    System.out.println(fileName + " - Not Changed");
                                }
                                break;
                            }
                        }

                        if (!fileFound) {
                            System.out.println(fileName + " - Deleted");
                        }
                    }
                }
            }

            for (File file : new File(directoryPath).listFiles()) {
                if (!fileSet.contains(file.getName())) {
                    System.out.println(file.getName() + " - New file");
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File list not found: " + fileListPath);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
