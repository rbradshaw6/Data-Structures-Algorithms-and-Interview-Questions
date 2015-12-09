import java.util.*;

/**
 * Created by robert on 11/29/15.
 */
public class SinglyLinkedList<T> {
    private SLLNode<T> head;
    private int size;

    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data argument cannot be null.");
        }

        if (this.isEmpty()) {
            this.head = new SLLNode<>(data);
        } else {
            SLLNode<T> current = this.head;
            while (current.getNext() != null) {
                current = current.getNext();
            }

            SLLNode<T> newNode = new SLLNode<>(data);
            current.setNext(newNode);
        }
        this.size++;
    }

    public void add(T... data) {
        if (data == null) {
            throw new IllegalArgumentException("Data argument cannot be null.");
        }

        for (T element : data) {
            add(element);
        }
    }

    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data argument cannot be null");
        }

        if (this.isEmpty()) {
            throw new IllegalArgumentException("No elements exist in SLL.");
        }

        T theSlab = null;

        if (this.head.getData().equals(data)) {
            theSlab = this.head.getData();
            this.head = this.head.getNext();
            this.size--;
            return theSlab;
        } else {
            SLLNode<T> current = this.head;
            while (current.getNext() != null) {
                if (current.getNext().getData().equals(data)) {
                    theSlab = current.getNext().getData();
                    current.setNext(current.getNext().getNext());
                    this.size--;
                    return theSlab;
                }
                current = current.getNext();
            }
        }

        throw new NoSuchElementException("Element does not exist in SLL.");
    }

    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data argument cannot be null.");
        }

        if (this.isEmpty()) {
            throw new NoSuchElementException("No elements exist in SLL.");
        }

        SLLNode<T> current = this.head;
        while (current.getNext() != null) {
            if (current.getData().equals(data)) {
                return current.getData();
            }
            current = current.getNext();
        }

        throw new NoSuchElementException("No element exists in SLL.");
    }

    public boolean isEmpty() {
        return (this.head == null);
    }

    public String toString() {
        ArrayList<T> list = new ArrayList<>();
        SLLNode<T> current = this.head;

        while (current != null) {
            list.add(current.getData());
            current = current.getNext();
        }

        return list.toString();
    }

    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data argument cannot be null.");
        }

        SLLNode<T> current = this.head;
        while (current.getNext() != null) {
            if (current.getData().equals(data)) {
                return true;
            }

            current = current.getNext();
        }

        return false;
    }

    public boolean isPalindrome() {
        if (this.isEmpty() || this.size < 2) {
            return false;
        }

        Stack<SLLNode<T>> stack = new Stack();
        SLLNode<T> current = this.head;
        while (current != null) {
            stack.push(current);
            current = current.getNext();
        }

        current = this.head;
        while (current != null) {
            T stackComp = stack.pop().getData();
            if (!current.getData().equals(stackComp)) {
                return false;
            }
            current = current.getNext();
        }
        return true;
    }

    public int size() {
        return this.size;
    }

    public void reverseList() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("SLL is empty.");
        }

        Stack<SLLNode<T>> stack = new Stack<>();
        SLLNode<T> current = this.head;

        while (current != null) {
            stack.push(current);
            current = current.getNext();
        }

        this.head = stack.pop();
        current = this.head; //reset current to old tail

        while (!stack.isEmpty() && current != null) {
            current.setNext(stack.pop());
            current = current.getNext();
        }

        current.setNext(null);
    }



    public SLLNode<T> findMedian() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("No elements in DLL");
        }

        if (this.size() == 1) {
            return this.head;
        } else if (this.size() == 2) {
            return this.head.getNext();
        }

        SLLNode<T> current = this.head;
        SLLNode<T> fastPointer = this.head;

        while (current.getNext() != null) {

            if (fastPointer == null || fastPointer.getNext() == null) { //rounds up if even number of elements
                return current;
            }
            current = current.getNext();
            fastPointer = fastPointer.getNext().getNext();
        }

        return null;
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> curr = new SinglyLinkedList<>();

        curr.add(1,2,3,4,5);
        curr.reverseList();
        System.out.println(curr);
        System.out.println(curr.findMedian());
    }


}
