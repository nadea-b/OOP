import java.util.NoSuchElementException;
import java.util.Scanner;

public class CircularLinkedListQueue<E> implements Queue<E> {

    private Node<E> rear; // Rear of the queue
    private int size;

    private static class Node<E> {
        E data;
        Node<E> next;

        public Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    public CircularLinkedListQueue() {
        rear = null;
        size = 0;
    }

    @Override
    public void enqueue(E item) {
        Node<E> newNode = new Node<>(item);
        if (isEmpty()) {
            newNode.next = newNode;
        } else {
            newNode.next = rear.next;
            rear.next = newNode;
        }
        rear = newNode;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        E item = rear.next.data;
        if (rear.next == rear) {
            rear = null;
        } else {
            rear.next = rear.next.next;
        }
        size--;
        return item;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return rear.next.data;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    public static void main() {
        CircularLinkedListQueue<Integer> queue = new CircularLinkedListQueue<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Peek");
            System.out.println("4. Check Size");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter value to enqueue: ");
                    int valueToEnqueue = scanner.nextInt();
                    queue.enqueue(valueToEnqueue);
                    System.out.println(valueToEnqueue + " enqueued.");
                    break;
                case 2:
                    try {
                        int dequeuedValue = queue.dequeue();
                        System.out.println(dequeuedValue + " dequeued.");
                    } catch (NoSuchElementException e) {
                        System.out.println("Queue is empty. Cannot dequeue.");
                    }
                    break;
                case 3:
                    try {
                        int peekedValue = queue.peek();
                        System.out.println("Peeked value: " + peekedValue);
                    } catch (NoSuchElementException e) {
                        System.out.println("Queue is empty. Cannot peek.");
                    }
                    break;
                case 4:
                    System.out.println("Queue size: " + queue.size());
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }
}
