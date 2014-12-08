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

import info.informationsea.dataclustering4j.matrix.DefaultMutableMatrix;
import info.informationsea.dataclustering4j.matrix.DoubleIdentityMatrix;
import info.informationsea.dataclustering4j.matrix.Matrix;
import org.junit.Assert;
import org.junit.Test;

public class DefaultMutableMatrixTest {

    @Test
    public void testConstructor() {
        DoubleIdentityMatrix idm = new DoubleIdentityMatrix(2);
        DefaultMutableMatrix<Double> dmmd = new DefaultMutableMatrix<Double>(idm);
        Assert.assertEquals(Double.valueOf(1), dmmd.get(0, 0));
        Assert.assertEquals(Double.valueOf(0), dmmd.get(1, 0));
        Assert.assertEquals(Double.valueOf(0), dmmd.get(0, 1));
        Assert.assertEquals(Double.valueOf(1), dmmd.get(1, 1));
    }

    @Test
    public void testConstructor2() {
        boolean exceptionCatched = false;
        try {
            DefaultMutableMatrix<Double> dmmd = new DefaultMutableMatrix<Double>(new Double[]{1., 2., 3.}, 2);
        } catch (IllegalArgumentException iiae) {
            exceptionCatched = true;
        }
        Assert.assertTrue(exceptionCatched);
    }

    @Test
    public void testGet() {
        DefaultMutableMatrix<Integer> m1 = new DefaultMutableMatrix<Integer>(5, 6, 0);
        Assert.assertEquals(m1.get(2, 4), Integer.valueOf(0));
    }

    @Test
    public void testPut() {
        DefaultMutableMatrix<Integer> m1 = new DefaultMutableMatrix<Integer>(5, 6, 0);
        m1.put(2, 4, 1);
        Assert.assertEquals(m1.get(2, 4), Integer.valueOf(1));
        Assert.assertEquals(m1.get(2, 3), Integer.valueOf(0));
        Assert.assertEquals(m1.get(4, 2), Integer.valueOf(0));
    }



    @Test
    public void testGetRow() {
        DefaultMutableMatrix<Integer> m1 = new DefaultMutableMatrix<Integer>(5, 6, 0);
        m1.put(3, 0, 1);
        m1.put(3, 3, 6);

        Integer[] correct1 = new Integer[]{1, 0, 0, 6, 0, 0};
        Integer[] correct2 = new Integer[]{0, 0, 0, 0, 0, 0};

        Assert.assertArrayEquals(correct1, m1.getRow(3));
        Assert.assertArrayEquals(correct2, m1.getRow(4));
    }

    @Test
    public void testSize() {
        DefaultMutableMatrix<Integer> m1 = new DefaultMutableMatrix<Integer>(5, 6, 0);
        Assert.assertArrayEquals(new int[]{5, 6}, m1.getSize());

        DefaultMutableMatrix<Integer> m2 = new DefaultMutableMatrix<Integer>(1, 4, 0);
        Assert.assertArrayEquals(new int[]{1, 4}, m2.getSize());
    }

    @Test
    public void testTranspose() {
        DefaultMutableMatrix<Integer> m = new DefaultMutableMatrix<Integer>(2, 3, 0);
        m.put(0, 2, 1);
        m.put(1, 0, 2);
        Matrix<Integer> t = m.transpose();
        Assert.assertArrayEquals(new int[]{3, 2}, t.getSize());
        Assert.assertEquals(Integer.valueOf(1), t.get(2, 0));
        Assert.assertEquals(Integer.valueOf(2), t.get(0, 1));
        Assert.assertEquals(t.transpose(), m);
    }

    @Test
    public void testEqual() {
        DefaultMutableMatrix<Integer> m1 = new DefaultMutableMatrix<Integer>(2, 3, 0);
        m1.put(0, 2, 1);
        m1.put(1, 0, 2);
        DefaultMutableMatrix<Integer> m2 = new DefaultMutableMatrix<Integer>(2, 3, 0);
        DefaultMutableMatrix<Integer> m3 = new DefaultMutableMatrix<Integer>(2, 2, 1);
        DefaultMutableMatrix<Integer> m4 = new DefaultMutableMatrix<Integer>(2, 2, 1);
        DefaultMutableMatrix<Integer> m5 = new DefaultMutableMatrix<Integer>(3, 2, 1);

        Assert.assertFalse(m1.equals(m2));
        Assert.assertFalse(m1.equals(m3));
        Assert.assertFalse(m2.equals(m3));
        Assert.assertFalse(m3.equals(m5));
        Assert.assertTrue(m4.equals(m3));
        Assert.assertFalse(m2.equals(this));
    }

    @Test
    public void testToString() {
        DefaultMutableMatrix<Integer> m1 = new DefaultMutableMatrix<Integer>(2, 3, 0);
        m1.put(0, 2, 1);
        m1.put(1, 0, 2);
        String value = m1.toString();
        String expected = "Matrix[info.informationsea.dataclustering4j.matrix.DefaultMutableMatrix]\n" +
                "[0] 0, 0, 1\n" +
                "[1] 2, 0, 0\n";
        Assert.assertEquals(expected, value);
    }

}
