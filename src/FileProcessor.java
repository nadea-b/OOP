import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileProcessor {
    public void process(String folderPath, String outputFilePath) {
        try {
            FileWriter fileWriter = new FileWriter(outputFilePath, true);

            File folder = new File(folderPath);
            if (folder.exists() && folder.isDirectory()) {
                File[] files = folder.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isFile()) {
                            String fileName = file.getName();
                            String fileExtension = getFileExtension(fileName);
                            String creationDate = getFileCreationDate(file);
                            String lastModifiedTime = getFileLastModifiedTime(file);


                            // Create a string with the file information
                          //  String fileData = "File: " + fileName + ", Extension: " + fileExtension + ", Creation Date: " + creationDate;

                            // Write the file information to the output file
                           // fileWriter.write(fileData + "\n");
                        }
                    }
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("An error occurred while processing files: " + e.getMessage());
        }
    }

    protected String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1);
        }
        return "";
    }

    protected String getFileCreationDate(File file) {
        Path filePath = file.toPath();
        try {
            BasicFileAttributes attrs = Files.readAttributes(filePath, BasicFileAttributes.class);
            FileTime creationTime = attrs.creationTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateFormat.format(creationTime.toMillis());
        } catch (Exception e) {
            System.err.println("Error getting creation date: " + e.getMessage());
            return "N/A";
        }
    }

    protected String getFileLastModifiedTime(File file) {
        Path filePath = file.toPath();
        try {
            BasicFileAttributes attrs = Files.readAttributes(filePath, BasicFileAttributes.class);
            FileTime lastModifiedTime = attrs.lastModifiedTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateFormat.format(lastModifiedTime.toMillis());
        } catch (Exception e) {
            System.err.println("Error getting last modified time: " + e.getMessage());
            return "N/A";
        }
    }

}
