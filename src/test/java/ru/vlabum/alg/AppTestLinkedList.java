package ru.vlabum.alg;

import org.junit.Assert;
import org.junit.Test;

public class AppTestLinkedList {

    @Test
    public void testStack() {
        MyLinkedList<Integer> stack = new MyLinkedList<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        Integer[] arr = new Integer[stack.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = stack.pop();
        }
        Assert.assertArrayEquals(new Integer[]{5, 4, 3, 2, 1}, arr);
    }

    @Test
    public void testQueue() {
        MyLinkedList<Integer> queue = new MyLinkedList<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        Integer[] arr = new Integer[queue.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = queue.dequeue();
        }
        Assert.assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    public void testDeque() {
        MyLinkedList<Integer> deque = new MyLinkedList<>();
        deque.addRight(1);
        deque.addRight(2);
        deque.addRight(3);
        deque.addLeft(4);
        deque.addLeft(5);
        Integer[] arr = new Integer[deque.size()];
        int i = 0;
        for (Integer item : deque) {
            arr[i++] = item;
        }
        Assert.assertArrayEquals(new Integer[]{5, 4, 1, 2, 3}, arr);
    }

    @Test
    public void testLinkedList() {
        MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(4);
        linkedList.addLast(5);
        linkedList.add(2, 3);
        linkedList.add(3, 8);
        linkedList.add(3, 8);
        linkedList.remove(8);
        Integer[] arr = new Integer[linkedList.size()];
        int i = 0;
        for (Integer item : linkedList) {
            arr[i++] = item;
        }
        Assert.assertArrayEquals(new Integer[]{1, 2, 3, 8, 4, 5}, arr);
        Assert.assertEquals(8L, (long) linkedList.get(3));
        linkedList.set(3, 7);
        Assert.assertEquals(7L, (long) linkedList.get(3));
        Assert.assertEquals(3L, (long) linkedList.indexOf(7));
    }

}
