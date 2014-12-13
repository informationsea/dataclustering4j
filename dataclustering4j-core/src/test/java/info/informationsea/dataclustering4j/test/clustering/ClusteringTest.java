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

package info.informationsea.dataclustering4j.test.clustering;

import info.informationsea.dataclustering4j.clustering.impl.ClusterBranch;
import info.informationsea.dataclustering4j.clustering.impl.ClusterLeaf;
import info.informationsea.dataclustering4j.clustering.ClusterNode;
import info.informationsea.dataclustering4j.clustering.Clustering;
import info.informationsea.dataclustering4j.distance.DistanceMatrixMaker;
import info.informationsea.dataclustering4j.distance.DoubleEuclidDistance;
import info.informationsea.dataclustering4j.linkage.CompleteLinkage;
import info.informationsea.dataclustering4j.matrix.DefaultMutableMatrix;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;

public class ClusteringTest {
    private DefaultMutableMatrix<Double> original;
    private DistanceMatrixMaker.DistanceMatrix distanceMatrix;

    @Before
    public void setUp() throws IOException {
        original = new DefaultMutableMatrix<Double>(new Double[]{
                0., 0.,
                1., 0.,
                1., 0.5,
                1., 3.,
                1.0, 4.0,
                1.5, 3.0}, 2);
        distanceMatrix = DistanceMatrixMaker.matrixDistance(original, new DoubleEuclidDistance());
    }

    private HashSet<Integer> array2hashSet(int[] values) {
        HashSet<Integer> hs = new HashSet<Integer>();
        for (int i : values) {
            hs.add(i);
        }
        return hs;
    }

    @Test
    public void testClustering() {
        ClusterNode tree = Clustering.doClustering(distanceMatrix, new CompleteLinkage());

        Assert.assertEquals(4.123106, tree.distance(), 0.000001);
        Assert.assertArrayEquals(new Integer[]{0, 1, 2}, tree.getChildren()[0].leafIndexes().toArray());
        Assert.assertArrayEquals(new Integer[]{4, 3, 5}, tree.getChildren()[1].leafIndexes().toArray());

        Assert.assertEquals(1.118034, tree.getChildren()[0].distance(), 0.000001);
        Assert.assertArrayEquals(new Integer[]{0}, tree.getChildren()[0].getChildren()[0].leafIndexes().toArray());

        Assert.assertEquals(0, tree.getChildren()[0].getChildren()[0].distance(), 0);
        Assert.assertEquals(0.5, tree.getChildren()[0].getChildren()[1].distance(), 0);
        Assert.assertTrue(tree.getChildren()[0].getChildren()[0].isLeaf());
        Assert.assertFalse(tree.getChildren()[0].getChildren()[1].isLeaf());

        Assert.assertEquals(0, tree.getChildren()[1].getChildren()[0].distance(), 0);
        Assert.assertEquals(0.5, tree.getChildren()[1].getChildren()[1].distance(), 0);
    }
}
