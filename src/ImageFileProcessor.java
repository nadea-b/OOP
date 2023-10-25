import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImageFileProcessor extends FileProcessor {
    public static void main(String[] args) {
        ImageFileProcessor imageFileProcessor = new ImageFileProcessor();
        imageFileProcessor.process("D:\\exemplu", "file_list.txt");

        // Additional processing specific to image files
        imageFileProcessor.processImageFiles("D:\\exemplu", "file_list.txt");
    }

    @Override
    public void process(String folderPath, String outputFilePath) {
        super.process(folderPath, outputFilePath);

    }

    protected void processImageFiles(String folderPath, String outputFilePath) {
        try {
            FileWriter fileWriter = new FileWriter(outputFilePath, true); // Append to the file

            File folder = new File(folderPath);
            if (folder.exists() && folder.isDirectory()) {
                File[] files = folder.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isFile() && isImageFile(file)) {
                            String fileName = file.getName();
                            String fileExtension = getFileExtension(fileName);
                            String creationDate = getFileCreationDate(file);

                            // Get image size
                            int[] imageSize = getImageSize(file);

                            // Register the image information in the "file_list.txt" file, including size
                            String fileData = "File: " + fileName + ", Extension: " + fileExtension +
                                    ", Creation Date: " + creationDate + ", Width: " + imageSize[0] + ", Height: " + imageSize[1];
                            fileWriter.write(fileData + "\n");
                            System.out.println("Processed image file: " + fileName);
                        }
                    }
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("An error occurred while processing image files: " + e.getMessage());
        }
    }

    private int[] getImageSize(File imageFile) {
        int[] imageSize = new int[] { 0, 0 };
        try {
            BufferedImage image = ImageIO.read(imageFile);
            if (image != null) {
                imageSize[0] = image.getWidth();
                imageSize[1] = image.getHeight();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while getting image size: " + e.getMessage());
        }
        return imageSize;
    }

    private boolean isImageFile(File file) {
        // List of common image file extensions
        String[] imageExtensions = { "jpg", "jpeg", "png", "gif" };
        String fileExtension = getFileExtension(file.getName());

        // Check if the file extension is in the list of image extensions
        for (String ext : imageExtensions) {
            if (ext.equalsIgnoreCase(fileExtension)) {
                return true;
            }
        }
        return false;
    }
}
