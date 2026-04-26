public class AVL<E extends Comparable<E>> extends BST<E> {
    private class AVLNode {
        E data;
        int height;
        AVLNode left, right;
        AVLNode(E data) { this.data = data; this.height = 1; }
    }

    private AVLNode rootAVL;

    private int height(AVLNode n) { return n == null ? 0 : n.height; }
    private int getBalance(AVLNode n) { return n == null ? 0 : height(n.left) - height(n.right); }

    private AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    private AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }

    public void add(E data) { rootAVL = addRec(rootAVL, data); }
    private AVLNode addRec(AVLNode node, E data) {
        if (node == null) return new AVLNode(data);
        int cmp = data.compareTo(node.data);
        if (cmp < 0) node.left = addRec(node.left, data);
        else if (cmp > 0) node.right = addRec(node.right, data);
        else return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);

        if (balance > 1 && data.compareTo(node.left.data) < 0) return rotateRight(node);
        if (balance < -1 && data.compareTo(node.right.data) > 0) return rotateLeft(node);
        if (balance > 1 && data.compareTo(node.left.data) > 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if (balance < -1 && data.compareTo(node.right.data) < 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
    }
}