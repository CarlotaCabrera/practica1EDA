public class StackImplLinked<E> implements Stack<E> {
    private Node<E> top;
    private int size;

    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    public StackImplLinked() {
        top = null;
        size = 0;
    }

    public void push(E data) {
        top = new Node<>(data, top);
        size++;
    }

    public E pop() throws WrongIndexException {
        if (isEmpty()) throw new WrongIndexException("Pila vacía");
        E data = top.data;
        top = top.next;
        size--;
        return data;
    }

    public E peek() throws WrongIndexException {
        if (isEmpty()) throw new WrongIndexException("Pila vacía");
        return top.data;
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }
}