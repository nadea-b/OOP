import java.util.EmptyStackException;

public class LinkedStack<E> implements Stack<E> {
    // Inner class for the nodes of the linked list
    private class Node {
        E data;
        Node next;

        Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node top; // Reference to the top element
    private int size; // Number of elements in the stack

    public LinkedStack() {
        top = null;
        size = 0;
    }

    @Override
    public void push(E item) {
        Node newNode = new Node(item);
        newNode.next = top;
        top = newNode;
        size++;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        E poppedItem = top.data;
        top = top.next;
        size--;
        return poppedItem;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.data;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        LinkedStack<Integer> stack = new LinkedStack<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println("Top element: " + stack.peek()); // Should print 3

        while (!stack.isEmpty()) {
            System.out.println("Popped: " + stack.pop());
        }
    }
}
