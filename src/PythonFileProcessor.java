import java.io.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PythonFileProcessor extends FileProcessor {
    public static void main(String[] args) {
        PythonFileProcessor pythonFileProcessor = new PythonFileProcessor();
        pythonFileProcessor.process("D:\\exemplu", "file_list.txt");

        // Additional processing specific to Python files
        pythonFileProcessor.processPythonFiles("D:\\exemplu", "file_list.txt");
    }

    @Override
    public void process(String folderPath, String outputFilePath) {
        super.process(folderPath, outputFilePath);
    }

    protected void processPythonFiles(String folderPath, String outputFilePath) {
        try {
            FileWriter fileWriter = new FileWriter(outputFilePath, true); // Append to the file

            File folder = new File(folderPath);
            if (folder.exists() && folder.isDirectory()) {
                File[] files = folder.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isFile() && isPythonFile(file)) {
                            String fileName = file.getName();
                            String fileExtension = getFileExtension(fileName);
                            String creationDate = getFileCreationDate(file);
                            int lineCount = countLinesInFile(file);
                            int classCount = countClassesInFile(file);
                            int methodCount = countMethodsInFile(file);

                            // Register the Python file information in the "python_file_list.txt" file
                            String fileData = "File: " + fileName + ", Extension: " + fileExtension +
                                    ", Creation Date: " + creationDate + ", Line Count: " + lineCount +
                                    ", Class Count: " + classCount + ", Method Count: " + methodCount;
                            fileWriter.write(fileData + "\n");
                            System.out.println("Processed Python file: " + fileName);
                        }
                    }
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("An error occurred while processing Python files: " + e.getMessage());
        }
    }


    private boolean isPythonFile(File file) {
        // Check if the file extension is ".py"
        String fileExtension = getFileExtension(file.getName());
        return "py".equalsIgnoreCase(fileExtension);
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
                if (line.matches("^\\s*class\\s+\\w+(\\s*\\(\\s*\\w+\\s*\\))?\\s*:.*")) {
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
        boolean insideClass = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.matches("^\\s*class\\s+\\w+(\\s*\\(\\s*\\w+\\s*\\))?\\s*:.*")) {
                    // Match lines that define a class using regular expression
                    insideClass = true;
                } else if (insideClass) {
                    if (line.matches("^\\s{4,}def\\s+\\w+\\s*\\(.*\\)\\s*:.*")) {
                        // Match lines that define a method inside a class using regular expression
                        methodCount++;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while counting methods in the file: " + e.getMessage());
        }
        return methodCount;
    }

}
