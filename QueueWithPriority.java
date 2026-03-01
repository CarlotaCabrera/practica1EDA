public class QueueWithPriority<E> implements Queue<E> {
    private Node<E> head;
    private int size;

    private static class Node<E> {
        E data;
        int priority;
        Node<E> next;

        Node(E data, int priority, Node<E> next) {
            this.data = data;
            this.priority = priority;
            this.next = next;
        }
    }

    public QueueWithPriority() {
        head = null;
        size = 0;
    }

    public void enqueue(E data) {
        enqueue(data, 0);
    }

    public void enqueue(E data, int priority) {
        Node<E> newNode = new Node<>(data, priority, null);
        if (head == null || priority > head.priority) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<E> current = head;
            while (current.next != null && current.next.priority >= priority)
                current = current.next;
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }

    public E dequeue() throws WrongIndexException {
        if (isEmpty()) throw new WrongIndexException("Cola vacía");
        E data = head.data;
        head = head.next;
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