import java.io.FileWriter;
import java.io.IOException;

public class FileLister {
    public static void registerFile(String fileName, FileWriter fileWriter) {
        try {
            String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1);
            fileWriter.write("File: " + fileName + ", Extension: " + fileExtension + "\n");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the text file.");
            e.printStackTrace();
        }
    }
}
