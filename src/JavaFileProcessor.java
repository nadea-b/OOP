import java.io.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaFileProcessor extends FileProcessor {
    public static void main(String[] args) {
        JavaFileProcessor javaFileProcessor = new JavaFileProcessor();
        javaFileProcessor.process("D:\\exemplu", "file_list.txt");

        // Additional processing specific to Java files
        javaFileProcessor.processJavaFiles("D:\\exemplu", "file_list.txt");
    }

    @Override
    public void process(String folderPath, String outputFilePath) {
        super.process(folderPath, outputFilePath);
    }

    protected void processJavaFiles(String folderPath, String outputFilePath) {
        try {
            FileWriter fileWriter = new FileWriter(outputFilePath, true); // Append to the file

            File folder = new File(folderPath);
            if (folder.exists() && folder.isDirectory()) {
                File[] files = folder.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isFile() && isJavaFile(file)) {
                            String fileName = file.getName();
                            String fileExtension = getFileExtension(fileName);
                            String creationDate = getFileCreationDate(file);
                            int lineCount = countLinesInFile(file);
                            int classCount = countClassesInFile(file);
                            int methodCount = countMethodsInFile(file);

                            // Register the Java file information in the "java_file_list.txt" file
                            String fileData = "File: " + fileName + ", Extension: " + fileExtension +
                                    ", Creation Date: " + creationDate + ", Line Count: " + lineCount +
                                    ", Class Count: " + classCount + ", Method Count: " + methodCount;
                            fileWriter.write(fileData + "\n");
                            System.out.println("Processed Java file: " + fileName);
                        }
                    }
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("An error occurred while processing Java files: " + e.getMessage());
        }
    }

    private boolean isJavaFile(File file) {
        // Check if the file extension is ".java"
        String fileExtension = getFileExtension(file.getName());
        return "java".equalsIgnoreCase(fileExtension);
    }

    private int countLinesInFile(File file) {
        int lineCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.readLine() != null) {
                lineCount++;
            }
        } catch (IOException e) {
            System.err.println("An error occurred while counting lines in the file: " + e.getMessage());
        }
        return lineCount;
    }


    private int countClassesInFile(File file) {
        int classCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.matches("^\\s*class\\s+\\w+.*") || line.matches("^\\s*public\\s+class\\s+\\w+.*")) {
                    // Match lines that define a class using regular expression
                    classCount++;
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while counting classes in the file: " + e.getMessage());
        }
        return classCount;
    }

    private int countMethodsInFile(File file) {
        int methodCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.matches("^\\s*(public|private|protected)?\\s+\\w+\\s+\\w+\\(.*\\)\\s*\\{.*")) {
                    // Match lines that define a method using regular expression
                    methodCount++;
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while counting methods in the file: " + e.getMessage());
        }
        return methodCount;
    }
}
