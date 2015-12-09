public class MapEntry<K, V> {
    private K key;
    private V value;
    private MapEntry<K, V> next;

    public MapEntry() {
        this.key = null;
        this.value = null;
    }

    public MapEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return this.key;
    }
    public V getValue() {
        return this.value;
    }
    public MapEntry<K, V> getNext() {
        return this.next;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public void setNext(MapEntry<K,V> next) {
        this.next = next;
    }

    public String toString() {
        return("{" + this.key.toString() + " : " + this.value.toString() + "}");
    }
}
