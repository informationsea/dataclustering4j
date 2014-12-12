package info.informationsea.dataclustering4j.test.matrix;

import info.informationsea.dataclustering4j.matrix.DefaultLabeledMutableMatrix;
import info.informationsea.dataclustering4j.matrix.LabeledMappedMatrix;
import info.informationsea.dataclustering4j.matrix.MappedMatrix;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * dataclustering4j
 * Copyright (C) 2014 Yasunobu OKAMURA
 * Created at 14/12/09
 */
public class LabeledMappedMatrixTest {

    DefaultLabeledMutableMatrix<Integer, String, String> m_dlmm;
    LabeledMappedMatrix<Integer, String, String> m_matrix;

    @Before
    public void initialized() {
        m_dlmm = new DefaultLabeledMutableMatrix<Integer, String, String>(new Integer[]{1, 2, 3, 4}, 2);
        m_dlmm.setColumnKeys(Arrays.asList("A", "B"));
        m_dlmm.setRowKeys(Arrays.asList("1", "2"));

        m_matrix = new LabeledMappedMatrix<Integer, String, String>(m_dlmm, new MappedMatrix.MapValue<Integer>() {
            @Override
            public Integer map(Integer obj) {
                return obj * 2;
            }
        });
    }

    @Test
    public void testLabeledMappedMatrix() {
        Assert.assertEquals(2, m_matrix.get("1", "A"), 0);
        Assert.assertEquals(8, m_matrix.get("2", "B"), 0);

        Assert.assertArrayEquals(new String[]{"A", "B"}, m_matrix.getColumnKeys().toArray());
        Assert.assertArrayEquals(new String[]{"1", "2"}, m_matrix.getRowKeys().toArray());
    }

    @Test
    public void testLabeledMappedMatrix2() {
        m_matrix.setColumnKeys(Arrays.asList("X", "Y"));
        m_matrix.setRowKeys(Arrays.asList("X", "Y"));
        Assert.assertEquals(2, m_matrix.get("X", "X"), 0);
        Assert.assertEquals(8, m_matrix.get("Y", "Y"), 0);
    }

    @Test
    public void testLabeledMappedMatrix3() {
        m_matrix.put(0, 0, 10);
        Assert.assertEquals(20, m_matrix.get(0, 0), 0);

        m_matrix.put("1", "B", 40);
        Assert.assertEquals(80, m_matrix.get("1", "B"), 0);
    }
}
