import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.Queue;

/**
 * Queue that uses two stacks as the backing structures for functionality.
 * Implemented as a challenge question from Reddit.
 */

public class DoubleStackBackedQueue<T> {
    private Stack<T> one;
    private Stack<T> two;
    private int size;

    public DoubleStackBackedQueue() {
        this.one = new Stack<>();
        this.two = new Stack<>();
        this.size = 0;
    }

    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data argument cannot be null.");
        }

        while (!this.one.isEmpty()) {
            this.two.push(this.one.pop());
        }

        this.one.push(data);
        while (!this.two.isEmpty()) {
            this.one.push(this.two.pop());
        }
        this.size++;
    }

    public T dequeue() {
        if (this.isEmpty()) {
            throw new IllegalArgumentException("Queue is empty.");
        }
        this.size--;
        return one.pop();
    }

    public T peek() {
        return one.peek();
    }

    public int size() {
        return this.size;
    }
    public String toString() {
        return one.toString();
    }

    public boolean isEmpty() {
        return (this.one.isEmpty());
    }


    public static void main(String[] args) {
        DoubleStackBackedQueue<Integer> queue = new DoubleStackBackedQueue<>();

        queue.enqueue(1);
        queue.enqueue(2);
        queue.dequeue();

        System.out.println(queue);


    }
}
