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

package info.informationsea.dataclustering4j.matrix.largematrix;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IntegerLargeMatrixTest {

    IntegerLargeMatrix<String, String> matrix;

    @Before
    public void setup() throws Exception {
        matrix = new IntegerLargeMatrix<String, String>(4, 3, 0);
    }

    @Test
    public void testPutGet() throws Exception {
        int[] size = matrix.getSize();
        for (int i = 0; i < size[0]; i++) {
            for (int j = 0; j < size[1]; j++) {
                Assert.assertEquals(0., matrix.get(i, j), 0.0000001);
                matrix.put(i, j, i+j);
                Assert.assertEquals(i+j, matrix.get(i, j), 0.0000001);
            }
        }
        for (int i = 0; i < size[0]; i++) {
            for (int j = 0; j < size[1]; j++) {
                Assert.assertEquals(i+j, matrix.get(i, j), 0.0000001);
            }
        }
    }

    @Test
    public void testGetItemSize() throws Exception {
        Assert.assertEquals(4, matrix.getItemSize());
    }
}