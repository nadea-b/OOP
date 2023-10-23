import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.EnumSet;

class ListFilesInFolder {
    public static void main(String[] args) {
        // Specify the folder path
        String folderPath = "D:\\Semestrul 3\\OOP";

        // File object for the folder
        File folder = new File(folderPath);

        // Check if the folder exists
        if (folder.exists() && folder.isDirectory()) {
            // List all files and subdirectories
            File[] files = folder.listFiles();

            if (files != null) {
                try {
                    // Create a FileWriter and PrintWriter to write to a text file
                    FileWriter fileWriter = new FileWriter("file_list.txt");
                    PrintWriter printWriter = new PrintWriter(fileWriter);

                    for (File file : files) {
                        if (file.isFile()) {
                            String fileName = file.getName();
                            String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1);

                            // Get file creation time
                            Path filePath = file.toPath();
                            BasicFileAttributes fileAttributes = Files.readAttributes(filePath, BasicFileAttributes.class);
                            FileTime creationTime = fileAttributes.creationTime();

                            printWriter.println("File: " + fileName + ", Extension: " + fileExtension + ", Created: " + creationTime);
                            System.out.println("File: " + fileName);
                        } else if (file.isDirectory()) {
                            System.out.println("Directory: " + file.getName());
                        }
                    }

                    // Close the PrintWriter and FileWriter
                    printWriter.close();
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println("An error occurred while writing to the text file.");
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("The specified folder does not exist or is not a directory.");
        }
    }
}
