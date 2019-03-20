package ru.vlabum.alg;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class AppTestTree {

    private static int COUNT_TREES = 200;

    @Test
    public void testTreeBalance() {
        MyTreeMap<Integer, Integer> tree = new MyTreeMap<>();
        tree.put(12, 0);
        tree.put(13, 0);
        tree.put(4, 0);
        tree.put(2, 0);
        tree.put(1, 0);
        tree.put(3, 0);
        tree.put(8, 0);
        tree.put(6, 0);
        tree.put(10, 0);
        tree.put(5, 0);
        tree.put(7, 0);
        tree.put(9, 0);
        tree.put(11, 0);
        tree.remove(8);
        Assert.assertFalse(tree.isBalanced());
        // уравновесим вручную
        tree.remove(5);
        tree.remove(9);
        tree.remove(11);
        tree.put(14, 0);
        tree.put(15, 0);
        tree.put(16, 0);
        tree.remove(13);
        tree.put(13, 0); // чтобы к 14 цеплялся, а не наоборот (балансировку пока не изучал)
        Assert.assertTrue(tree.isBalanced());
    }

    @Test
    public void testFullBalanced() {
        MyTreeMap<Integer, Integer> tree = new MyTreeMap<>();
        int[] arr = {
                16,
                8, 24,
                4, 12, 20, 28,
                2, 6, 10, 14, 18, 22, 26, 30,
                1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31};
        for (int i = 0; i < arr.length; i++) {
            tree.put(arr[i], 0);
        }
        Assert.assertTrue(tree.isBalanced());
    }

    /**
     * В общем ни одного сбалансированного дерева пока не получил.
     * Все сильно зависит не только от равномерного распределения, но и от последовательности вставки.
     */
    @Test
    public void testRandomTreesIsBalanced() {
        int countBalance = 0;
        for (int i = 0; i < COUNT_TREES; i++) {
            MyTreeMap<Integer, Integer> tree = getRandomTreeH6();
            countBalance = countBalance + (tree.isBalanced() ? 1 : 0);
        }
        System.out.println(String.format("Number of balanced trees %d", countBalance));
    }

    private MyTreeMap<Integer, Integer> getRandomTreeH6() {
        MyTreeMap<Integer, Integer> tree = new MyTreeMap<>();
        Random rnd = new Random();
        while (tree.height() < 6) {
            int k = rnd.nextInt(201) - 100;
            tree.put(k, 0);
            System.out.print(k + " ");
        }
        System.out.println();
        return tree;
    }

}
