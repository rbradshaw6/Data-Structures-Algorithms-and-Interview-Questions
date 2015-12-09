import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Timer;

public class CircularDoublyLinkedList<T extends Comparable<? super T>> {
    CDLLNode<T> head;
    int size;

    public CircularDoublyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data argument cannot be null.");
        }

        CDLLNode<T> newNode;
        if (this.isEmpty()) {
            newNode = new CDLLNode<>(data);
            newNode.setNext(newNode);
            newNode.setPrev(newNode);
            this.head = newNode;
            this.size++;
        } else if (this.size == 1) {
            newNode = new CDLLNode<>(data);
            newNode.setPrev(this.head);
            newNode.setNext(this.head);
            this.head.setNext(newNode);
            this.head.setPrev(newNode);
            this.size++;
        } else {
            newNode = new CDLLNode<>(data);
            newNode.setPrev(this.head.getPrev());
            this.head.getPrev().setNext(newNode);
            newNode.setNext(this.head);
            this.head.setPrev(newNode);
            this.size++;
        }
    }

    public void add(T... data) {
        if (data == null) {
            throw new IllegalArgumentException("Data argument cannot be null.");
        }
        for (T element : data) {
            if (element != null) {
                add(element);
            } else {
                throw new IllegalArgumentException("Element cannot be null");
            }

        }
    }

    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data argument cannot be null.");
        }

        if (this.isEmpty()) {
            throw new NoSuchElementException("No elements exist in CDLL");
        }

        T theSlab = null;
        if (this.head.getData().equals(data)) {
            theSlab = this.head.getData();
            if (this.size == 1) {
                this.clear();
            } else {
                this.head.getPrev().setNext(this.head.getNext());
                this.head.getNext().setPrev(this.head.getPrev());
                this.head = this.head.getNext();
            }
        } else if (this.head.getPrev().getData().equals(data)) {
            theSlab = this.head.getPrev().getData();
            this.head.getPrev().getPrev().setNext(this.head);
            this.head.setPrev(this.head.getPrev().getPrev());
        } else {
            CDLLNode<T> current = this.head;
            while (current.getNext() != this.head) {
                if (current.getNext().getData().equals(data)) {
                    theSlab = current.getNext().getData();
                    current.setNext(current.getNext().getNext());
                    current.getNext().getNext().setPrev(current);
                    this.size--;
                    return theSlab;
                }
                current = current.getNext();
            }
        }
        if (theSlab != null) {
            this.size--;
            return theSlab;
        }
        throw new NoSuchElementException("Data not found in CDLL");
    }

    private T removeFromBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data argument cannot be null.");
        }

        CDLLNode<T> current = this.head.getPrev();
        T theSlab = null;

        while (current.getPrev() != this.head) {
            if (current.getPrev().getData().equals(data)) {
                theSlab = current.getPrev().getData();
                current.getPrev().getPrev().setNext(current);
                current.setPrev(current.getPrev().getPrev());
                this.size--;
                return theSlab;
            }

            current = current.getPrev();
        }

        return theSlab;
    }

    public int removeAll(T data) {
        T theSlab = null;
        CDLLNode<T> current = this.head;
        int i = 0;
        while (!current.getNext().equals(this.head)) {
            if (current.getNext().getData().equals(data)) {
                theSlab = current.getNext().getData();
                current.setNext(current.getNext().getNext());
                current.getNext().getNext().setPrev(current);
                this.size--;
                i++;
            }
            current = current.getNext();
        }
        return i;
    }

    public T removeAtIndex(int index) {
        if (index >= this.size) {
            throw new IllegalArgumentException("pls no");
        }

        T theSlab = null;
        if (index == 0) {
            theSlab = this.head.getData();
            if (this.size == 1) {
                this.clear();
            } else {
                this.head.getPrev().setNext(this.head.getNext());
                this.head.getNext().setPrev(this.head.getPrev());
                this.head = this.head.getNext();
            }
        } else if (index == this.size - 1) {
            theSlab = this.head.getPrev().getData();
            this.head.getPrev().getPrev().setNext(this.head);
            this.head.setPrev(this.head.getPrev().getPrev());
        } else {
            CDLLNode<T> current = this.head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            theSlab = current.getData();
            current.getPrev().setNext(current.getNext());
            current.getNext().setPrev(current.getPrev());
        }
        if (theSlab != null) {
            this.size--;
            return theSlab;
        }
        throw new NoSuchElementException("Data not found in CDLL");
    }

    public int size() {
        return this.size;
    }

    public void clear() {
        this.head = null;
        this.size = 0;
    }

    public CDLLNode<T> get(int index) {
        if (index >= this.size) {
            throw new IllegalArgumentException("pls no");
        }
        if (index == 0) {
            return this.head;
        } else if (index == 1) {
            return this.head.getNext();
        }

        CDLLNode<T> current = this.head;
        for (int i = 0; i < index && current.getNext() != null; i++) {
            current = current.getNext();
        }
        return current;
    }


    public boolean isPalindrome() {
        if (this.isEmpty()) {
            return false;
        }
        CDLLNode<T> front = this.head;
        CDLLNode<T> back = this.head.getPrev();

        while (front != back && this.head.getPrev() != front && back != this.head) {
            if (!front.getData().equals(back.getData())) {
                return false;
            }
            front = front.getNext();
            back = back.getPrev();
        }

        return true;
    }

    public Object[] toArray() {
        if (this.isEmpty()) {
            return null;
        }

        Object[] arr = new Comparable[this.size];

        CDLLNode<T> current = this.head;
        int i = 0;
        while (current != null && i < this.size) {
            arr[i] = current.getData();
            if (current.getNext().equals(this.head)) {
                return arr;
            }
            i++;
            current = current.getNext();
        }
        return arr;
    }

    public boolean isEmpty() {
        return (this.head == null);
    }

    @Override
    public String toString() {
        ArrayList<T> list = new ArrayList<>();
        CDLLNode<T> current = this.head;

        while (current != null) {
            list.add(current.getData());
            if (current.getNext().equals(this.head)) {
                return list.toString();
            }
            current = current.getNext();
        }
        return list.toString();
    }

    public static void main(String[] args) {
        CircularDoublyLinkedList<Integer> cdll = new CircularDoublyLinkedList<>();
        cdll.add(1,1);

        Object[] arr = cdll.toArray();
        System.out.println(arr);

    }

}
