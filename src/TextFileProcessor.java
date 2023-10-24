import java.io.*;
import java.util.Scanner;

public class TextFileProcessor extends FileProcessor {
    public static void main(String[] args) {
        TextFileProcessor textFileProcessor = new TextFileProcessor();
        textFileProcessor.process("D:\\exemplu", "file_list.txt");

        // Count the number of lines and words in the text file
        int[] counts = textFileProcessor.countLinesAndWordsInFile("file_list.txt");
        int lineCount = counts[0];
        int wordCount = counts[1];
        System.out.println("Number of lines in the file: " + lineCount);
        System.out.println("Number of words in the file: " + wordCount);
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
                            int[] counts = countLinesAndWordsInFile(file.getPath());
                            int lineCount = counts[0];
                            int wordCount = counts[1];
                            String fileData = "File: " + fileName + ", Extension: " + fileExtension +
                                    ", Creation Date: " + creationDate + ", Line Count: " + lineCount +
                                    ", Word Count: " + wordCount;
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

    private int[] countLinesAndWordsInFile(String filePath) {
        int lineCount = 0;
        int wordCount = 0;
        Scanner scanner = null;

        try {
            scanner = new Scanner(new File(filePath));
            scanner.useDelimiter("\\s+"); // Use whitespace as the delimiter to count words

            while (scanner.hasNextLine()) {
                scanner.nextLine();
                lineCount++;
            }

            scanner.close(); // Close and reopen the scanner to reset position
            scanner = new Scanner(new File(filePath));
            scanner.useDelimiter("\\s+"); // Use whitespace as the delimiter to count words

            while (scanner.hasNext()) {
                scanner.next();
                wordCount++;
            }
        } catch (IOException e) {
            System.err.println("An error occurred while counting lines and words: " + e.getMessage());
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        return new int[]{lineCount, wordCount};
    }
}
