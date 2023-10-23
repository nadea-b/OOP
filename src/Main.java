import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Main Menu");
            System.out.println("1. Commit");
            System.out.println("2. Info");
            System.out.println("3. Status");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.println("You selected 'Commit'");
                    // Create an instance of the Commit class
                    Commit commit = new Commit();
                    // Call the updateSnapshot method to update the snapshot time
                    commit.updateSnapshot();
                    System.out.println("Snapshot time updated.");
                    break;
                case 2:
                    System.out.print("Enter the filename for 'Info': ");
                    String filename = scanner.nextLine();
                    System.out.println("You selected 'Info' for file: " + filename);
                    // Add your info logic here with the provided filename
                    break;
                case 3:
                    System.out.println("You selected 'Status'");
                    // Add your status logic here
                    break;
                case 4:
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option (1-4).");
                    break;
            }
        }
    }
}
