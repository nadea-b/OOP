import java.io.*;
import java.util.Scanner;

public class TextFileProcessor extends FileProcessor {
    public static void main(String[] args) {
        TextFileProcessor textFileProcessor = new TextFileProcessor();
        textFileProcessor.process("D:\\exemplu", "file_list.txt");

        // Count the number of lines and words in the text file
        int[] counts = textFileProcessor.countLinesAndWordsAndCharactersInFile("file_list.txt");
        int lineCount = counts[0];
        int wordCount = counts[1];
    }

    @Override
    public void process(String folderPath, String outputFilePath) {
        super.process(folderPath, outputFilePath);

        // Additional processing specific to text files
        processTextFiles(folderPath, outputFilePath);
    }

    protected void processTextFiles(String folderPath, String outputFilePath) {
        try {
            FileWriter fileWriter = new FileWriter(outputFilePath, true);

            File folder = new File(folderPath);
            if (folder.exists() && folder.isDirectory()) {
                File[] files = folder.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isFile() && file.getName().toLowerCase().endsWith(".txt")) {
                            String fileName = file.getName();
                            String fileExtension = getFileExtension(fileName);
                            String creationDate = getFileCreationDate(file);
                            int[] counts = countLinesAndWordsAndCharactersInFile(file.getPath());
                            int lineCount = counts[0];
                            int wordCount = counts[1];
                            int charCount = counts[2];
                            String fileData = "File: " + fileName + ", Extension: " + fileExtension +
                                    ", Creation Date: " + creationDate + ", Line Count: " + lineCount +
                                    ", Word Count: " + wordCount + ", Character Count: " + charCount;
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


    private int countCharactersInFile(String filePath) {
        int charCount = 0;
        try (Reader reader = new FileReader(filePath)) {
            int data;
            while ((data = reader.read()) != -1) {
                charCount++;
            }
        } catch (IOException e) {
            System.err.println("An error occurred while counting characters: " + e.getMessage());
        }
        return charCount;
    }


    private int[] countLinesAndWordsAndCharactersInFile(String filePath) {
        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;
        Scanner scanner = null;

        try {
            scanner = new Scanner(new File(filePath));
            scanner.useDelimiter("\\s+"); // Use whitespace as the delimiter to count words

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineCount++;
                wordCount += line.split("\\s+").length; // Split the line into words and count
                charCount += line.length(); // Count characters in the line
            }
        } catch (IOException e) {
            System.err.println("An error occurred while counting lines, words, and characters: " + e.getMessage());
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        return new int[]{lineCount, wordCount, charCount};
    }
}
