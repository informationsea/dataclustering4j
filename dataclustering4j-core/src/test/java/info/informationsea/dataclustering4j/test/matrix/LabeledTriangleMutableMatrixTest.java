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

import info.informationsea.dataclustering4j.matrix.LabeledTriangleMutableMatrix;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class LabeledTriangleMutableMatrixTest {
    @Test
    public void testKeys() {
        LabeledTriangleMutableMatrix<Integer, String> tmm = new LabeledTriangleMutableMatrix<Integer, String>(3, 1);
        tmm.setColumnKeys(Arrays.asList("A", "B", "C"));
        Assert.assertArrayEquals(new String[]{"A", "B", "C"}, tmm.getKeys().toArray());
        Assert.assertArrayEquals(new String[]{"A", "B", "C"}, tmm.getColumnKeys().toArray());
        Assert.assertArrayEquals(new String[]{"A", "B", "C"}, tmm.getRowKeys().toArray());
        tmm.setRowKeys(Arrays.asList("X", "Y", "Z"));
        Assert.assertArrayEquals(new String[]{"X", "Y", "Z"}, tmm.getKeys().toArray());
        Assert.assertArrayEquals(new String[]{"X", "Y", "Z"}, tmm.getColumnKeys().toArray());
        Assert.assertArrayEquals(new String[]{"X", "Y", "Z"}, tmm.getRowKeys().toArray());
        tmm.setKeys(Arrays.asList("1", "2", "3"));
        Assert.assertArrayEquals(new String[]{"1", "2", "3"}, tmm.getKeys().toArray());
        Assert.assertArrayEquals(new String[]{"1", "2", "3"}, tmm.getColumnKeys().toArray());
        Assert.assertArrayEquals(new String[]{"1", "2", "3"}, tmm.getRowKeys().toArray());
    }

    @Test
    public void testAt() {
        LabeledTriangleMutableMatrix<Integer, String> tmm = new LabeledTriangleMutableMatrix<Integer, String>(4, 1);
        tmm.setColumnKeys(Arrays.asList("A", "B", "C", "D"));

        Assert.assertEquals(Integer.valueOf(1), tmm.get("A", "B"));
    }

    @Test
    public void testSetAndAt() {
        LabeledTriangleMutableMatrix<Integer, String> tmm = new LabeledTriangleMutableMatrix<Integer, String>(4, 1);
        tmm.setColumnKeys(Arrays.asList("A", "B", "C", "D"));
        tmm.put("A", "A", 10);
        tmm.put("C", "D", 3);
        tmm.put("A", "C", 9);

        Assert.assertEquals(Integer.valueOf(10), tmm.get("A", "A"));
        Assert.assertEquals(Integer.valueOf(3), tmm.get("C", "D"));
        Assert.assertEquals(Integer.valueOf(9), tmm.get("A", "C"));
        Assert.assertEquals(Integer.valueOf(3), tmm.get("D", "C"));
        Assert.assertEquals(Integer.valueOf(9), tmm.get("C", "A"));
    }

    @Test
    public void testTranspose() {
        LabeledTriangleMutableMatrix<Integer, String> tmm = new LabeledTriangleMutableMatrix<Integer, String>(4, 1);
        tmm.setColumnKeys(Arrays.asList("A", "B", "C", "D"));
        tmm.put("A", "A", 10);
        tmm.put("C", "D", 3);
        tmm.put("A", "C", 9);
        tmm = (LabeledTriangleMutableMatrix<Integer, String>)tmm.transpose();

        Assert.assertEquals(Integer.valueOf(10), tmm.get("A", "A"));
        Assert.assertEquals(Integer.valueOf(3), tmm.get("C", "D"));
        Assert.assertEquals(Integer.valueOf(9), tmm.get("A", "C"));
        Assert.assertEquals(Integer.valueOf(3), tmm.get("D", "C"));
        Assert.assertEquals(Integer.valueOf(9), tmm.get("C", "A"));
    }

}
