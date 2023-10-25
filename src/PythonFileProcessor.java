import java.io.*;
import java.util.Date;

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

    private void processPythonFiles(String folderPath, String outputFilePath) {
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

                            // Register the Python file information in the "python_file_list.txt" file
                            String fileData = "File: " + fileName + ", Extension: " + fileExtension +
                                    ", Creation Date: " + creationDate;
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
}
