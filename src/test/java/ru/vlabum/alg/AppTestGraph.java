package ru.vlabum.alg;

import org.junit.Assert;
import org.junit.Test;

public class AppTestGraph {

    @Test
    public void testGraph() {
        Graph g = new Graph(6);
        g.addEdge(0, 2);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(5, 3);
        g.addEdge(3, 4);
        g.addEdge(3, 2);
        g.addEdge(4, 2);
        g.addEdge(5, 0);

        DepthFirstPath dfp = new DepthFirstPath(g, 0, DepthFirstPath.Method.cycle);
        System.out.println(dfp.pathTo(5).toString());

        DepthFirstPath dfpR = new DepthFirstPath(g, 0, DepthFirstPath.Method.rec);
        System.out.println(dfpR.pathTo(5).toString());

        Assert.assertArrayEquals(dfp.pathTo(1).toArray(), dfpR.pathTo(1).toArray());
        Assert.assertArrayEquals(dfp.pathTo(2).toArray(), dfpR.pathTo(2).toArray());
        Assert.assertArrayEquals(dfp.pathTo(3).toArray(), dfpR.pathTo(3).toArray());
        Assert.assertArrayEquals(dfp.pathTo(4).toArray(), dfpR.pathTo(4).toArray());
        Assert.assertArrayEquals(dfp.pathTo(5).toArray(), dfpR.pathTo(5).toArray());
    }

}
