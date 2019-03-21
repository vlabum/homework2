package ru.vlabum.alg;

import java.util.LinkedList;

public class DepthFirstPath {

    private boolean[] marked;
    ;
    private int[] edgeTo;
    private int[] distTo;
    private int source;

    public DepthFirstPath(Graph g, int source, Method method) {
        if (source < 0) {
            throw new IllegalArgumentException();
        }
        if (source >= g.vertexCount()) {
            throw new IndexOutOfBoundsException();
        }
        this.source = source;
        edgeTo = new int[g.vertexCount()];
        distTo = new int[g.vertexCount()];
        marked = new boolean[g.vertexCount()];
        if (method == Method.cycle) {
            dfs2(g, source);
        } else {
            dfs(g, source);
        }
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        for (int w : g.adjList(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                distTo[w] = distTo[v] + 1;
                dfs(g, w);
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

    /**
     * обход в глибину с помощью стека без рекурсии
     */
    private void dfs2(Graph g, int v) {
        LinkedList<Integer> stack = new LinkedList<>();
        marked[v] = true;
        stack.push(v);
        while (!stack.isEmpty()) {
            int vertex = stack.peek(); // сначала берем с вершины
            int nextVertex = -1;
            for (int w : g.adjList(vertex)) {
                if (!marked[w]) {
                    nextVertex = w;
                    break;
                }
            }
            if (nextVertex > -1) { // имеется не посещенная смежная вершина
                marked[nextVertex] = true; // пометим ее
                edgeTo[nextVertex] = vertex; // укажем откуда пришли
                distTo[nextVertex] = distTo[vertex] + 1; // укажем дистанцию
                stack.push(nextVertex); // поместим ее в стек
            } else {
                stack.pop(); // иначе, уберем вершину из стека
            }
        }
    }

    public enum Method {rec, cycle}

}