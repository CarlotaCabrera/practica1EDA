public interface Queue<E> {
    void enqueue(E data);
    E dequeue() throws WrongIndexException;
    E peek() throws WrongIndexException;
    int size();
    boolean isEmpty();
}