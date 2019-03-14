package ru.vlabum.alg;

import org.junit.Assert;
import org.junit.Test;

public class AppTestHanoy {

    @Test
    public void hanoyRec() {
        MyStack<Integer> from = new MyStack<>();
        MyStack<Integer> buf = new MyStack<>();
        MyStack<Integer> to = new MyStack<>();
        from.push(5);
        from.push(4);
        from.push(3);
        from.push(2);
        from.push(1); // Вершина стека
        int[] expected = {1, 2, 3, 4, 5};
        System.out.print("from:\t");
        System.out.println(from);
        App.hanoyR(from, buf, to);
        int sizeTo = to.size();
        int[] actual = new int[sizeTo];
        System.out.print("to:  \t");
        System.out.println(to);
        for (int i = 0; i < sizeTo; i++) {
            actual[i] = to.pop();
        }
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void hanoyCircle() {
        MyStack<Integer> from = new MyStack<>();
        MyStack<Integer> buf = new MyStack<>();
        MyStack<Integer> to = new MyStack<>();
        from.push(5);
        from.push(4);
        from.push(3);
        from.push(2);
        from.push(1); // Вершина стека
        int[] expected = {1, 2, 3, 4, 5};
        System.out.print("from:\t");
        System.out.println(from);
        App.hanoyC(from, buf, to, new IntegerComparator());
        int sizeTo = to.size();
        int[] actual = new int[sizeTo];
        System.out.print("to:  \t");
        System.out.println(to);
        for (int i = 0; i < sizeTo; i++) {
            actual[i] = to.pop();
        }
        Assert.assertArrayEquals(expected, actual);
    }

}
