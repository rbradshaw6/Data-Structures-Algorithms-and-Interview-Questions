/**
 * Created by robert on 11/8/15.
 */
public class BSTNode<T> {
    private T data;
    private BSTNode<T> left;
    private BSTNode<T> right;

    public BSTNode() {
        this.data = null;
        this.left = null;
        this.right = null;
    }

    public BSTNode(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public T getData() {
        return this.data;
    }
    public BSTNode<T> getLeft() {
        return this.left;
    }
    public BSTNode<T> getRight() {
        return this.right;
    }

    public void setData(T data) {
        this.data = data;
    }
    public void setLeft(BSTNode<T> left) {
        this.left = left;
    }
    public void setRight(BSTNode<T> right) {
        this.right = right;
    }

    public String toString() {
        return this.data.toString();
    }
}
