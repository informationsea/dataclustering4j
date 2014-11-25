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

import info.informationsea.java.dataclustering.matrix.DoubleIdentityMatrix;
import info.informationsea.java.dataclustering.matrix.Matrix;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

public class DoubleIdentityMatrixTest {

    private final int SIZE = 10;
    private DoubleIdentityMatrix m;

    @Before
    public void setUp() {
        m = new DoubleIdentityMatrix(SIZE);
    }

    @Test
    public void testSize() {
        Assert.assertArrayEquals(new int[]{SIZE, SIZE}, m.getSize());
    }

    @Test
    public void testAt() {
        Assert.assertEquals(0.0, m.at(0, 1), 0);
        Assert.assertEquals(1.0, m.at(2, 2), 0);
    }

    @Test
    public void testGetRow() {
        final int TEST_ROW = 3;
        Double[] result = new Double[SIZE];
        for (int i = 0; i < SIZE; i++) {
            result[i] = Double.valueOf(0);
        }
        result[TEST_ROW] = Double.valueOf(1.0);

        Assert.assertArrayEquals(result, m.getRow(TEST_ROW));
    }

    @Test
    public void testGetRow2() {
        final int TEST_ROW = 3;
        Double[] expected = new Double[SIZE];
        for (int i = 0; i < SIZE; i++) {
            expected[i] = Double.valueOf(0);
        }
        expected[TEST_ROW] = Double.valueOf(1.0);

        Double[] array = new Double[SIZE];
        m.getRow(array, TEST_ROW);

        Assert.assertArrayEquals(expected, array);
    }

    @Test
    public void testEqual() {
        Matrix<Double> ma[] = new DoubleIdentityMatrix[]{
                new DoubleIdentityMatrix(13),
                new DoubleIdentityMatrix(13),
                new DoubleIdentityMatrix(16)
        };

        Assert.assertTrue(ma[0].equals(ma[1]));
        Assert.assertFalse(ma[1].equals(ma[2]));
        Assert.assertFalse(ma[0].equals(ma[2]));
    }

    @Test
    public void testEqual2() {
        Matrix<Double> ma[] = new DoubleIdentityMatrix[]{
                new DoubleIdentityMatrix(13),
                new DoubleIdentityMatrix(13),
                new DoubleIdentityMatrix(16)
        };

        Assert.assertFalse(ma[0].equals(new Object()));
        Assert.assertFalse(ma[1].equals(new Object()));
        Assert.assertFalse(ma[2].equals(new Object()));
    }

    @Test
    public void testTranspose() {
        Matrix<Double> t = m.transpose();
        Assert.assertTrue(t.equals(m));
        Assert.assertTrue(m.equals(t));
    }
}
