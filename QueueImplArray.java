public class QueueImplArray<E> implements Queue<E> {
    private Object[] data;
    private int head;
    private int tail;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public QueueImplArray() {
        data = new Object[DEFAULT_CAPACITY];
        head = 0;
        tail = 0;
        size = 0;
    }

    private void grow() {
        Object[] newData = new Object[data.length * 2];
        for (int i = 0; i < size; i++)
            newData[i] = data[(head + i) % data.length];
        data = newData;
        head = 0;
        tail = size;
    }

    public void enqueue(E elem) {
        if (size == data.length) grow();
        data[tail] = elem;
        tail = (tail + 1) % data.length;
        size++;
    }

    @SuppressWarnings("unchecked")
    public E dequeue() throws WrongIndexException {
        if (isEmpty()) throw new WrongIndexException("Cola vacía");
        E elem = (E) data[head];
        data[head] = null;
        head = (head + 1) % data.length;
        size--;
        return elem;
    }

    @SuppressWarnings("unchecked")
    public E peek() throws WrongIndexException {
        if (isEmpty()) throw new WrongIndexException("Cola vacía");
        return (E) data[head];
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }
}