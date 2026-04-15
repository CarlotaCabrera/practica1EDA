import java.util.Iterator;
import java.util.Stack;
import java.util.NoSuchElementException;

public class BST<E extends Comparable<E>> implements Iterable<E> {
    private Node<E> root;

    private static class Node<E> {
        E data;
        Node<E> left, right;
        Node(E data) { this.data = data; }
    }

    public void add(E value) {
        if (root == null) { root = new Node<>(value); return; }
        Node<E> current = root, parent = null;
        int cmp = 0;
        while (current != null) {
            parent = current;
            cmp = value.compareTo(current.data);
            if (cmp < 0) current = current.left;
            else if (cmp > 0) current = current.right;
            else return;
        }
        if (cmp < 0) parent.left = new Node<>(value);
        else parent.right = new Node<>(value);
    }

    public Node<E> search(E value) {
        Node<E> current = root;
        while (current != null) {
            int cmp = value.compareTo(current.data);
            if (cmp == 0) return current;
            current = (cmp < 0) ? current.left : current.right;
        }
        return null;
    }

    public void delete(E value) {
        Node<E> parent = null, current = root;
        while (current != null && !current.data.equals(value)) {
            parent = current;
            current = (value.compareTo(current.data) < 0) ? current.left : current.right;
        }
        if (current == null) return;
        if (current.left != null && current.right != null) {
            Node<E> sP = current, s = current.right;
            while (s.left != null) { sP = s; s = s.left; }
            current.data = s.data;
            current = s; parent = sP;
        }
        Node<E> child = (current.left != null) ? current.left : current.right;
        if (parent == null) root = child;
        else if (parent.left == current) parent.left = child;
        else parent.right = child;
    }

    public E min() {
        if (root == null) return null;
        Node<E> curr = root;
        while (curr.left != null) curr = curr.left;
        return curr.data;
    }

    @Override
    public Iterator<E> iterator() { return new InOrderIterator(); }

    private class InOrderIterator implements Iterator<E> {
        private Stack<Node<E>> stack = new Stack<>();
        public InOrderIterator() { pushLeft(root); }
        private void pushLeft(Node<E> n) { while (n != null) { stack.push(n); n = n.left; } }
        public boolean hasNext() { return !stack.isEmpty(); }
        public E next() {
            Node<E> n = stack.pop();
            pushLeft(n.right);
            return n.data;
        }
    }
}