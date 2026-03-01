import java.util.Iterator;

public class HashTable<K, V> implements Dictionary<K, V> {
    private static class TableEntry<K, V> {
        K key;
        V value;
        TableEntry<K, V> next;

        TableEntry(K key, V value, TableEntry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private TableEntry<K, V>[] table;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;
    private static final double MAX_LOAD_FACTOR = 0.75;

    @SuppressWarnings("unchecked")
    public HashTable() {
        table = new TableEntry[DEFAULT_CAPACITY];
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        table = new TableEntry[capacity];
        size = 0;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % table.length);
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        TableEntry<K, V>[] oldTable = table;
        table = new TableEntry[oldTable.length * 2];
        size = 0;
        for (TableEntry<K, V> entry : oldTable) {
            while (entry != null) {
                put(entry.key, entry.value);
                entry = entry.next;
            }
        }
    }

    public V put(K key, V value) {
        if ((double)(size + 1) / table.length > MAX_LOAD_FACTOR)
            rehash();
        int index = hash(key);
        TableEntry<K, V> current = table[index];
        while (current != null) {
            if (current.key.equals(key)) {
                V old = current.value;
                current.value = value;
                return old;
            }
            current = current.next;
        }
        table[index] = new TableEntry<>(key, value, table[index]);
        size++;
        return null;
    }

    public V get(K key) {
        int index = hash(key);
        TableEntry<K, V> current = table[index];
        while (current != null) {
            if (current.key.equals(key)) return current.value;
            current = current.next;
        }
        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        TableEntry<K, V> current = table[index];
        TableEntry<K, V> prev = null;
        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) table[index] = current.next;
                else prev.next = current.next;
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    public boolean contains(K key) { return get(key) != null; }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    @SuppressWarnings("unchecked")
    public void clear() {
        table = new TableEntry[DEFAULT_CAPACITY];
        size = 0;
    }

    public Iterator<K> iterator() { return new CIterator(); }

    private class CIterator implements Iterator<K> {
        private int index = 0;
        private TableEntry<K, V> current = null;

        public CIterator() { advance(); }

        private void advance() {
            while (index < table.length && current == null) {
                current = table[index++];
            }
        }

        public boolean hasNext() { return current != null; }

        public K next() {
            K key = current.key;
            current = current.next;
            if (current == null) advance();
            return key;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < table.length; i++) {
            sb.append(i).append(": ");
            TableEntry<K, V> current = table[i];
            while (current != null) {
                sb.append("[").append(current.key).append("->").append(current.value).append("] ");
                current = current.next;
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}