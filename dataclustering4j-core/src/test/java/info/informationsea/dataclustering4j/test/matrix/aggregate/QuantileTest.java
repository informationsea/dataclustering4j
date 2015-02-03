package info.informationsea.dataclustering4j.test.matrix.aggregate;

import info.informationsea.dataclustering4j.matrix.aggregate.Median;
import info.informationsea.dataclustering4j.matrix.aggregate.Quantile;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * dataclustering4j
 * Copyright (C) 2015 Yasunobu OKAMURA
 * Created at 15/02/03
 */
public class QuantileTest {
    @Test
    public void testQuantile() {
        List<Number> list = new ArrayList<Number>();
        for (int i = 0; i < 10; i++)
            list.add(i);
        Assert.assertEquals(4.5, new Quantile(0.5).process(list), 0);
    }

    @Test
    public void testQuantile2() {
        List<Number> list = new ArrayList<Number>();
        list.add(1);
        list.add(10);
        list.add(12);
        list.add(20);
        list.add(16);

        Assert.assertEquals(12, new Quantile(0.5).process(list), 0);
    }

    @Test
    public void testQuantile3() {
        Assert.assertEquals(0.5, new Quantile(0.5).process(Arrays.asList(0, 1)), 0);
        Assert.assertEquals(0.1, new Quantile(0.1).process(Arrays.asList(0, 1)), 0);
        Assert.assertEquals(0.2, new Quantile(0.2).process(Arrays.asList(0, 1)), 0);
        Assert.assertEquals(0.3, new Quantile(0.3).process(Arrays.asList(0, 1)), 0);
        Assert.assertEquals(0.8, new Quantile(0.8).process(Arrays.asList(0, 1)), 0);
    }

}
