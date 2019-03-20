package ru.vlabum.alg;

import java.util.NoSuchElementException;

public class MyTreeMap<Key extends Comparable<Key>, Value> {

    private Node root = null;

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.size;
    }

    public int height() {
        return height(root);
    }

    public int height(Key key) {
        return getNode(key).height;
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private Node getNode(Key key) {
        return getNode(root, key);
    }

    private Node getNode(Node node, Key key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node;
        }
        if (cmp < 0) {
            return getNode(node.left, key);
        } else {
            return getNode(node.right, key);
        }
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        return getNode(node, key).value;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void put(Key key, Value value) { //a[key] = value
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        if (node == null) {
            return new Node(key, value, 1, 0);
        }

        int cmp = key.compareTo(node.key);
        if (cmp == 0) { // если ключ совпал, то просто обновим значение
            node.value = value;
        } else if (cmp < 0) { // новый ключ меньше текущего узла => идем влево
            node.left = put(node.left, key, value);
        } else { // новый ключ больше текущего узла => идем вправо
            node.right = put(node.right, key, value);
        }

        node.size = size(node.left) + size(node.right) + 1;
        // выбрать максимальную высоту поддеревьев + 1
        node.height = (height(node.left) > height(node.right) ? height(node.left) : height(node.right)) + 1;
        return node;
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        } else {
            return min(node.left);
        }
    }

    public Value min() {
        return min(root).value;
    }

    private Node max(Node node) {
        if (node.right == null) {
            return node;
        } else {
            return max(node.right);
        }
    }

    public Value max() {
        return max(root).value;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            return node.right;
        } else {
            node.left = removeMin(node.left);
        }
        node.size = size(node.left) + size(node.right) + 1;
        // выбрать максимальную высоту поддеревьев + 1
        node.height = (height(node.left) > height(node.right) ? height(node.left) : height(node.right)) + 1;
        return node;
    }

    public void removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Tree is empty");
        }
        root = removeMin(root);
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            return node.left;
        } else {
            node.right = removeMax(node.right);
        }
        node.size = size(node.left) + size(node.right) + 1;
        // выбрать максимальную высоту поддеревьев + 1
        node.height = (height(node.left) > height(node.right) ? height(node.left) : height(node.right)) + 1;
        return node;
    }

    public void removeMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("Tree is empty");
        }
        root = removeMax(root);
    }

    public void remove(Key key) {
        root = remove(root, key);
    }

    private Node remove(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = remove(node.left, key);
        } else if (cmp > 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            Node tmp = node;
            node = max(node.left);
            node.left = removeMax(tmp.left);
            node.right = tmp.right;
            tmp = null;
        }
        node.size = size(node.left) + size(node.right) + 1;
        // выбрать максимальную высоту поддеревьев + 1
        node.height = (height(node.left) > height(node.right) ? height(node.left) : height(node.right)) + 1;
        return node;
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null) { // пустое дерево считаем сбалансированным
            return true;
        }
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        boolean isBalanced = Math.abs(leftHeight - rightHeight) <= 1;
        // дерево сбалансировано, если высота его поддеревьев различается не более, чем на 1, и поддеревья тоже сбалансированы
        return isBalanced && isBalanced(node.left) && isBalanced(node.right);
    }

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int size; //кол-во узлов в дереве (поддереве)
        int height; //высота дерева (поддерева)

        public Node(Key key, Value value, int size, int height) {
            this.key = key;
            this.value = value;
            this.size = size;
            this.height = height;
        }

    }

}