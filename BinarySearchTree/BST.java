import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BST<T extends Comparable<? super T>> {
    public BSTNode<T> root;
    public int size;

    public BST() {
        this.root = null;
        this.size = 0;
    }

    public BST(List<T> list) {
        if (list == null) {
            throw new IllegalArgumentException("List argument cannot be null");
        }
        for (T element : list) {
            if (element == null) {
                throw new IllegalArgumentException("Element in list cannot be null");
            }
            add(element);
        }
    }

    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data argument is null.");
        }

        if (this.root == null) {
            BSTNode<T> newNode = new BSTNode<>(data);
            this.root = newNode;
            this.size++;
        } else {
            addHelper(data, this.root);
        }
    }

    public void addHelper(T data, BSTNode<T> current) {
        if (data.compareTo(current.getData()) > 0) {
            if (current.getRight() == null) {
                current.setRight(new BSTNode<>(data));
                this.size++;
            } else {
                addHelper(data, current.getRight());
            }
        } else if (data.compareTo(current.getData()) < 0) {
            if (current.getLeft() == null) {
                current.setLeft(new BSTNode<>(data));
                this.size++;
            } else {
                addHelper(data, current.getLeft());
            }
        }
    }

    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data argument is null.");
        }

        if (this.isEmpty()) {
            throw new NoSuchElementException("No elements currently exist in BST");
        }

        T theSlab = null;

        if (this.root.equals(data)) {
            theSlab = this.root.getData();
            this.root = null;
            this.size--;
            return theSlab;
        } else {
           return removeHelper(data, this.root, this.root);
        }
    }

    /**
     * Helper method for remove() that handles traversal through the BST..
     * @param data - Data we're searching for.
     * @param parent - lagging pointer of current
     * @param current - the current node we are comparing to
     * @return Data that was removed.
     */

    private T removeHelper(T data, BSTNode<T> parent, BSTNode<T> current) {
        if (data.compareTo(current.getData()) > 0) {
            if (current.getRight() == null) {
                throw new NoSuchElementException("Element does not exist in BST");
            } else if (current.getRight().getData().equals(data)) {
                return remove(current, current.getRight());
            } else {
                return removeHelper(data, current, current.getRight());
            }
        } else {
            if (current.getLeft() == null) {
                throw new NoSuchElementException("Element does not exist in BST");
            } else if (current.getLeft().getData().equals(data)) {
                return remove(current, current.getLeft());
            } else {
                return removeHelper(data, current, current.getLeft());
            }
        }
    }

    /**
     * Helper method that actually hands removing the node and logic according to children of the child
     * @param parent - parent of child
     * @param child - the node we are deleting from the BST
     * @return data of the removed node.
     */
    private T remove(BSTNode<T> parent, BSTNode<T> child) {
        if (child.getLeft() == null && child.getRight() == null) {
            if (parent.getRight().getData().equals(child.getData())) {
                parent.setRight(null);
            } else {
                parent.setLeft(null);
            }
        } else if (child.getRight() != null && child.getLeft() == null) {
            if (parent.getRight().getData().equals(child.getData())) {
                parent.setRight(child.getRight());
            } else {
                parent.setLeft(child.getRight());
            }
        } else if (child.getLeft() != null && child.getRight() == null) {
            if (parent.getRight().getData().equals(child.getData())) {
                parent.setRight(child.getLeft());
            } else {
                parent.setLeft(child.getLeft());
            }
        } else {
            if (parent.getRight().getData().equals(child.getData())) {
                parent.setRight(child.getRight());
            } else {
                parent.setLeft(child.getRight());
            }
        }
        this.size--;
        return child.getData();
    }

    public int size() {
        return this.size;
    }

    public void clear() {
        this.root = null;
        this.size = 0;
    }


    public boolean isEmpty() {
        return (this.root == null);
    }

    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data argument cannot be null.");
        }

        BSTNode<T> foundNode = get(data);
        return (foundNode != null);
    }

    public BSTNode<T> get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data argument cannot be null.");
        }

        if (this.root == null) {
            throw new NoSuchElementException("No elements exist in this BST.");
        }

        return getHelper(data, this.root);
    }

    private BSTNode<T> getHelper(T data, BSTNode<T> current) {
        if (data.compareTo(current.getData()) > 0) {
            return (current.getRight() == null) ? null : getHelper(data, current.getRight());
        } else if (data.compareTo(current.getData()) < 0) {
            return (current.getLeft() == null) ? null : getHelper(data, current.getLeft());
        }
        return current;
    }


    public List<T> inOrderTraversal() {
        ArrayList<T> list = new ArrayList<>();
        return inOrderTraversal(list, this.root);
    }

    public List<T> inOrderTraversal(List<T> list, BSTNode<T> current) {
        if (current.getLeft() != null) {
            inOrderTraversal(list, current.getLeft());
        }
        list.add(current.getData());
        if (current.getRight() != null) {
            inOrderTraversal(list, current.getRight());
        }
        return list;
    }


    public List<T> preOrderTraversal() {
        ArrayList<T> list = new ArrayList<>();
        return preOrderTraversal(list, this.root);
    }

    public List<T> preOrderTraversal(List<T> list, BSTNode<T> current) {
        list.add(current.getData());
        if (current.getRight() != null) {
            preOrderTraversal(list, current.getRight());
        }
        if (current.getLeft() != null) {
            preOrderTraversal(list, current.getLeft());
        }
        return list;
    }


    public List<T> postOrderTraversal() {
        ArrayList<T> list = new ArrayList<>();
        return postOrderTraversal(list, this.root);
    }

    public List<T> postOrderTraversal(List<T> list, BSTNode<T> current) {
        if (current.getRight() != null) {
            postOrderTraversal(list, current.getRight());
        }
        if (current.getLeft() != null) {
            postOrderTraversal(list, current.getLeft());
        }
        list.add(current.getData());
        return list;
    }

}