package ru.vlabum.alg;

public class MyLinearProbbingHashMap<Key, Value> {

    private static int CONSTANT_2HASH = 7;
    private int M = 13;
    private int size = 0;
    private Object[] keys = new Object[M];
    private Object[] values = new Object[M];

    private int size() {
        return size;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("The key can't be null");
        }
//        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
        int k = hash(key);
        for (int i = hash(key); keys[i] != null; i = (i + (CONSTANT_2HASH - (k % CONSTANT_2HASH))) % M) {
            if (((Key) keys[i]).equals(key)) {
                return (Value) values[i];
            }
        }
        return null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("The key can't be null");
        }
        if (size == M - 1) {
            if (!resize(M * 2)) {
                throw new IndexOutOfBoundsException("Достигнуто наибольшое количество элеменотов в коллекции.");
            }
        }
        int i;
//        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
        int k = hash(key);
        for (i = k; keys[i] != null; i = (i + (CONSTANT_2HASH - (k % CONSTANT_2HASH))) % M) {
            if (((Key) keys[i]).equals(key)) {
                values[i] = value;
                return;
            }
        }
        keys[i] = key;
        values[i] = value;
        size++;
    }

    private boolean resize(int capacity) {
        int target;
        target = SimpleNumbers.getNext(capacity);

        if (target == -1) { // не нашли следующего числа
            return false;
        }

        M = target;

        Object[] newKeys = new Object[M];
        Object[] newValues = new Object[M];
        Object[] oldKeys = keys;
        Object[] oldValues = values;
        keys = newKeys;
        values = newValues;
        // далее нужно пройти по всему old и все объекты заново добавить уже в new, т.к. хеш будет у них уже другой
        for (int i = 0; i < oldKeys.length - 1; i++) {
            if (oldKeys[i] != null)
                put((Key) oldKeys[i], (Value) oldValues[i]);
        }
        return true;
    }

}