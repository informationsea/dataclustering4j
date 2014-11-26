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

package info.informationsea.java.dataclustering.test.matrix;

import info.informationsea.java.dataclustering.matrix.Matrix;
import info.informationsea.java.dataclustering.matrix.TriangleMutableMatrix;
import org.junit.Assert;
import org.junit.Test;

public class TriangleMutableMatrixTest {

    @Test
    public void testAt() {
        TriangleMutableMatrix<Integer> tmm = new TriangleMutableMatrix<Integer>(4, 1);
        Assert.assertEquals(Integer.valueOf(1), tmm.get(1, 3));
    }

    @Test
    public void testSetAndAt() {
        TriangleMutableMatrix<Integer> tmm = new TriangleMutableMatrix<Integer>(4, 1);
        tmm.put(1, 1, 10);
        tmm.put(1, 2, 3);
        tmm.put(3, 1, 9);

        Assert.assertEquals(Integer.valueOf(10), tmm.get(1, 1));
        Assert.assertEquals(Integer.valueOf(3), tmm.get(1, 2));
        Assert.assertEquals(Integer.valueOf(9), tmm.get(3, 1));
        Assert.assertEquals(Integer.valueOf(3), tmm.get(2, 1));
        Assert.assertEquals(Integer.valueOf(9), tmm.get(1, 3));
    }

    @Test
    public void testGetSize() {
        TriangleMutableMatrix<Integer> tmm = new TriangleMutableMatrix<Integer>(4, 1);
        Assert.assertArrayEquals(new int[]{4, 4}, tmm.getSize());
    }

    @Test
    public void testTranspose() {
        TriangleMutableMatrix<Integer> tmm = new TriangleMutableMatrix<Integer>(4, 1);
        tmm.put(1, 1, 10);
        tmm.put(1, 2, 3);
        tmm.put(3, 1, 9);

        Matrix<Integer> t = tmm.transpose();
        Assert.assertEquals(Integer.valueOf(10), t.get(1, 1));
        Assert.assertEquals(Integer.valueOf(3), t.get(1, 2));
        Assert.assertEquals(Integer.valueOf(9), t.get(3, 1));
        Assert.assertEquals(Integer.valueOf(3), t.get(2, 1));
        Assert.assertEquals(Integer.valueOf(9), t.get(1, 3));
    }

    @Test
    public void testEqual() {
        TriangleMutableMatrix<Integer> tmm1 = new TriangleMutableMatrix<Integer>(4, 1);
        tmm1.put(1, 1, 10);
        tmm1.put(1, 2, 3);
        tmm1.put(3, 1, 9);

        TriangleMutableMatrix<Integer> tmm2 = new TriangleMutableMatrix<Integer>(4, 1);
        tmm2.put(1, 1, 10);
        tmm2.put(2, 1, 3);
        tmm2.put(1, 3, 9);

        TriangleMutableMatrix<Integer> tmm3 = new TriangleMutableMatrix<Integer>(4, 0);
        TriangleMutableMatrix<Integer> tmm4 = new TriangleMutableMatrix<Integer>(3, 1);

        Assert.assertEquals(tmm1, tmm2);

        Assert.assertFalse(tmm1.equals(tmm3));
        Assert.assertFalse(tmm1.equals(tmm4));
        Assert.assertFalse(tmm1.equals(this));
    }

    @Test
    public void testGetRow() {
        TriangleMutableMatrix<Integer> tmm1 = new TriangleMutableMatrix<Integer>(4, 1);
        tmm1.put(1, 1, 10);
        tmm1.put(1, 2, 3);
        tmm1.put(3, 1, 9);

        Integer[] correctRow = new Integer[]{1, 10, 3, 9};
        //printArrayHelper(tmm1.getRow(2));
        //printArrayHelper(tmm1.getRow(1));
        Assert.assertArrayEquals(correctRow, tmm1.getRow(1));
    }

    public void printArrayHelper(Object[] array) {
        System.err.print("[");
        for (int i = 0; i < array.length; i++) {
            System.err.print(array[i]);
            if (i != array.length-1)
                System.err.print(", ");
        }
        System.err.println("]");
    }
}
