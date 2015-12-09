public class MaximumPriorityQueue<T extends Comparable<? super T>> {
    private MaxHeap<T> heap;
    public MaximumPriorityQueue() {
        this.heap = new MaxHeap<>();
    }

    public void enqueue(T item) {
        heap.add(item);
    }

    public T dequeue() {
        return heap.remove();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    public void clear() {
        heap.clear();
    }

    public MaxHeap<T> getBackingHeap() {
        return heap;
    }
}