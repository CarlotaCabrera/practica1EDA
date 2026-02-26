import java.util.Iterator;

public class ListImpl<E> implements List<E> {
    private Node<E> head;
    private int size;

    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private class CIterator implements Iterator<E> {
        private Node<E> current;

        public CIterator() {
            this.current = head;
        }

        public boolean hasNext() {
            return current != null;
        }

        public E next() {
            E data = current.data;
            current = current.next;
            return data;
        }
    }

    public ListImpl() {
        this.head = null;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public void insert(int pos, E data) throws WrongIndexException {
        if (pos < 0 || pos > size)
            throw new WrongIndexException("Posición inválida: " + pos);
        if (pos == 0) {
            head = new Node<>(data, head);
        } else {
            Node<E> current = head;
            for (int i = 0; i < pos - 1; i++)
                current = current.next;
            current.next = new Node<>(data, current.next);
        }
        size++;
    }

    public void delete(int pos) throws WrongIndexException {
        if (pos < 0 || pos >= size)
            throw new WrongIndexException("Posición inválida: " + pos);
        if (pos == 0) {
            head = head.next;
        } else {
            Node<E> current = head;
            for (int i = 0; i < pos - 1; i++)
                current = current.next;
            current.next = current.next.next;
        }
        size--;
    }

    public E get(int pos) throws WrongIndexException {
        if (pos < 0 || pos >= size)
            throw new WrongIndexException("Posición inválida: " + pos);
        Node<E> current = head;
        for (int i = 0; i < pos; i++)
            current = current.next;
        return current.data;
    }

    public int search(E data) {
        Node<E> current = head;
        int pos = 0;
        while (current != null) {
            if (current.data.equals(data))
                return pos;
            current = current.next;
            pos++;
        }
        return -1;
    }

    public Iterator<E> iterator() {
        return new CIterator();
    }
}