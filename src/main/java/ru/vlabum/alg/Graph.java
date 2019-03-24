package ru.vlabum.alg;

import java.util.LinkedList;

public class Graph {
    private int vertexCount;
    private int edgeCount;
    private LinkedList<Integer>[] adjLists;

    public Graph(int vertexCount) {
        if (vertexCount < 0) {
            throw new IllegalArgumentException("The number of vertices can not be negative.");
        }
        this.vertexCount = vertexCount;
        adjLists = new LinkedList[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            adjLists[i] = new LinkedList<>();
        }
    }

    public int vertexCount() {
        return vertexCount;
    }

    public int edgeCount() {
        return edgeCount;
    }

    public void addEdge(int v1, int v2) {
        if (v1 < 0 || v2 < 0) {
            throw new IllegalArgumentException("Vertex number cannot be negative.");
        }
        if (v1 >= vertexCount || v2 >= vertexCount) {
            throw new IndexOutOfBoundsException("No vertex with this number.");
        }

        adjLists[v1].add(v2);
        adjLists[v2].add(v1);
        edgeCount++;
    }

    public LinkedList<Integer> adjList(int vertex) {
        return adjLists[vertex];
    }
}
