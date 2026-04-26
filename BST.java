import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import java.util.NoSuchElementException;
import java.util.Queue;


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

public void preOrden() { preOrden(root); System.out.println(); }
private void preOrden(Node<E> n) {
    if (n != null) { System.out.print(n.data + " "); preOrden(n.left); preOrden(n.right); }
}

public void inOrden() { inOrden(root); System.out.println(); }
private void inOrden(Node<E> n) {
    if (n != null) { inOrden(n.left); System.out.print(n.data + " "); inOrden(n.right); }
}

public void postOrden() { postOrden(root); System.out.println(); }
private void postOrden(Node<E> n) {
    if (n != null) { postOrden(n.left); postOrden(n.right); System.out.print(n.data + " "); }
}

public void recorridoAnchura() {
    if (root == null) return;
    Queue<Node<E>> cola = new LinkedList<>();
    cola.add(root);
    while (!cola.isEmpty()) {
        Node<E> actual = cola.poll();
        System.out.print(actual.data + " ");
        if (actual.left != null) cola.add(actual.left);
        if (actual.right != null) cola.add(actual.right);
    }
    System.out.println();
}

public void mayoresQue(E elemento) {
    mayoresQueRec(root, elemento);
    System.out.println();
}
private void mayoresQueRec(Node<E> n, E elemento) {
    if (n == null) return;
    if (n.data.compareTo(elemento) > 0) {
        mayoresQueRec(n.left, elemento);
        System.out.print(n.data + " ");
        mayoresQueRec(n.right, elemento);
    } else {
        mayoresQueRec(n.right, elemento);
    }
}
}