import java.util.EmptyStackException;
import java.util.Scanner;

public class ArrayQueue<E> implements Queue<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private E[] array;
    private int front;
    private int rear;
    private int size;

    public ArrayQueue() {
        array = (E[]) new Object[DEFAULT_CAPACITY];
        front = 0;
        rear = -1;
        size = 0;
    }

    @Override
    public void enqueue(E item) {
        if (size == array.length) {
            resizeArray();
        }
        rear = (rear + 1) % array.length;
        array[rear] = item;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        E item = array[front];
        front = (front + 1) % array.length;
        size--;
        return item;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return array[front];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void resizeArray() {
        int newCapacity = array.length * 2;
        E[] newArray = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[(front + i) % array.length];
        }
        array = newArray;
        front = 0;
        rear = size - 1;
    }

    public static void main() {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
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
                    if (!queue.isEmpty()) {
                        int dequeuedValue = queue.dequeue();
                        System.out.println(dequeuedValue + " dequeued.");
                    } else {
                        System.out.println("Queue is empty. Cannot dequeue.");
                    }
                    break;
                case 3:
                    if (!queue.isEmpty()) {
                        int peekedValue = queue.peek();
                        System.out.println("Peeked value: " + peekedValue);
                    } else {
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
