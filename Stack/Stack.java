import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Stack<T> {
    private StackNode<T> front;
    private int size;

    public Stack() {
        this.front = null;
        this.size = 0;
    }

    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data argument cannot be null.");
        }

        this.front = new StackNode<>(data, this.front);
        this.size++;

    }

    public T pop() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("No elements currently exist in the Stack.");
        }

        T theSlab = front.getData();
        this.front = this.front.getNext();
        this.size--;
        return theSlab;
    }

    public T peek() {
        return (this.front != null) ? this.front.getData() : null;
    }

    public int size() {
        return this.size;
    }

    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data argument cannot be null.");
        }
        StackNode<T> current = this.front;
        while (current != null) {
            if (current.equals(data)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }
    public boolean isEmpty() {
        return (this.front == null);
    }

    private class StackNode<T> {
        private T data;
        private StackNode<T> next;

        public StackNode() {

        }

        public StackNode(T data) {
            if (data == null) {
                throw new IllegalArgumentException("Data argument cannot be null.");
            }
            this.data = data;
        }

        public StackNode(T data, StackNode<T> next) {
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return this.data;
        }

        public StackNode<T> getNext() {
            return this.next;
        }

        public void setNext(StackNode<T> next) {
            this.next = next;
        }

    }

    @Override
    public String toString() {

        StackNode<T> current = this.front;
        ArrayList<T> list = new ArrayList<>();

        while (current != null) {
            list.add(current.getData());
            current = current.getNext();
        }

        return list.toString();
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack();
        stack.push(1);
        stack.push(2);
       // stack.pop();

        System.out.println(stack.pop());
    }
}