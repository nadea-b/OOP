import java.io.*;
import java.util.Scanner;

public class TextFileProcessor extends FileProcessor {
    public static void main(String[] args) {
        TextFileProcessor textFileProcessor = new TextFileProcessor();
        textFileProcessor.process("D:\\exemplu", "file_list.txt");

        // Count the number of lines in the text file
        int lineCount = textFileProcessor.countLinesInFile("file_list.txt");
        System.out.println("Number of lines in the file: " + lineCount);
    }

    @Override
    public void process(String folderPath, String outputFilePath) {
        super.process(folderPath, outputFilePath);

        // Additional processing specific to text files
        processTextFiles(folderPath, outputFilePath);
    }

    private void processTextFiles(String folderPath, String outputFilePath) {
        try {
            FileWriter fileWriter = new FileWriter(outputFilePath);

            File folder = new File(folderPath);
            if (folder.exists() && folder.isDirectory()) {
                File[] files = folder.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isFile() && file.getName().toLowerCase().endsWith(".txt")) {
                            String fileName = file.getName();
                            String fileExtension = getFileExtension(fileName);
                            String creationDate = getFileCreationDate(file);
                            int lineCount = countLinesInFile(file.getPath());
                            String fileData = "File: " + fileName + ", Extension: " + fileExtension +
                                    ", Creation Date: " + creationDate + ", Line Count: " + lineCount;
                            fileWriter.write(fileData + "\n");
                            System.out.println("Processed TXT file: " + fileName);
                        }
                    }
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("An error occurred while processing text files: " + e.getMessage());
        }
    }

    private static int countLinesInFile(String filePath) {
        int lineCount = 0;
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                lineCount++;
            }
        } catch (IOException e) {
            System.err.println("An error occurred while counting lines: " + e.getMessage());
        }
        return lineCount;
    }
}
