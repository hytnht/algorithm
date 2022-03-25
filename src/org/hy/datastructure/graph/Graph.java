package org.hy.datastructure.graph;

import org.hy.datastructure.linkedlist.SinglyLinkedList;
import org.hy.datastructure.queue.Queue;
import org.hy.datastructure.stack.Stack;

import java.util.Iterator;

public class Graph<T> {
    SinglyLinkedList<Vertex> vlist;
    int size;
    String traversal;
    T root;

    class Vertex {
        T data;
        SinglyLinkedList<Vertex> adj;

        public Vertex(T data) {
            this.data = data;
            adj = new SinglyLinkedList<>();
        }

        public void addAdj(Vertex v) {
            if (adj.search(v) != -1) {
                throw new Error("Edge " + data + " - " + v.data + " is existed.");
            }
            adj.insert(v);
        }

        public void removeAdj(Vertex v) {
            adj.remove(v);
        }
    }

    public Graph() {
        vlist = new SinglyLinkedList<>();
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public void addVertex(Vertex v) {
        vlist.insert(v);
        size++;
    }

    public int search(T data) {
        for (int i = 0; i < vlist.getSize(); i++) {
            Vertex current = vlist.get(i);
            if (current.data == data) {
                return i;
            }
        }
        return -1;
    }

    public Vertex getVertex(T data) {
        for (int i = 0; i < vlist.getSize(); i++) {
            Vertex current = vlist.get(i);
            if (current.data == data) {
                return current;
            }
        }
        return null;
    }

    public void addEdge(T src, T dst, boolean bidirect) {
        Vertex v1 = new Vertex(src);
        Vertex v2 = new Vertex(dst);
        Vertex s1 = getVertex(src);
        Vertex s2 = getVertex(dst);
        if (s1 == null) {
            addVertex(v1);
            s1 = v1;
        }
        if (s2 == null) {
            addVertex(v2);
            s2 = v2;
        }
        s1.addAdj(s2);
        if (bidirect) {
            s2.addAdj(s1);
        }
    }

    public void removeEdge(T src, T dst, boolean bidirect) {
        Vertex s1 = getVertex(src);
        Vertex s2 = getVertex(dst);
        if (s1 == null) {
            throw new Error(src + " isn't in the graph.");
        } else {
            s1.removeAdj(s2);
        }
        if (s2 == null) {
            throw new Error(src + " isn't in the graph.");
        } else {
            if (bidirect) {
                s2.removeAdj(s1);
            }
        }
    }

    //Traversal
    public Iterator<T> BFS(T data) {
        traversal = "BFS";
        root = data;
        Queue<Vertex> queue = new Queue<>(size);
        boolean[] visited = new boolean[size];
        visited[search(data)] = true;
        queue.enqueue(getVertex(data));
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return !queue.isEmpty();
            }

            @Override
            public T next() {
                Vertex current = queue.dequeue();
                for (Vertex i : current.adj) {
                    int adjIndex = search(i.data);
                    if (!visited[adjIndex]) {
                        visited[adjIndex] = true;
                        queue.enqueue(i);
                    }
                }
                return current.data;
            }
        };
    }

    public Iterator<T> DFS(T data) {
        traversal = "DFS";
        root = data;
        Stack<Vertex> stack = new Stack<>(size);
        boolean[] visited = new boolean[size];
        visited[search(data)] = true;
        stack.push(getVertex(data));
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return !stack.isEmpty();
            }

            @Override
            public T next() {
                Vertex current = stack.pop();
                for (Vertex i : current.adj) {
                    int adjIndex = search(i.data);
                    if (!visited[adjIndex]) {
                        visited[adjIndex] = true;
                        stack.push(i);
                    }
                }
                return current.data;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<T> iter;
        switch (traversal) {
            case "BFS" -> iter = BFS(root);
            case "DFS" -> iter = DFS(root);
            default -> throw new Error("Set traversal first");
        }
        while (iter.hasNext()) {
            sb.append(iter.next()).append(" - ");
        }
        sb.replace(sb.length() - 3, sb.length() - 1, ".");
        return sb.toString();
    }

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>();
        graph.addEdge(1,3,true);
        graph.addEdge(5,2,true);
        graph.addEdge(1,4,true);
        graph.addEdge(4,6,true);
        graph.addEdge(4,2,true);
        graph.addEdge(6,2,true);
        graph.BFS(1);
        System.out.println("BFS 1: " + graph);
        graph.DFS(1);
        System.out.println("DFS 1: " + graph);
        System.out.println("Size: " + graph.getSize());
        graph.removeEdge(2, 4, true);
        graph.BFS(1);
        System.out.println("BFS 1: " + graph);
        graph.DFS(1);
        System.out.println("DFS 1: " + graph);
    }
}
