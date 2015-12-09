public class CDLLNode<T extends Comparable<? super T >> {
    protected T data;
    protected CDLLNode<T> next;
    protected CDLLNode<T> prev;

    protected CDLLNode() {
        this.data = null;
        this.next = null;
        this.prev = null;
    }

    protected CDLLNode(T data) {
        this(data, null, null);
    }

    protected CDLLNode(T data, CDLLNode<T> next, CDLLNode<T> prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    protected CDLLNode<T> getNext() { return this.next; }

    protected CDLLNode<T> getPrev() {
        return this.prev;
    }

    protected T getData() {
        return this.data;
    }

    protected void setNext(CDLLNode<T> next) {
        this.next = next;
    }

    protected void setPrev(CDLLNode<T> prev) {
        this.prev = prev;
    }

    public String toString() {
        return ("" + this.data.toString());
    }
}
