import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class Queue<T> {
    private QueueNode<T> front;
    private int size;

    public Queue() {
        this.front = null;
        this.size = 0;
    }

    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data argument cannot be null.");
        }

        if (this.isEmpty()) {
            this.front = new QueueNode<>(data);
        } else {
            QueueNode<T> current = this.front;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(new QueueNode<>(data));
        }

        this.size++;
    }

    public T dequeue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data argument cannot be null.");
        }
        if (this.isEmpty()) {
            throw new NoSuchElementException("No elements exist in Queue");
        }

        T theSlab = this.front.getData();
        this.front = this.front.getNext();
        this.size--;
        return theSlab;

    }

    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data argument cannot be null.");
        }

        QueueNode<T> current = this.front;

        while (current != null) {
            if (current.getData().equals(data)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public boolean isEmpty() {
        return (this.front == null);
    }
    private class QueueNode<T> {
        private T data;
        private QueueNode<T> next;
        public QueueNode() {

        }
        public QueueNode(T data) {
            if (data == null) {
                throw new IllegalArgumentException("Data argument cannot be null.");
            }
            this.data = data;
        }
        public QueueNode(T data, QueueNode<T> next) {
            this.data = data;
            this.next = next;
        }
        public T getData() {
            return this.data;
        }
        public QueueNode<T> getNext() {
            return this.next;
        }
        public void setNext(QueueNode<T> next) {
            this.next = next;
        }
    }

    public int size() {
        return this.size;
    }

    @Override
    public String toString() {

        QueueNode<T> current = this.front;
        ArrayList<T> list = new ArrayList<>();

        while (current != null) {
            list.add(current.getData());
            current = current.getNext();
        }

        return list.toString();
    }
    public static void main(String[] args) {
        Queue<Integer> q = new Queue();

        q.enqueue(1);
        q.enqueue(2);
        q.dequeue(1);
        System.out.println(q);


        PriorityQueue<Integer> q2 = new PriorityQueue<>();
        q2.add(1);
        q2.add(-1);
        q2.remove();


        System.out.println(q2);
    }
}
