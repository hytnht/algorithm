package main.java.org.hy.datastructure.tree;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
    public BinarySearchTree(String traversal) {
        super(traversal);
    }

    @Override
    public void insert(T data) {
        Node current = root;
        Node prev = null;
        boolean subTree = true;
        if (isEmpty()) {
            root = new Node(data);
        } else {
            if (search(root, data) != null) {
                throw new Error("No duplicate allowed in BST.");
            }
            while (current != null) {
                if (data.compareTo(current.data) < 0) {
                    prev = current;
                    current = current.left;
                    subTree = true;
                } else if (data.compareTo(current.data) > 0) {
                    prev = current;
                    current = current.right;
                    subTree = false;
                }
            }
            current = new Node(data, null, null, prev);
            if (subTree) {
                prev.left = current;
            } else {
                prev.right = current;
            }
        }
        size++;
    }

    @Override
    public void remove(T data) {
        remove(search(root, data));
    }

    public void remove(Node node) {
        if (isEmpty()) {
            throw new Error("Empty tree.");
        }
        if (node == null) {
            throw new Error("Not in the tree.");
        }
        if (node.left == null && node.right == null) {
            set(node, null);
        } else {
            if (node.left == null) {
                set(node, node.right);
            } else if (node.right == null) {
                set(node, node.left);
            } else {
                Node min = minNode(node.right);
                node.data = min.data;
                remove(min);
            }
        }
        size--;
    }

    public void set(Node old, Node node) {
        if (old == root) {
            root = node;
        } else {
            if (old.parent.right == old) {
                old.parent.right = node;
            } else {
                old.parent.left = node;
            }
        }
        if (node != null) {
            node.parent = old.parent;
        }
    }

    public Node minNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public Node search(Node node, T data) {
        if (data.compareTo(node.data) < 0) {
            if (node.left == null) {
                return null;
            }
            return search(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            if (node.right == null) {
                return null;
            }
            return search(node.right, data);
        }
        return node;
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>("level-order");
        tree.insert(3);
        tree.insert(2);
        tree.insert(5);
        tree.insert(0);
        tree.insert(1);
        tree.insert(4);
        tree.insert(6);
        System.out.println("Tree: " + tree);
        System.out.println("Size: " + tree.getSize());
        System.out.println("Height: " + tree.getHeight(tree.root));
        tree.remove(1);
        System.out.println("Removed 1. Tree now: " + tree);
        tree.remove(2);
        BinaryTree<Integer>.Node oldRoot = tree.root;
        System.out.println("Removed 2. Tree now: " + tree);
        tree.remove(tree.root);
        System.out.println("Removed root. Tree now: " + tree);
        tree.set(tree.root, oldRoot);
        System.out.println("Size: " + tree.getSize());
        System.out.println("Empty: " + tree.isEmpty());
        System.out.println("Height: " + tree.getHeight(tree.root));
    }

}
