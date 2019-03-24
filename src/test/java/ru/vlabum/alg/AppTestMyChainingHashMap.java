package ru.vlabum.alg;

import org.junit.Assert;
import org.junit.Test;

public class AppTestMyChainingHashMap {

    @Test
    public void testChaining() {
        MyChainingHashMap<Integer, Integer> hm = new MyChainingHashMap<>();
        hm.put(1, 1);
        hm.put(2, 1);
        hm.put(3, 1);
        hm.put(4, 1);
        hm.put(5, 1);
        hm.put(6, 1);
        hm.put(7, 1);
        hm.put(8, 8);
        hm.put(9, 1);
        hm.put(10, 1);
        hm.put(11, 1);
        Assert.assertEquals(8L, (long) hm.get(8));
    }

    @Test
    public void testLinearProbbing() {
        MyLinearProbbingHashMap<String, Integer> hm = new MyLinearProbbingHashMap<>();
        hm.put("one", 1);
        hm.put("two", 1);
        hm.put("three", 1);
        hm.put("four", 1);
        hm.put("five", 1);
        hm.put("six", 1);
        hm.put("seven", 7);
        hm.put("eight", 8);
        hm.put("nine", 9);
        hm.put("ten", 1);
        hm.put("eleven", 1);
        hm.put("twelve", 1);
        hm.put("13teen", 1);
        hm.put("14teen", 1);
        Assert.assertEquals(9L, (long) hm.get("nine"));
    }
}
