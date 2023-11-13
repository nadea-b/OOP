import java.util.NoSuchElementException;

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
            newNode.next = newNode; // Point to itself
        } else {
            newNode.next = rear.next; // New node points to the current front
            rear.next = newNode; // Rear's next points to the new node
        }
        rear = newNode; // Update the rear reference
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        E item = rear.next.data;
        if (rear.next == rear) {
            rear = null; // If the queue has only one element
        } else {
            rear.next = rear.next.next; // Remove the front element
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
}
