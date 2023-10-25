import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileInfoSearcher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the name of the file you want to get information about: ");
        String fileNameToSearch = scanner.nextLine();

        searchFileInFileList("file_list.txt", fileNameToSearch);
    }

    public static void searchFileInFileList(String fileListPath, String fileNameToSearch) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileListPath))) {
            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                if (line.contains("File: " + fileNameToSearch + ",")) {
                    found = true;
                    String[] fileInfo = line.split(", ");
                    System.out.println("File Information:");
                    for (String info : fileInfo) {
                        System.out.println(info);
                    }
                }
            }

            if (!found) {
                System.out.println("File not found in the list: " + fileNameToSearch);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file list: " + e.getMessage());
        }
    }
}
