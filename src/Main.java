import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice;

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Queue");
            System.out.println("2. Stack");
            System.out.println("3. Exit");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    queueMenu(scanner);
                    break;
                case "2":
                    stackMenu(scanner);
                    break;
                case "3":
                    System.out.println("Exiting the program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    public static void queueMenu(Scanner scanner) {
        String choice;
        while (true) {
            System.out.println("Select a Queue implementation:");
            System.out.println("1. ArrayQueue");
            System.out.println("2. CircularLinkedListQueue");
            System.out.println("3. LinkedQueue");
            System.out.println("4. Back to the main menu");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("You selected ArrayQueue.");
                    ArrayQueue.main();
                    break;
                case "2":
                    System.out.println("You selected CircularLinkedListQueue.");
                    CircularLinkedListQueue.main();
                    break;
                case "3":
                    System.out.println("You selected LinkedQueue.");
                    LinkedQueue.main();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    public static void stackMenu(Scanner scanner) {
        String choice;
        while (true) {
            System.out.println("Select a Stack implementation:");
            System.out.println("1. ArrayListStack");
            System.out.println("2. ArrayStack");
            System.out.println("3. LinkedStack");
            System.out.println("4. Back to the main menu");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("You selected ArrayListStack.");
                    ArrayListStack.main();
                    break;
                case "2":
                    System.out.println("You selected ArrayStack.");
                    ArrayStack.main();
                    break;
                case "3":
                    System.out.println("You selected LinkedStack.");
                    LinkedStack.main();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}
