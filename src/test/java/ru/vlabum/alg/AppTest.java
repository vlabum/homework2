package ru.vlabum.alg;

import static org.junit.Assert.assertTrue;

import com.sun.org.apache.xpath.internal.operations.UnaryOperation;
import org.junit.Test;

import java.util.Random;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private static final int COUNT_ITEMS = 30000; // количество элементов
    private static final int COUNT_REPEAT = 10; // количество повторений
    private static final int FROM = 0; // нижняя граница значений
    private static final int TO = COUNT_ITEMS - 5; // верхняя граница значений

    MyArrayList<Integer> arrayListInt;

    private void initArray() {
        this.arrayListInt = new MyArrayList<Integer>();
        Random rnd = new Random();
        for (int i = 0; i < COUNT_ITEMS; i++) {
            arrayListInt.add(rnd.nextInt(TO - FROM) + FROM);
        }
    }

    @Test
    public void selectionSortTest() {
        System.out.println(String.format("Start Сортировка выбором %d элементов",COUNT_ITEMS));
        for (int i = 0; i < COUNT_REPEAT; i++) {
            initArray();
            long start = System.currentTimeMillis();
            arrayListInt.selectionSort(new IntegerComparator());
            long time = System.currentTimeMillis() - start;
            System.out.printf("Took %.3f%n", time/1e3);
        }
    }

    @Test
    public void insertionSort() {
        System.out.println(String.format("Start Сортировка вставками %d элементов",COUNT_ITEMS));
        for (int i = 0; i < COUNT_REPEAT; i++) {
            initArray();
            long start = System.currentTimeMillis();
            arrayListInt.insertionSort(new IntegerComparator());
            long time = System.currentTimeMillis() - start;
            System.out.printf("Took %.3f%n", time/1e3);
        }
    }

}
