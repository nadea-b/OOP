import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        executorService.scheduleAtFixedRate(() -> {
           // System.out.println("Running 'Status' command in the background...");
            DirectoryComparatorBackground.main(new String[0]);
            FileLister.main(new String[0]);
        }, 0, 5, TimeUnit.SECONDS);

        while (true) {
            System.out.println("\nMain Menu\nChoose one option:");
            System.out.println("1. Commit");
            System.out.println("2. Info");
            System.out.println("3. Status");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Newline character

            switch (choice) {
                case 1:
                    System.out.println("You selected 'Take Snapshot'");
                    SnapshotRecorder snapshotRecorder = new SnapshotRecorder();
                    snapshotRecorder.takeSnapshot();
                    System.out.println("Snapshot taken at: " + snapshotRecorder.getSnapshotTime());
                    snapshotRecorder.saveStateToTextFile("snapshot.txt"); // Save the state to a text file
                    break;
                case 2:
                    // Call the main method of FileInfoSearcher to search for the specified file in the file list
                    FileInfoSearcher.main(new String[0]);
                    break;
                case 3:
                    System.out.println("You selected 'Status'");
                    DirectoryComparator.main(new String[0]);
                    FileLister.main(new String[0]);
                    break;
                case 4:
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    executorService.shutdown();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option (1-4).");
                    break;
            }
        }
    }
}
