package ru.vlabum.alg;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<Item> implements Iterable<Item> {

    private Node first = null;
    private Node last = null;
    private int size = 0;

    @Override
    public Iterator<Item> iterator() {
        return new MyLinkedListIterator();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Item getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        return first.item;
    }

    public void addFirst(Item item) {
        Node oldFirst = first;
        first = new Node(null, item, oldFirst);
        if (isEmpty()) {
            last = first;
        } else {
            oldFirst.prev = first;
        }
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }

        Node second = first.next;
        Item item = first.item;
        first.item = null;
        first.next = null;
        first = second;
        size--;
        if (isEmpty()) {
            last = null;
        } else {
            second.prev = null;
        }
        return item;
    }

    public Item getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        return last.item;
    }

    public void addLast(Item item) {
        Node oldLast = last;
        last = new Node(oldLast, item, null);
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        size++;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        Item item = last.item;
        Node previous = last.prev;
        last.prev = null;
        last = previous;
        size--;
        if (isEmpty()) {
            first = null;
        } else {
            last.next = null;
        }
        return item;
    }

    private Node getNodeByIndex(int index) {
        if (size / 2 >= index) {
            return getNodeByIndexAsc(index);
        }
        return getNodeByIndexDesc(index);
    }

    private Node getNodeByIndexAsc(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        int currentIndex = 0;
        Node current = first;
        while (currentIndex < index) {
            current = current.next;
            currentIndex++;
        }
        return current;
    }

    private Node getNodeByIndexDesc(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        int currentIndex = size - 1;
        Node current = last;
        while (currentIndex > 0) {
            current = current.prev;
            currentIndex--;
        }
        return current;
    }

    public Item get(int index) {
        return getNodeByIndex(index).item;
    }

    public void set(int index, Item item) {
        getNodeByIndex(index).item = item;
    }

    public int indexOf(Item item) {
        Node current = first;
        int currentIndex = 0;
        while (current != null) {
            if (current.item == null && item == null)
                break;
            if (current.item != null && current.item.equals(item))
                break;
            current = current.next;
            currentIndex++;
        }
        return current != null ? currentIndex : -1;
    }

    public boolean contains(Item item) {
        return indexOf(item) > -1;
    }

    public Item remove(Item item) {
        Node current = first;

        while (current != null) {
            if (current.item == null && item == null)
                break;
            if (current.item != null && current.item.equals(item))
                break;
            current = current.next;
        }
        if (current == null) {
            return null;
        }
        if (current == first) {
            return removeFirst();
        }
        if (current == last) {
            return removeLast();
        }
        Node next = current.next;
        Node previous = current.prev;
        previous.next = next;
        next.prev = previous;
        size--;
        current.prev = null;
        current.next = null;
        return current.item;
    }

    public void add(int index, Item item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            addFirst(item);
            return;
        } else if (index == size) {
            addLast(item);
            return;
        }

        int currentIndex = 0;
        Node current = first;
        while (currentIndex < index) {
            current = current.next;
            currentIndex++;
        }
        Node newNode = new Node(current.prev, item, current);
        Node previous = current.prev;
        previous.next = newNode;
        current.prev = newNode;
        size++;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        Node current = first;
        while (current != null) {
            s.append(current.item.toString());
            s.append(", ");
            current = current.next;
        }
        return s.toString();
    }

    /**
     * Stack method
     *
     * @param item
     */
    public void push(Item item) {
        addLast(item);
    }

    /**
     * Stack method
     */
    public Item pop() {
        return removeLast();
    }

    /**
     * Stack method
     */
    public Item peek() {
        return getLast();
    }

    /**
     * Deque method
     */
    public void addLeft(Item item) {
        addFirst(item);
    }

    /**
     * Deque method
     */
    public Item getLeft() {
        return removeFirst();
    }

    /**
     * Deque method
     */
    public Item peekLeft() {
        return getFirst();
    }

    /**
     * Deque method
     */
    public void addRight(Item item) {
        addLast(item);
    }

    /**
     * Deque method
     */
    public Item getRight() {
        return removeLast();
    }

    /**
     * Deque method
     */
    public Item peekRight() {
        return getLast();
    }

    /**
     * Queue method
     */
    public void enqueue(Item item) {
        addLast(item);
    }

    /**
     * Queue method
     */
    public Item dequeue() {
        return removeFirst();
    }

    /**
     * Queue method
     */
    public Item peekQueue() {
        return getFirst();
    }

    private class MyLinkedListIterator implements Iterator<Item> {

        Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
        }
    }

    private class Node {
        Item item;
        Node next;
        Node prev;

        public Node(Node prev, Item item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }
}