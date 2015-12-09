public class SLLNode<T> {
    private T data;
    private SLLNode<T> next;

    public SLLNode() {

    }

    public SLLNode(T data) {
        this.data = data;
        this.next = null;
    }

    public SLLNode(T data, SLLNode<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return this.data;
    }

    public SLLNode<T> getNext() {
        return this.next;
    }

    public void setNext(SLLNode<T> next) {
        this.next = next;
    }

    public String toString() {
        return this.data.toString();
    }
}
