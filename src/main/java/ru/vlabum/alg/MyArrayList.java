package ru.vlabum.alg;

import java.util.Comparator;
import java.util.Iterator;

public class MyArrayList<Item> implements Iterable<Item> {

    private Object[] arr = new Object[1];

    private int size = 0;

    public int getSize() {
        return size;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
    }

    public void add(Item item) {
        if (size == arr.length) {
            resize(2 * arr.length);
        }
        arr[size] = item;
        size++;
    }

    private void resize(int capacity) {
        Object[] newArr = new Object[capacity];
        System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
    }

    public void set(int index, Item item) {
        checkIndex(index);
        arr[index] = item;
    }

    public Item get(int index) {
        checkIndex(index);
        return (Item) arr[index];
    }

    public int indexOf(Item item) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(Item item) {
        return indexOf(item) != -1;
    }

    public boolean remove(Item item) {
        int i = 0;
        while (i < size && !arr[i].equals(item)) {
            i++;
        }
        if (i == size) {
            return false;
        }
        for (int j = i; j < size - 1; j++) {
            arr[j] = arr[j + 1];
        }
        arr[size - 1] = null;
        size--;
        if (size == arr.length / 4 && size > 0) {
            resize(arr.length / 2);
        }
        return true;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < size; i++) {
            s.append(arr[i]);
            s.append(", ");
        }
        return s.toString();
    }

    @Override
    public Iterator<Item> iterator() {
        return new MyArrayListIterator();
    }

    class MyArrayListIterator<Item> implements Iterator<Item> {
        int cursor = 0;
        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException();
            }
            Item item = (Item) arr[cursor];
            cursor++;
            return item;
        }

        @Override
        public void remove() {}

    }

    private void exch(int i, int j) {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private boolean less(Item item1, Item item2, Comparator<Item> cmp) {
        return cmp.compare(item1, item2) < 0;
    }

    public void selectionSort(Comparator<Item> cmp) {
        for (int i = 0; i < size - 1; i++) {
            int min = i;
            for (int j = i + 1; j < size; j++) {
                if (less((Item) arr[j], (Item) arr[min], cmp)) {
                    min = j;
                }
            }
            exch(i, min);
        }
    }

    public void insertionSort(Comparator<Item> cmp) {
        for (int i = 0; i < size; i++) {
            for (int j = i; j > 0; j--) {
                if (less((Item) arr[j], (Item) arr[j - 1], cmp)) {
                    exch(j, j - 1);
                }
                else {
                    break;
                }
            }

        }
    }

    public int binarySearch(Item item, Comparator<Item> cmp) {
        int low = 0;
        int high = size - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (cmp.compare(item, (Item) arr[mid]) < 0) {
                high = mid - 1;
            }
            if (cmp.compare(item, (Item) arr[mid]) > 0) {
                low = mid + 1;
            }
            else {
                return mid;
            }
        }
        return -1;
    }

}
