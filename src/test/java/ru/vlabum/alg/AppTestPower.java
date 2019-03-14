package ru.vlabum.alg;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class AppTestPower {

    private long base;
    private long pow;

    public AppTestPower(final long base, final long pow) {
        this.base = base;
        this.pow = pow;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {2L, 7L},
                {3L, 9L},
                {4L, 4L},
                {5L, 8L}
        });
    }

    @Test
    public void testPower() {
        long actual = App.powFast(base, pow);
        long expected = (long) Math.pow((double) base, (double) pow);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testPowerRec() {
        long actual = App.powRec(base, pow);
        long expected = (long) Math.pow((double) base, (double) pow);
        Assert.assertEquals(expected, actual);
    }

}
