public class PriorityQueue<E extends Comparable<E>> {

    private Node<E> root;
    public PriorityQueue() {
        root = null;
    }
    //public PriorityQueue(E value) {
    //    root = null;
    //}

    public void enqueue(E value) {
        root = merge(new Node<>(value, null, null), root);
    }

    //private void reEnqueue(E value, Node<E> node) {
    //    if (node == null) {
    //        node.value = value;
    //    }
    //    if (value.compareTo(node.value) < 0) {
    //        reEnqueue(node.value, node.left);
    //        node.value = value;
    //    }
    //    if (value.compareTo(node.value) > 0) {
    //        reEnqueue(value, node.left);
    //    }
    //    reEnqueue(value, node.right);
    //}

    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        Node<E> temp = new Node<>(root.value, root.left, root.right);
        root = merge(root.left, root.right);
        return temp.value;
    }

    public boolean isEmpty() {
        if (this.root == null) {
            return true;
        }
        return false;
    }

    public void printTree(String label) {
        System.out.println(label);
        rePrintTree(root, 0);
    }
    private void rePrintTree(Node node, int depth) {
        if (node == null) {
            return;
        }
        String indentStr = "";
        for (int i = 0; i<depth; i++) {
            indentStr += "  ";
        }
        if (node.value != null) {
            rePrintTree(node.right, depth+1);
            System.out.println(indentStr + node.value + "(" + node.npl + ")");
            rePrintTree(node.left, depth+1);
        }

    }

    private Node<E> merge(Node<E> t1, Node<E> t2) {
        Node<E> small;
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        if (t1.value.compareTo(t2.value) < 0) {
            t1.right = merge(t1.right, t2);
            small = t1;
        }
        else {
            t2.right = merge(t2.right, t1);
            small = t2;
        }
        if (getNpl(small.left) < getNpl(small.right))
            swapkids(small);
        //small.npl = getNpl(small.right) + 1;
        setNullPathLength(small);
        return small;
    }

    private void setNullPathLength(Node node) {
        if (node.right == null || node.left == null) {
            node.npl = 0;
        }
        if (node.left != null && node.right != null) {
            if (node.left.npl >= node.right.npl) {
                node.npl = getNpl(node.left) + 1;
            }
            else {
                node.npl = getNpl(node.left) + 1;
            }
        }
    }

    private void swapkids(Node<E> node) {
        Node<E> temp = new Node<>(node.value, node.left, node.right);
        node.right = node.left;
        node.left = temp.right;
    }

    private int getNpl(Node<E> node) {
        if (node == null) {
            return -1;
        }
        return node.npl;
    }

    private class Node<E> {
        public E value;
        public Node<E> left;
        public Node<E> right;
        public int npl;

        public Node(E value, Node<E> left, Node<E> right) {
            this.value = value;
            this.left = left;
            this.right = right;
            npl = 0;
        }
    }
}
