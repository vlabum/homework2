package ru.vlabum.alg;

public class MyChainingHashMap<Key, Value> {
    private int M = 7;
    private int size = 0;
    private Object[] st = new Object[M];

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("The key can't be null");
        }
        int i = hash(key);
        Node x = (Node) st[i];
        while (x != null) {
            if (key.equals(x.key)) {
                return x.value;
            }
            x = x.next;
        }
        return null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Value remove(Key key) {
        int i = hash(key);
        Node node = (Node) st[i];
        st[i] = null;
        size--;
        return node.value;
    }

    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("The key can't be null");
        }
        int i = hash(key);
        Node x = (Node) st[i];
        while (x != null) {
            if (key.equals(x.key)) {
                x.value = value;
                return;
            }
            x = x.next;
        }
        st[i] = new Node(key, value, (Node) st[i]);
        size++;
        if (size == M) { // нужно увеличивать
            if (!resize(M * 2)) {
                throw new IndexOutOfBoundsException("Достигнуто наибольшое количество элеменотов в коллекции.");
            }
        } else if (size <= M / 2) {
            resize(M / 2);
        }
    }

    private boolean resize(int capacity) {
        int target;
        target = SimpleNumbers.getNext(capacity);

        if (target == -1) { // не нашли следующего числа
            return false;
        }

        M = target;

        Object[] newSt = new Object[target];
        Object[] old = st;
        st = newSt;
        // далее нужно пройти по всему old и все объекты заново добавить уже в new, т.к. хеш будет у них уже другой
        for (Object o : old) {
            Node node = (Node) o;
            while (node != null) {
                put(node.key, node.value);
                node = node.next;
            }
        }
        return true;
    }

    private class Node {
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.next = next;
            this.value = value;
        }
    }

}