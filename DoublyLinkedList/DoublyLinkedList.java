import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> { //not circular
    public DLLNode<T> head;
    public DLLNode<T> tail;
    public int size;

    public DoublyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public DoublyLinkedList(List<T> collection) {
        for (T element : collection) {
            if (element == null) {
                throw new IllegalArgumentException("We don't like no nulls 'round here partner");
            }
            add(element);
        }
    }

    // O(n)
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data argument cannot be null");
        }

        if (this.isEmpty()) {
            this.head = new DLLNode<>(data);
        } else if (this.head.getNext() == null) {
            DLLNode<T> newNode = new DLLNode<>(data, null, this.head);
            this.head.setNext(newNode);
        } else {
            DLLNode<T> current = this.head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            DLLNode<T> newNode = new DLLNode<>(data, null, current);
            current.setNext(newNode);
        }
        this.size++;
    }


    // O(n)
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data argument cannot be null");
        }

        T theSlab;
        if (this.head.getData().equals(data)) {
            if (this.head.getNext() == null) {
                theSlab = this.head.getData();
                this.head = null;
            } else {
                theSlab = this.head.getNext().getData();
                this.head = this.head.getNext();
            }
            this.size--;
            return theSlab;
        }  else {
            DLLNode<T> current = this.head;
            while (current != null) {
                if (current.getData().equals(data)) {
                    theSlab = current.getData();

                    DLLNode<T> tempPrev = current.getPrev();
                    DLLNode<T> newNext = (current.getNext() != null) ? current.getNext() : null;
                    tempPrev.setNext(newNext);
                    current.setNext(null);

                    if (newNext != null) {
                        newNext.setPrev(tempPrev);
                    }
                    this.size--;
                    return theSlab;
                }
                current = current.getNext();
            }
        }

        return null;
    }

    // O(1)
    public boolean isEmpty() {
        return (this.head == null);
    }


    // O(1)
    public void clear() {
        this.head = null;
        this.size = 0;
    }

    //O(n)
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data argument cannot be null.");
        }

        DLLNode<T> current = this.head;
        while (current.getNext() != null) {
            if (current.getData().equals(data)) {
                return true;
            }
        }

        return false;
    }

    public String toString() {
        ArrayList<String> list = new ArrayList<>();
        DLLNode<T> current = this.head;

        while (current != null) {
            list.add(current.getData().toString());
            current = current.getNext();
        }

       return list.toString();
    }

    // O(1)
    public int size() {
        return this.size;
    }


    // O(n)
    public void reverse() {
        DLLNode current = this.head;
        DLLNode trailing = null;

        while (current != null) {
            DLLNode temp = current.getNext();
            current.setNext(trailing);
            current.setPrev(temp);

            trailing = current;
            current = temp;
        }
        this.head = trailing;

    }

    // O(n)
    public DLLNode<T> findMedian() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("No elements in DLL");
        }

        if (this.size() == 1) {
            return this.head;
        } else if (this.size() == 2) {
            return this.head.getNext();
        }

        DLLNode<T> current = this.head;
        DLLNode<T> fastPointer = this.head;

        while (current.getNext() != null) {

            if (fastPointer == null || fastPointer.getNext() == null) { //rounds up if even number of elements
                return current;
            }
            current = current.getNext();
            fastPointer = fastPointer.getNext().getNext();
        }

        return null;
    }

    // O(n)
    public DLLNode<T> getNodeAtIndex(int index) {
        if (index >= this.size) {
            throw new IllegalArgumentException("Invalid index.");
        }
        DLLNode<T> current = this.head;
        int i = 0;
        while (current.getNext() != null && i < index) {
            current = current.getNext();
            i++;
        }
        return current;
    }

    //O(n)
    public T removeAtIndex(int index) {

        if (index >= this.size()) {
            throw new IllegalArgumentException("Invalid index");
        }

        if (this.isEmpty()) {
            throw new NoSuchElementException("No elements in DLL");
        }

        T theSlab;

        if (index == 0) {
            theSlab = this.head.getData();
            this.head.getNext().setPrev(null);
            this.head = null;
            return theSlab;
        }

        DLLNode<T> current = this.head;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }
        theSlab = current.getNext().getData();
        DLLNode<T> focus = (current.getNext() != null) ? current.getNext().getNext() : null;
        if (focus != null) {
            focus.setPrev(current);
        }
        current.setNext(focus);

        this.size--;
        return theSlab;
    }

}
