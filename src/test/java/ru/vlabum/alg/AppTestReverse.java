package ru.vlabum.alg;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class AppTestReverse {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { new String[]{"Привет"},                new String[]{"тевирП"} },
                { new String[]{"Пока"},                  new String[]{"акоП"} },
                { new String[]{"Hello"},                 new String[]{"olleH"} },
                { new String[]{"арозаупаланалапуазора"}, new String[]{"арозаупаланалапуазора"} }
        });
    }

    private String[] sourceArray;
    private String[] expectedArray;

    public AppTestReverse(final String[] expectedArray, final String[] sourceArray) {
        this.sourceArray= sourceArray;
        this.expectedArray = expectedArray;
    }

    @Test
    public void testReverse() {
        String str = App.reverseString(sourceArray[0]);
        Assert.assertEquals(expectedArray[0], str);
    }

}
