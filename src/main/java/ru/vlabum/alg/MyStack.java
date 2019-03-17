package ru.vlabum.alg;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class MyStack<Item>  {

    private Object[] stack = new Object[1];
    private int size = 0;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void resize(int capacity) {
        Object[] newStack = new Object[capacity];
        System.arraycopy(stack, 0, newStack, 0, size);
        stack = newStack;
    }

    public void push(Item item) {
        if (size == stack.length) {
            resize(2 * stack.length);
        }
        stack[size++] = item;
    }

    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        Item item = (Item) stack[--size];
        if (size == stack.length / 4 && size > 0) {
            resize(stack.length / 2);
        }
        return item;
    }

    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        return (Item) stack[size-1];
    }

    public static <Item> boolean less(Item item1, Item item2, Comparator<Item> cmp) {
        return cmp.compare(item1, item2) < 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = size - 1; i >= 0; i--) {
            sb.append(stack[i]);
            sb.append(", ");
        }
        return sb.toString();
    }

}
