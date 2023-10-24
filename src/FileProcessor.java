import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileProcessor {
    public void process(String folderPath, String outputFilePath) {
        try {
            FileWriter fileWriter = new FileWriter(outputFilePath);

            File folder = new File(folderPath);
            if (folder.exists() && folder.isDirectory()) {
                File[] files = folder.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isFile()) {
                            String fileName = file.getName();
                            String fileExtension = getFileExtension(fileName);
                            String creationDate = getFileCreationDate(file);

                            // Create a string with the file information
                            String fileData = "File: " + fileName + ", Extension: " + fileExtension + ", Creation Date: " + creationDate;

                            // Write the file information to the output file
                            fileWriter.write(fileData + "\n");
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
        try {
            long creationTime = file.lastModified();
            return String.valueOf(creationTime);
        } catch (Exception e) {
            System.err.println("Error getting creation date: " + e.getMessage());
            return "N/A";
        }
    }
}
