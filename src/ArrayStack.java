import java.util.EmptyStackException;
import java.util.Scanner;

public class ArrayStack<E> implements Stack<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private E[] array;
    private int size;

    public ArrayStack() {
        array = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void push(E item) {
        if (size == array.length) {
            resizeArray();
        }
        array[size++] = item;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        E item = array[--size];
        array[size] = null;
        return item;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return array[size - 1];
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
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    public static void main() {
        ArrayStack<Integer> stack = new ArrayStack<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek");
            System.out.println("4. Check Size");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter value to push: ");
                    int valueToPush = scanner.nextInt();
                    stack.push(valueToPush);
                    System.out.println(valueToPush + " pushed onto the stack.");
                    break;
                case 2:
                    try {
                        int poppedValue = stack.pop();
                        System.out.println(poppedValue + " popped from the stack.");
                    } catch (EmptyStackException e) {
                        System.out.println("Stack is empty. Cannot pop.");
                    }
                    break;
                case 3:
                    try {
                        int peekedValue = stack.peek();
                        System.out.println("Peeked value: " + peekedValue);
                    } catch (EmptyStackException e) {
                        System.out.println("Stack is empty. Cannot peek.");
                    }
                    break;
                case 4:
                    System.out.println("Stack size: " + stack.size());
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
