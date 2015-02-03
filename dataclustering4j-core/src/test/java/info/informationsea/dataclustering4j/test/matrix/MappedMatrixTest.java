package info.informationsea.dataclustering4j.test.matrix;

import info.informationsea.dataclustering4j.matrix.DefaultMutableMatrix;
import info.informationsea.dataclustering4j.matrix.MappedMatrix;
import info.informationsea.dataclustering4j.matrix.Matrix;
import org.junit.Assert;
import org.junit.Test;

/**
 * dataclustering4j
 * Copyright (C) 2014 Yasunobu OKAMURA
 * Created at 14/12/09
 */
public class MappedMatrixTest {
    @Test
    public void testMappedMatrix() {
        DefaultMutableMatrix<Integer> dmmi = new DefaultMutableMatrix<Integer>(new Integer[]{1, 2, 3, 4, 5, 6}, 3);

        MappedMatrix<Integer> mappedMatrix = new MappedMatrix<Integer>(dmmi, new MappedMatrix.MapValue<Integer>() {
            @Override
            public Integer map(Integer obj) {
                return obj*2;
            }
        });

        Assert.assertArrayEquals(new int[]{2, 3}, mappedMatrix.getSize());
        Assert.assertEquals(4, mappedMatrix.get(0, 1), 0);
        Assert.assertEquals(12, mappedMatrix.get(1, 2), 0);
        Assert.assertArrayEquals(new Integer[]{2, 4, 6}, mappedMatrix.getRow(0));
        Assert.assertArrayEquals(new Integer[]{2, 8}, mappedMatrix.getColumn(0));

        Matrix<Integer> transposedMatrix = mappedMatrix.transpose();

        Assert.assertArrayEquals(new int[]{3, 2}, transposedMatrix.getSize());
        Assert.assertEquals(4, transposedMatrix.get(1, 0), 0);
        Assert.assertArrayEquals(new Integer[]{2, 8}, transposedMatrix.getRow(0));
        Assert.assertArrayEquals(new Integer[]{2, 4, 6}, transposedMatrix.getColumn(0));
    }
}
