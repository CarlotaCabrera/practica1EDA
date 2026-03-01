public class StackImplArray<E> implements Stack<E> {
    private Object[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public StackImplArray() {
        data = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private void grow() {
        Object[] newData = new Object[data.length * 2];
        for (int i = 0; i < size; i++)
            newData[i] = data[i];
        data = newData;
    }

    public void push(E elem) {
        if (size == data.length) grow();
        data[size++] = elem;
    }

    @SuppressWarnings("unchecked")
    public E pop() throws WrongIndexException {
        if (isEmpty()) throw new WrongIndexException("Pila vacía");
        E elem = (E) data[--size];
        data[size] = null;
        return elem;
    }

    @SuppressWarnings("unchecked")
    public E peek() throws WrongIndexException {
        if (isEmpty()) throw new WrongIndexException("Pila vacía");
        return (E) data[size - 1];
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }
}