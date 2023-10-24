import java.io.FileWriter;
import java.io.IOException;

public class FileLister {
    public static void registerFile(String fileName, FileWriter fileWriter) {
        try {
            String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1);

            // Write the full information to the text file
            String fileData = "File: " + fileName + ", Extension: " + fileExtension;
            fileWriter.write(fileData + "\n");

            // Display only the file name in the console
            System.out.println(fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the text file.");
            e.printStackTrace();
        }
    }
}
