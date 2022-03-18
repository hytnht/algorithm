package org.hy.datastructure.tree;

import org.hy.datastructure.queue.Queue;
import org.hy.datastructure.stack.Stack;

import java.util.Iterator;

public class BinaryTree<T> implements Iterable<T> {
    private int size;
    private Node root;
    private Iterator<Node> traversal;
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
        if (isEmpty()) {
            throw new Error("Empty");
        }
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    public String getTraversal() {
        return traversalName;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int search(T data) {
        Iterator<Node> i = traversal;
        int index = 0;
        while (i.hasNext()) {
            T current = i.next().data;
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
        Iterator<Node> i = levelOrder();
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
            throw new Error(data + "isn't in the tree.");
        }
        Iterator<Node> i = levelOrder();
        Node deepest = root;
        while (i.hasNext()) {
            deepest = i.next();
        }
        Iterator<Node> j = levelOrder();
        while (j.hasNext()) {
            Node current = j.next();
            if (current.data == data) {
                current.data = deepest.data;
                break;
            }
        }
        deepest.parent = null;
    }

    @Override
    public String toString() {
        if (size == 0) {
            System.out.println("Empty.");
        }
        StringBuilder sb = new StringBuilder();
        for (T data : this) {
            sb.append(data).append(", ");
            sb.replace(sb.length() - 2, sb.length() - 1, ".");
        }
        return sb.toString();
    }

    //Traversal
    public void setTraversal(String traversal) {
        traversalName = traversal + " order.";
        switch (traversal) {
            case "in" -> this.traversal = inOrder();
            case "pre" -> this.traversal = preOrder();
            case "post" -> this.traversal = postOrder();
            case "level" -> this.traversal = levelOrder();
            default -> throw new Error("Only between \"in\", \"pre\", \"post\" and \"level\" order");
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            final Iterator<Node> i = traversal;

            @Override
            public boolean hasNext() {
                return i.hasNext();
            }

            @Override
            public T next() {
                return i.next().data;
            }
        };
    }

    public Iterator<Node> inOrder() {
        return new Iterator<Node>() {
            private Stack<Node> stack = new Stack<>(size);
            Node current = root;
            while (current != null) {
                stack.push(current);
                current = current.left;
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
        };
    }

    public Iterator<Node> postOrder() {
        return new Iterator<Node>() {
            Stack<Node> stack = new Stack<>(size);
            stack.push(root.right);
            stack.push(root);
            Node current = root.left;

            @Override
            public boolean hasNext() {
                return !stack.isEmpty();
            }

            @Override
            public Node next() {
                Node ret = null;
                while (current != null) {
                    stack.push(current.right);
                    stack.push(current);
                    current = current.left;
                }
                current = stack.pop();
                if (current.right != null && stack.peek() == current.right) {
                    current = stack.pop();
                    stack.push(current.parent);
                } else {
                    ret = current;
                    current = null;
                }
                return ret;
            }
        };
    }

    public Iterator<Node> preOrder() {
        return new Iterator<Node>() {
            private Stack<Node> stack;
            Node current = root;

            public void preOrder() {
                stack = new Stack<>(size);
                stack.push(current);
            }

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Node next() {
                current = stack.pop();
                Node ret = current;
                stack.push(current.right);
                stack.push(current.left);
                return ret;
            }
        };
    }

    public Iterator<Node> levelOrder() {
        return new Iterator<Node>() {
            Queue<Node> queue = new Queue<>(size);
            Node current = root;
            queue.enqueue(root);

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Node next() {
                Node ret = current;
                if (current.left != null) {
                    queue.enqueue(current.left);
                }
                if (current.right != null) {
                    queue.enqueue(current.right);
                }
                current = queue.dequeue();
                return ret;
            }
        };
    }

    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>("in");
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
        System.out.println("Tree in " + tree.traversalName + tree);
        System.out.println("Search 1 in " + tree.traversalName + tree.search(1));
        tree.setTraversal("post");
        System.out.println("Tree in " + tree.traversalName + tree);
        System.out.println("Search 1 in " + tree.traversalName + tree.search(1));
        tree.setTraversal("pre");
        System.out.println("Tree in " + tree.traversalName + tree);
        System.out.println("Search 1 in " + tree.traversalName + tree.search(1));
        tree.setTraversal("level");
        System.out.println("Tree in " + tree.traversalName + tree);
        System.out.println("Search 1 in " + tree.traversalName + tree.search(1));
        System.out.println("Search null: " + tree.search(null));
        tree.remove(1);
        System.out.println("Removed 1. Tree in " + tree.traversalName + tree);
        tree.remove(6);
        System.out.println("Removed 6. Tree in " + tree.traversalName + tree);
        tree.remove(3);
        System.out.println("Removed 3. Tree in " + tree.traversalName + tree);
        tree.remove(2);
        System.out.println("Removed 2. Tree in " + tree.traversalName + tree);
        tree.remove(4);
        System.out.println("Removed 4. Tree in " + tree.traversalName + tree);
        tree.remove(5);
        System.out.println("Removed 5. Tree in " + tree.traversalName + tree);
        tree.remove(7);
        System.out.println("Removed 7. Tree in " + tree.traversalName + tree);
        System.out.println("Empty: " + tree.isEmpty());
    }

}




