public class MinimumPriorityQueue<T extends Comparable<? super T>> {
    private MinHeap<T> heap;
    public MinimumPriorityQueue() {
        this.heap = new MinHeap<>();
    }

    public void enqueue(T item) {
        this.heap.add(item);
    }

    public T dequeue() {
        return this.heap.remove();
    }

    public boolean isEmpty() {
        return this.heap.isEmpty();
    }

    public int size() {
        return this.heap.size();
    }

    public void clear() {
        this.heap.clear();
    }

    public MinHeap<T> getBackingHeap() {
        return this.heap;
    }
}