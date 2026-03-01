public class QueueImplLinked<E> implements Queue<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    public QueueImplLinked() {
        head = null;
        tail = null;
        size = 0;
    }

    public void enqueue(E data) {
        Node<E> newNode = new Node<>(data, null);
        if (isEmpty())
            head = newNode;
        else
            tail.next = newNode;
        tail = newNode;
        size++;
    }

    public E dequeue() throws WrongIndexException {
        if (isEmpty()) throw new WrongIndexException("Cola vacía");
        E data = head.data;
        head = head.next;
        if (head == null) tail = null;
        size--;
        return data;
    }

    public E peek() throws WrongIndexException {
        if (isEmpty()) throw new WrongIndexException("Cola vacía");
        return head.data;
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }
}