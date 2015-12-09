public class DLLNode<T> {
    protected T data;
    protected DLLNode<T> next;
    protected DLLNode<T> prev;

    protected DLLNode() {
        this.data = null;
        this.next = null;
        this.prev = null;
    }

    protected DLLNode(T data) {
        this(data, null, null);
    }

    protected DLLNode(T data, DLLNode<T> next, DLLNode<T> prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    protected DLLNode<T> getNext() {
        return this.next;
    }

    protected DLLNode<T> getPrev() {
        return this.prev;
    }

    protected T getData() {
        return this.data;
    }

    protected void setNext(DLLNode<T> next) {
        this.next = next;
    }

    protected void setPrev(DLLNode<T> prev) {
        this.prev = prev;
    }

    public String toString() {
        return ("" + this.data.toString());
    }
}
