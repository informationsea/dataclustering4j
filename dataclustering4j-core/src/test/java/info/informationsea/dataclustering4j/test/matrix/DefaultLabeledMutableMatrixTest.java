/**
 *  dataclustering4j
 *  Copyright (C) 2014 Yasunobu OKAMURA
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package info.informationsea.dataclustering4j.test.matrix;

import info.informationsea.dataclustering4j.matrix.DefaultLabeledMutableMatrix;
import info.informationsea.dataclustering4j.matrix.DoubleIdentityMatrix;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class DefaultLabeledMutableMatrixTest {

    DefaultLabeledMutableMatrix<Integer, String, String> m1 = null;

    @Before
    public void prepare() {
        m1 = new DefaultLabeledMutableMatrix<Integer, String, String>(new Integer[]{1, 2, 3, 4, 5, 6}, 2);
        m1.setRowKeys(Arrays.asList("X", "Y", "Z"));
        m1.setColumnKeys(Arrays.asList("A", "B"));
    }

    @Test
    public void testConstructor1() {
        DefaultLabeledMutableMatrix<Integer, String, String> m2 = new DefaultLabeledMutableMatrix<Integer, String, String>(m1);
        Assert.assertEquals(Integer.valueOf(1), m2.get("X", "A"));
        Assert.assertEquals(Integer.valueOf(4), m2.get("Y", "B"));
        Assert.assertEquals(Integer.valueOf(6), m2.get("Z", "B"));
    }

    @Test
    public void testConstructor2() {
        DoubleIdentityMatrix dim = new DoubleIdentityMatrix(3);

        DefaultLabeledMutableMatrix<Double, String, String> m2 = new DefaultLabeledMutableMatrix<Double, String, String>(dim);
        Assert.assertEquals(Double.valueOf(1), m2.get(0, 0));
        Assert.assertEquals(Double.valueOf(0), m2.get(0, 1));
        Assert.assertEquals(Double.valueOf(0), m2.get(1, 0));
    }

    @Test
    public void testConstructor3() {
        DefaultLabeledMutableMatrix<Integer, String, String> m2 = new DefaultLabeledMutableMatrix<Integer, String, String>(m1, 1, 3, 0, 2);
        Assert.assertEquals(Integer.valueOf(4), m2.get("Y", "B"));
        Assert.assertEquals(Integer.valueOf(6), m2.get("Z", "B"));
    }

    @Test
    public void testConstructor4() {
        DefaultLabeledMutableMatrix<Integer, String, String> m1 = new DefaultLabeledMutableMatrix<Integer, String, String>(new Integer[]{1, 2, 3, 4, 5, 6}, 2);

        DefaultLabeledMutableMatrix<Integer, String, String> m2 = new DefaultLabeledMutableMatrix<Integer, String, String>(m1, 1, 3, 0, 2);
        Assert.assertEquals(new DefaultLabeledMutableMatrix<Integer, String, String>(new Integer[]{3, 4, 5, 6}, 2), m2);
    }

    @Test
    public void testKeys() {
        m1.setRowKeys(null);
        m1.setColumnKeys(null);

        Assert.assertNull(m1.getRowKeys());
        Assert.assertNull(m1.getColumnKeys());
    }

    @Test
    public void testSetKeys() {
        boolean exceptionRaised = false;
        try {
            m1.setColumnKeys(Arrays.asList(new String[]{}));
        } catch (IllegalArgumentException iae) {
            exceptionRaised = true;
        }
        Assert.assertTrue(exceptionRaised);

        exceptionRaised = false;
        try {
            m1.setRowKeys(Arrays.asList(new String[]{}));
        } catch (IllegalArgumentException iae) {
            exceptionRaised = true;
        }
        Assert.assertTrue(exceptionRaised);
    }

    @Test
    public void testLabelGet() {
        Assert.assertEquals(Integer.valueOf(1), m1.get("X", "A"));
        Assert.assertEquals(Integer.valueOf(4), m1.get("Y", "B"));
        Assert.assertEquals(Integer.valueOf(6), m1.get("Z", "B"));
    }

    @Test
    public void testLabelPut() {
        m1.put("X", "A", 10);
        m1.put("Y", "B", 11);
        m1.put("Z", "B", 12);

        Assert.assertEquals(Integer.valueOf(10), m1.get("X", "A"));
        Assert.assertEquals(Integer.valueOf(11), m1.get("Y", "B"));
        Assert.assertEquals(Integer.valueOf(12), m1.get("Z", "B"));

    }

    @Test
    public void testTranspose() {
        DefaultLabeledMutableMatrix<Integer, String, String> m2 = (DefaultLabeledMutableMatrix<Integer, String, String>)m1.transpose();
        Assert.assertEquals(Integer.valueOf(1), m2.get("A", "X"));
        Assert.assertEquals(Integer.valueOf(4), m2.get("B", "Y"));
        Assert.assertEquals(Integer.valueOf(6), m2.get("B", "Z"));
    }

    @Test
    public void testGetRow() {
        Assert.assertArrayEquals(new Integer[]{1, 2}, m1.getRow("X"));
        Assert.assertArrayEquals(new Integer[]{3, 4}, m1.getRow("Y"));

        Integer[] array = new Integer[2];
        m1.getRow(array, "Z");
        Assert.assertArrayEquals(new Integer[]{5, 6}, array);
    }

    @Test
    public void testGetColumn() {
        Assert.assertArrayEquals(new Integer[]{1, 3, 5}, m1.getColumn("A"));
        Assert.assertArrayEquals(new Integer[]{2, 4, 6}, m1.getColumn("B"));

        Integer[] array = new Integer[3];
        m1.getColumn(array, "A");
        Assert.assertArrayEquals(new Integer[]{1, 3, 5}, array);
    }

}
