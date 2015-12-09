import java.util.NoSuchElementException;

/**
 * Created by robert on 11/30/15.
 */
public class MinHeap<T extends Comparable<? super T>> {
    private T[] arr;
    private int size;
    private final int STARTING_SIZE = 11;

    public MinHeap() {
        this.arr = (T[]) new Comparable[STARTING_SIZE];
        this.size = 0;
    }

    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data argument cannot be null.");
        }

        if (this.arr[this.arr.length - 1] != null) {
            this.resize();
        }

        if (this.isEmpty()) {
            this.arr[1] = data;
            this.size++;
        } else {
            this.size++;
            arr[this.size] = data;

            int i = this.size;
            while (i > 1 && this.arr[i].compareTo(this.arr[i / 2]) < 0) {
                swap(i, i / 2);
                i /= 2;
            }
        }
    }

    public void add(T... data) {
        if (data == null) {
            throw new IllegalArgumentException("Data argument cannot be null.");
        }

        for (T element : data) {
            add(element);
        }
    }

    public T remove() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("No elements exist in maximum heap.");
        }

        T theSlab = this.arr[1];
        swap(1, this.size);
        this.arr[this.size--] = null;

        if (this.size > 1) {
            heapify(1);
        }
        return theSlab;
    }

    private void heapify(int i) {
        int left = 2 * i;
        int right = 2 * i + 1;

        if (this.size > left) {
            if (arr[right].compareTo(arr[left]) > 0) {
                swap(i, left);
                heapify(left);
            } else {
                swap(i, right);
                heapify(right);
            }
        }

    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return (this.arr[1] == null);
    }

    private void swap(int first, int second) {
        T temp = this.arr[first];
        this.arr[first] = this.arr[second];
        this.arr[second] = temp;
    }

    private void resize() {
        T[] newArr = (T[]) new Comparable[arr.length * 2];
        for (int i = 1; i < this.arr.length; i++) {
            newArr[i] = arr[i];
        }
        this.arr = newArr;
    }

    public String toString() {
        String newString = "[";
        for (int i = 0; i < arr.length; i++) {
            newString += (arr[i] != null) ? arr[i].toString() : "null";
            newString += ((i != arr.length - 1) ? ", " : "]");
        }
        return newString;
    }
/*
    public static void main(String[] args) {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.add(1,6,100,4,15,0,12,11,9,200,99,2,1);
        System.out.println(heap);
        System.out.println(heap.contains(100));

    }*/

    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data argument cannot be null.");
        }

        for (T element : this.arr) {
            if (element != null) {
                if (element.equals(data)) {
                    return true;
                }
            }
        }
        return false;
    }

    public T peek() {
        return this.arr[1];
    }

    public void clear() {
        this.arr = (T[]) new Comparable[STARTING_SIZE];
        this.size = 0;
    }

}
