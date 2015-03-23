/*
 *  dataclustering4j
 *  Copyright (C) 2015 Yasunobu OKAMURA
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

package info.informationsea.dataclustering4j.largematrix;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AbstractLargeMatrixTest {

    AbstractLargeMatrix<Integer, String, String> matrix;

    @Before
    public void tearUp() throws Exception {
        matrix = new AbstractLargeMatrix<Integer, String, String>(2, 3) {
            @Override
            protected int getItemSize() {
                return 1;
            }

            @Override
            public void put(int row, int col, Integer value) {

            }

            @Override
            public Integer get(int row, int col) {
                return row*(col-1);
            }

            @Override
            public int calculateArrayPosition(int col, int row) {
                return super.calculateArrayPosition(col, row);
            }
        };
    }

    @Test
    public void testCalculateArrayPosition() {
        Assert.assertEquals(0, matrix.calculateArrayPosition(0, 0));
        Assert.assertEquals(1, matrix.calculateArrayPosition(0, 1));
        Assert.assertEquals(2, matrix.calculateArrayPosition(0, 2));
        Assert.assertEquals(3, matrix.calculateArrayPosition(1, 0));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testCalculateArrayPositionException1() {
        matrix.calculateArrayPosition(0, 3);
    }
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testCalculateArrayPositionException2() {
        matrix.calculateArrayPosition(2, 0);
    }


    @Test
    public void testGetSize() throws Exception {
        Assert.assertArrayEquals(new int[]{3, 2}, matrix.getSize());
    }

    @Test
    public void testGetRow() throws Exception {
        Assert.assertArrayEquals(new Integer[]{-1, 0}, matrix.getRow(1));
    }

    @Test
    public void testGetColumn() throws Exception {
        Assert.assertArrayEquals(new Integer[]{0, -1, -2}, matrix.getColumn(0));
    }

    @Test
    public void testTranspose() throws Exception {
        Assert.assertArrayEquals(new Integer[]{-1, 0}, matrix.transpose().getColumn(1));
        Assert.assertArrayEquals(new Integer[]{0, -1, -2}, matrix.transpose().getRow(0));
        Assert.assertArrayEquals(new int[]{2, 3}, matrix.transpose().getSize());
        Assert.assertEquals(-1, (int)matrix.transpose().get(0, 1));
        Assert.assertEquals(matrix, matrix.transpose().transpose());
    }
}