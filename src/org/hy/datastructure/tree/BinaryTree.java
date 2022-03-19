package org.hy.datastructure.tree;

import org.hy.datastructure.queue.Queue;
import org.hy.datastructure.stack.Stack;

import java.util.Iterator;

public class BinaryTree<T> implements Iterable<T> {
    private int size;
    private Node root;
    private Traversal traversal;
    private String traversalName;

    class Node {
        T data;
        Node left;
        Node right;
        Node parent;

        public Node(T data, Node left, Node right, Node parent) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public Node(T data) {
            this.data = data;
            left = right = parent = null;
        }
    }

    public BinaryTree(String traversal) {
        root = null;
        size = 0;
        setTraversal(traversal);
    }

    public int getSize() {
        return size;
    }

    public int getHeight(Node node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + Math.max(getHeight(node.left), getHeight(node.right));
        }
    }

    public String getTraversal() {
        return traversalName;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int search(T data) {
        setTraversal(traversalName);
        int index = 0;
        while (traversal.hasNext()) {
            T current = traversal.next().data;
            if (current != data) {
                index++;
            } else {
                return index;
            }
        }
        return -1;
    }

    public void insert(T data) {
        Node node = new Node(data);
        Traversal i = new LevelOrder();
        if (size == 0) {
            root = node;
            size++;
        } else {
            while (i.hasNext()) {
                Node current = i.next();
                if (current.left == null) {
                    current.left = node;
                    node.parent = current;
                    size++;
                    break;
                } else if (current.right == null) {
                    current.right = node;
                    node.parent = current;
                    size++;
                    break;
                }
            }
        }
    }

    public void remove(T data) {
        if (search(data) == -1) {
            throw new Error(data + " isn't in the tree.");
        }
        if (size == 1 & root.data == data) {
            root = null;
            size--;
        } else {
            Traversal i = new LevelOrder();
            Node deepest = root;
            while (i.hasNext()) {
                deepest = i.next();
            }
            Traversal j = new LevelOrder();
            while (j.hasNext()) {
                Node current = j.next();
                if (current.data == data) {
                    current.data = deepest.data;
                    if (deepest.parent.left == deepest) {
                        deepest.parent.left = null;
                    } else {
                        deepest.parent.right = null;
                    }
                    size--;
                    break;
                }
            }
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "Empty.";
        } else {
            StringBuilder sb = new StringBuilder();
            for (T data : this) {
                sb.append(data).append(", ");
            }
            sb.replace(sb.length() - 2, sb.length() - 1, ".");
            return sb.toString();
        }
    }

    //Traversal
    public void setTraversal(String traversal) {
        traversalName = traversal;
        switch (traversal) {
            case "in-order" -> this.traversal = new InOrder();
            case "pre-order" -> this.traversal = new PreOrder();
            case "post-order" -> this.traversal = new PostOrder();
            case "level-order" -> this.traversal = new LevelOrder();
            default -> throw new Error("Only between \"in-order\", \"pre-order\", \"post-order\" and \"level-order\".");
        }
    }

    @Override
    public Iterator<T> iterator() {
        setTraversal(traversalName);
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return traversal.hasNext();
            }

            @Override
            public T next() {
                return traversal.next().data;
            }
        };
    }

    abstract class Traversal {
        abstract public boolean hasNext();

        abstract public Node next();
    }


    class InOrder extends Traversal {
        Stack<Node> stack = new Stack<>(size);
        Node current = root;

        InOrder() {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Node next() {
            Node ret = null;
            if (current == null) {
                current = stack.pop();
                ret = current;
                current = current.right;
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }

            }
            return ret;
        }
    }

    class PostOrder extends Traversal {
        Stack<Node> stack = new Stack<>(size);
        Node current;

        PostOrder() {
            pushLoop(root);
        }

        void pushLoop(Node node) {
            while (node != null) {
                if (node.right != null) {
                    stack.push(node.right);
                }
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Node next() {
            current = stack.pop();
            if (!stack.isEmpty()) {
                while (current.right != null && stack.peek() == current.right) {
                    current = stack.pop();
                    stack.push(current.parent);
                    pushLoop(current);
                    current = stack.pop();
                }
            }
            return current;
        }
    }

    class PreOrder extends Traversal {
        Stack<Node> stack;
        Node current = root;

        PreOrder() {
            stack = new Stack<>(size);
            stack.push(current);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Node next() {
            current = stack.pop();
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
            return current;
        }
    }

    class LevelOrder extends Traversal {
        Queue<Node> queue = new Queue<>(size + 1);

        LevelOrder() {
            queue.enqueue(root);
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public Node next() {
            Node current = queue.dequeue();
            if (current.left != null) {
                queue.enqueue(current.left);
            }
            if (current.right != null) {
                queue.enqueue(current.right);
            }
            return current;
        }
    }

    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>("in-order");
        System.out.println("Size: " + tree.getSize());
        System.out.println("Empty: " + tree.isEmpty());
        System.out.println("Traversal: " + tree.getTraversal());
        for (int i = 1; i <= 7; i++) {
            System.out.println("Inserted " + i);
            tree.insert(i);
        }
        System.out.println("Size: " + tree.getSize());
        System.out.println("Empty: " + tree.isEmpty());
        System.out.println("Height: " + tree.getHeight(tree.root));
        System.out.println("Tree in " + tree.traversalName + ": " + tree);
        System.out.println("Search 1 in " + tree.traversalName + ": " + tree.search(1));
        tree.setTraversal("post-order");
        System.out.println("Tree in " + tree.traversalName + ": " + tree);
        System.out.println("Search 1 in " + tree.traversalName + ": " + tree.search(1));
        tree.setTraversal("pre-order");
        System.out.println("Tree in " + tree.traversalName + ": " + tree);
        System.out.println("Search 1 in " + tree.traversalName + ": " + tree.search(1));
        tree.setTraversal("level-order");
        System.out.println("Tree in " + tree.traversalName + ": " + tree);
        System.out.println("Search 1 in " + tree.traversalName + ": " + tree.search(1));
        System.out.println("Search null: " + tree.search(null));
        tree.remove(1);
        System.out.println("Removed 1. Tree in " + tree.traversalName + ": " + tree);
        tree.remove(6);
        System.out.println("Removed 6. Tree in " + tree.traversalName + ": " + tree);
        tree.remove(3);
        System.out.println("Removed 3. Tree in " + tree.traversalName + ": " + tree);
        tree.remove(2);
        System.out.println("Removed 2. Tree in " + tree.traversalName + ": " + tree);
        tree.remove(4);
        System.out.println("Removed 4. Tree in " + tree.traversalName + ": " + tree);
        tree.remove(5);
        System.out.println("Removed 5. Tree in " + tree.traversalName + ": " + tree);
        tree.remove(7);
        System.out.println("Removed 7. Tree in " + tree.traversalName + ": " + tree);
        System.out.println("Empty: " + tree.isEmpty());
    }
}




