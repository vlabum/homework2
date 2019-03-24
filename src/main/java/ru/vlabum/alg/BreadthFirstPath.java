package ru.vlabum.alg;

import java.util.LinkedList;

public class BreadthFirstPath {
    private static final int INF = Integer.MAX_VALUE;
    private int[] edgeTo;
    private boolean[] marked;
    private int[] distTo;
    private int source;

    public BreadthFirstPath(Graph g, int source) {
        if (source < 0) {
            throw new IllegalArgumentException();
        }
        if (source >= g.vertexCount()) {
            throw new IndexOutOfBoundsException();
        }
        marked = new boolean[g.vertexCount()];
        edgeTo = new int[g.vertexCount()];
        distTo = new int[g.vertexCount()];
        this.source = source;
        for (int i = 0; i < distTo.length; i++) {
            distTo[i] = INF;
        }
        bfs(g, source);
    }

    private void bfs(Graph g, int source) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(source);
        marked[source] = true;
        distTo[source] = 0;

        while (!queue.isEmpty()) {
            int vertex = queue.removeFirst();
            for (int w :
                    g.adjList(vertex)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = vertex;
                    distTo[w] = distTo[vertex] + 1;
                    queue.addLast(w);
                }
            }
        }
    }

    public boolean hasPathTo(int dist) {
        if (dist < 0) {
            throw new IllegalArgumentException();
        }
        if (dist >= marked.length) {
            throw new IndexOutOfBoundsException();
        }
        return marked[dist];
    }

    public LinkedList pathTo(int dist) {
        if (!hasPathTo(dist)) {
            return null;
        }
        LinkedList<Integer> stack = new LinkedList<>();

        int vertex = dist;
        while (vertex != source) {
            stack.push(vertex);
            vertex = edgeTo[vertex];
        }
        return stack;
    }

    public int distTo(int dist) {
        if (dist < 0) {
            throw new IllegalArgumentException();
        }
        if (dist >= marked.length) {
            throw new IndexOutOfBoundsException();
        }
        return distTo[dist];
    }
}
