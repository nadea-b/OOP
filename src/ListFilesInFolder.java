import java.io.*;
import java.util.Arrays;

class ListFilesInFolder {
    public static void main(String[] args) {
        // Specify the folder path
        String folderPath = "D:\\exemplu";

        // Specify the output file path
        String outputFilePath = "file_list.txt";

        // Create a FileWriter for the output file
        try {
            FileWriter fileWriter = new FileWriter(outputFilePath);
            File folder = new File(folderPath);
            iterateAndRegisterFiles(folder, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while creating the output file.");
            e.printStackTrace();
        }
    }

    public static void iterateAndRegisterFiles(File folder, FileWriter fileWriter) {
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        String fileName = file.getName();
                        FileLister.registerFile(fileName, fileWriter);
                    } else if (file.isDirectory()) {
                        iterateAndRegisterFiles(file, fileWriter);
                    }
                }
            }
        }
    }
}