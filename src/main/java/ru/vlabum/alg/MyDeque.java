package ru.vlabum.alg;

import java.util.NoSuchElementException;

public class MyDeque<Item> {

    private Object[] queue = new Object[1];
    private int size = 0;
    private int left = queue.length - 1; // куда будет записан новый элемент слева
    private int right = 0; // куда будет записан новый элемент справа

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void resize(int capacity) {
        Object[] newQueue = new Object[capacity];
        for (int i = 0; i < size; i++) {
            newQueue[i] = queue[(left + 1 + i) % queue.length];
        }
        queue = newQueue;
        left = queue.length - 1;
        right = size;
    }

    public void enqueueRight(Item item) {
        if (size == queue.length) {
            resize(2 * queue.length);
        }
        queue[right++] = item;
        right %= queue.length;
        size++;
    }

    public void enqueueLeft(Item item) {
        if (size == queue.length) {
            resize(2 * queue.length);
        }
        queue[left--] = item;
        if (left == -1) {
            left = queue.length - 1;
        }
        size++;
    }

    public Item dequeueRight() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (right == 0) {
            right = queue.length;
        }
        Item item = (Item) queue[--right];
        size--;
        if (size == queue.length / 4 && size > 0) {
            resize(queue.length / 2);
        }
        return item;
    }

    public Item dequeueLeft() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        left = (left + 1) % queue.length;
        Item item = (Item) queue[left];
        size--;
        if (size == queue.length / 4 && size > 0) {
            resize(queue.length / 2);
        }
        return item;
    }

    public Item peekRight() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        int i = right == 0 ? queue.length - 1 : right - 1;
        return (Item) queue[i];
    }

    public Item peekLeft() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return (Item) queue[(left + 1) % queue.length];
    }

}
