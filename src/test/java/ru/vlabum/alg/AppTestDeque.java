package ru.vlabum.alg;

import org.junit.Assert;
import org.junit.Test;

public class AppTestDeque {

    @Test
    public void test1() {
        MyDeque<Integer> dq = new MyDeque<>();
        dq.enqueueRight(1);
        dq.enqueueRight(2);
        dq.enqueueRight(3);
        dq.enqueueLeft(0);
        dq.enqueueLeft(-1);
        dq.enqueueRight(4);
        dq.dequeueLeft();
        dq.dequeueRight();
        int[] actual = new int[dq.size()];
        int[] expect = {0, 1, 2, 3};
        for (int i = 0; i < actual.length; i++) {
            actual[i] = dq.dequeueLeft();
        }
        Assert.assertArrayEquals(expect, actual);
    }

}
