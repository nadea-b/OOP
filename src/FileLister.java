import java.io.File;

public class FileLister {
    public static void main(String[] args) {
        // Specify the folder path
        String folderPath = "D:\\exemplu";

        // Clear the contents of file_list.txt by deleting it (optional).
        File fileListFile = new File("file_list.txt");
        if (fileListFile.exists()) {
            fileListFile.delete();
        }

        File folder = new File(folderPath);
        iterateAndProcessFiles(folderPath, folder);
    }

    public static void iterateAndProcessFiles(String folderPath, File folder) {
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        String fileName = file.getName();
                        String fileExtension = getFileExtension(fileName);

                        // Manually call the processing class based on the file extension
                        if ("txt".equalsIgnoreCase(fileExtension)) {
                            TextFileProcessor textFileProcessor = new TextFileProcessor();
                            textFileProcessor.processTextFiles(folderPath, "file_list.txt");
                        } else if ("jpg".equalsIgnoreCase(fileExtension) || "png".equalsIgnoreCase(fileExtension)) {
                            ImageFileProcessor imageFileProcessor = new ImageFileProcessor();
                            imageFileProcessor.processImageFiles(folderPath, "file_list.txt");
                        } else if ("py".equalsIgnoreCase(fileExtension)){
                            PythonFileProcessor pythonFileProcessor = new PythonFileProcessor();
                            pythonFileProcessor.processPythonFiles(folderPath, "file_list.txt");
                        } else if ("java".equalsIgnoreCase(fileExtension)){
                            JavaFileProcessor javaFileProcessor = new JavaFileProcessor();
                            javaFileProcessor.processJavaFiles(folderPath, "file_list.txt");
                        } else System.out.println("No processing class found for file: " + fileName);
                    } else if (file.isDirectory()) {
                        iterateAndProcessFiles(folderPath, file);
                    }
                }
            }
        }
    }

    public static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1);
        }
        return "";
    }
}
