import java.util.Iterator;

public class ListImplArray<E> implements List<E> {
    private Object[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public ListImplArray() {
        data = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private void grow() {
        Object[] newData = new Object[data.length * 2];
        for (int i = 0; i < size; i++)
            newData[i] = data[i];
        data = newData;
    }

    public int size() { return size; }

    public void insert(int pos, E elem) throws WrongIndexException {
        if (pos < 0 || pos > size)
            throw new WrongIndexException("Posición inválida: " + pos);
        if (size == data.length) grow();
        for (int i = size; i > pos; i--)
            data[i] = data[i-1];
        data[pos] = elem;
        size++;
    }

    public void delete(int pos) throws WrongIndexException {
        if (pos < 0 || pos >= size)
            throw new WrongIndexException("Posición inválida: " + pos);
        for (int i = pos; i < size - 1; i++)
            data[i] = data[i+1];
        data[size-1] = null;
        size--;
    }

    @SuppressWarnings("unchecked")
    public E get(int pos) throws WrongIndexException {
        if (pos < 0 || pos >= size)
            throw new WrongIndexException("Posición inválida: " + pos);
        return (E) data[pos];
    }

    public int search(E elem) {
        for (int i = 0; i < size; i++)
            if (data[i].equals(elem)) return i;
        return -1;
    }

    public Iterator<E> iterator() { return new CIterator(); }

    private class CIterator implements Iterator<E> {
        private int current = 0;

        public boolean hasNext() { return current < size; }

        @SuppressWarnings("unchecked")
        public E next() { return (E) data[current++]; }
    }
}