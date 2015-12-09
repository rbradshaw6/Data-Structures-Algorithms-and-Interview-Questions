import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Simple hash map that utilizes linear probing.
 */
public class HashMap2<K, V> {
    private int size;
    private MapEntry<K, V>[] table;
    private final int STARTING_SIZE = 11; //Prime numbers are cool for some reason.
    private final double MAX_LOAD_FACTOR = 0.75;

    public HashMap2() {
        this.size = 0;
        this.table = new MapEntry[STARTING_SIZE];
    }

    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key argument cannot be null.");
        }

        if (value == null) {
            throw new IllegalArgumentException("Value argument cannot be null.");
        }

        if (this.size + 1 > (this.table.length * MAX_LOAD_FACTOR)) {
            this.resize();
        }

        int index = key.hashCode() % this.table.length;
        int i = index;

        if (this.table[index] == null) {
            this.table[index] = new MapEntry<>(key, value);
            this.size++;
        } else {
            while (this.table[i] != null) {
                if (this.table[i].getKey().equals(key)) {
                    this.table[i].setValue(value);
                    return;
                }
                if (i == this.table.length - 1) {
                    i = 0;
                }  else {
                    i++;
                }
            }
            this.table[i] = new MapEntry<>(key, value);
            this.size++;
        }
    }

    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key argument cannot be null.");
        }

        int index = key.hashCode() % this.table.length;
        int i = index;

        do {
            if (this.table[i] != null) {
                if (this.table[i].getKey().equals(key)) {
                    V theSlab = this.table[i].getValue();
                    this.table[i] = null;
                    this.size--;
                    return theSlab;
                }
            }
            if (i == this.table.length - 1) {
                i = 0;
            } else {
                i++;
            }

        } while (i != index);

        throw new NoSuchElementException("Key does not exist in Hash Map.");
    }

    private void resize() {
        MapEntry<K, V>[] temp = this.table;
        this.table = new MapEntry[temp.length * 2 + 1]; //Prime numbers are cool for some reason in hash tables.
        this.size = 0;

        for (int i = 0; i < temp.length; i++) {
            if (temp[i] != null) {
                put(temp[i].getKey(), temp[i].getValue());
            }
        }
    }

    public boolean isEmpty() {
        for (MapEntry<K, V> entry : this.table) {
            if (entry != null) {
                return true;
            }
        }
        return false;
    }

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key argument cannot be null.");
        }

        V theSlab = null;
        int index = key.hashCode() % this.table.length;

        int i = index;

        do {
            if (this.table[i] != null) {
                if (this.table[i].getKey().equals(key)) {
                    return this.table[i].getValue();
                }
            }
            if (i == this.table.length - 1) {
                i = 0;
            } else {
                i++;
            }
        } while (i != index);

        return null;

    }


    public String toString() {
        String newString = "[";
        for (int i = 0; i < table.length; i++) {
            MapEntry<K, V> current = this.table[i];
            if (current != null) {
                while (current != null) {
                    newString += current.toString();
                    if (current.getNext() != null) {
                        newString += " -> ";
                    }
                    current = current.getNext();
                }
            } else {
                newString += "null";
            }
            newString += (i != table.length - 1) ? ", " : "]";
        }
        return newString;
    }

    public int size() {
        return this.size;
    }

    public boolean contains(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key argument cannot be null.");
        }

        for (MapEntry<K, V> entry : this.table) {
            if (entry.equals(key)) {
                return true;
            }
        }

        return false;
    }

    public void clear() {
        this.size = 0;
        this.table = new MapEntry[STARTING_SIZE];
    }

    public static void main(String[] args) {
        HashMap2<Integer, String> map = new HashMap2<>();

        map.put(1, "lel");
        map.put(1, "butts");
        map.put(2, "lol");
        map.put(3, "kek");
        map.put(3, "lel");
        map.remove(3);

        System.out.println(map);
    }


}
