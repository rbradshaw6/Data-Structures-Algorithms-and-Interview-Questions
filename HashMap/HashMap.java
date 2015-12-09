import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Simple hash map that utilizes external chaining for collision resolution
 */

public class HashMap<K, V> {
    private int size;
    private MapEntry<K, V>[] table;
    private final int STARTING_SIZE = 11; //Prime numbers are cool for some reason.
    private final double MAX_LOAD_FACTOR = 0.67;


    public HashMap() {
        this.table = new MapEntry[STARTING_SIZE];
        this.size = 0;
    }

    private void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }

        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null.");
        }

        if (this.size + 1 > (this.table.length * MAX_LOAD_FACTOR)) {
            this.resize();
        }

        int index = key.hashCode() % this.table.length;

        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            this.size++;
        } else {
            MapEntry<K, V> current = this.table[index];
            if (current.getKey().equals(key)) {
                current.setValue(value);
                return;
            }
            while (current.getNext() != null) {
                if (current.getNext().getKey().equals(key)) {
                    current.getNext().setValue(value);
                    return;
                }
                current = current.getNext();
            }

            if (!current.getKey().equals(key)) {
                current.setNext(new MapEntry<>(key, value));
                this.size++;
            }

        }
    }

    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }

        V theSlab = null;

        for (int i = 0; i < table.length; i++) {
            MapEntry<K, V> current = this.table[i];
            if (current != null) {
                if (this.table[i].getKey().equals(key)) {
                    theSlab = current.getValue();
                    this.table[i] = current.getNext();
                    this.size--;
                    return theSlab;
                } else {
                    while (current.getNext() != null) {
                        if (current.getNext().getKey().equals(key)) {
                            theSlab = current.getNext().getValue();
                            current.setNext(current.getNext().getNext());
                            this.size--;
                            return theSlab;
                        }
                        current = current.getNext();
                    }
                }
            }
        }

       throw new IllegalArgumentException(key.toString() + " does not exist in HashMap.");

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

    public int size() {
        return this.size;
    }

    public boolean contains(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key argument cannot be null.");
        }

        for (int i = 0; i < table.length; i++) {
            MapEntry<K, V> current = this.table[i];
            if (current != null) {
                while (current != null) {
                    if (current.getKey().equals(key)) {
                        return true;
                    }
                    current = current.getNext();
                }
            }
        }
        return false;
    }

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key argument cannot be null.");
        }

        int index = key.hashCode() % this.table.length;
        MapEntry<K, V> current = this.table[index];

        if (current == null) {
            throw new NoSuchElementException(key.toString() + " does not exist in this Hash Map.");
        } else {
            if (current.getKey().equals(key)) {
                return current.getValue();
            } else {
                while (current.getNext() != null) {
                    if (current.getNext().getKey().equals(key)) {
                        return current.getNext().getValue();
                    }
                    current = current.getNext();
                }
            }
        }
        throw new NoSuchElementException(key.toString() + " does not exist in this Hash Map.");
    }

    public boolean isEmpty() {
        for (MapEntry<K, V> entry : this.table) {
            if (entry != null) {
                return true;
            }
        }
        return false;
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

    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (int i = 0; i < table.length; i++) {
            MapEntry<K, V> current = this.table[i];
            if (current != null) {
                while (current != null) {
                    keys.add(current.getKey());
                    current = current.getNext();
                }
            }
        }
        return keys;
    }


    public Collection<V> values() {
        Collection<V> values = new HashSet<>();
        for (int i = 0; i < table.length; i++) {
            MapEntry<K, V> current = this.table[i];
            if (current != null) {
                while (current != null) {
                    values.add(current.getValue());
                    current = current.getNext();
                }
            }
        }

        return values;
    }

    public void clear() {
        this.table = new MapEntry[STARTING_SIZE];
        this.size = 0;
    }


    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap();
        map.put(2, "lol");
        map.put(2, "b");
        map.put(3, "c");
        map.put(13, "swag");
        map.put(13, "newSwag");
        map.put(24, "lel");
        map.put(13, "test");
        map.put(14, "test");
        map.put(16, "test");
        map.put(157, "test");
        map.put(158, "test");
        map.put(152, "test");
        map.put(153, "test");
        map.put(154, "test");
        map.put(156, "test");
        map.put(15, "test");

        System.out.println(map);
        System.out.println(map.get(5));
        System.out.println(map);
    }
}
