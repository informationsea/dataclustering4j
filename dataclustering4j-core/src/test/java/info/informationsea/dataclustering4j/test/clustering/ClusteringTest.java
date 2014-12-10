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

import info.informationsea.dataclustering4j.clustering.ClusterBranch;
import info.informationsea.dataclustering4j.clustering.ClusterLeaf;
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

public class ClusteringTest {
    private DefaultMutableMatrix<Double> original;
    private DistanceMatrixMaker.DistanceMatrix distanceMatrix;

    @Before
    public void setUp() throws IOException {
        original = new DefaultMutableMatrix<Double>(new Double[]{0., 0., 1., 0., 1., 0.5, 1., 3., 1.0, 4.0, 1.5, 3.0}, 2);
        distanceMatrix = DistanceMatrixMaker.matrixDistance(original, new DoubleEuclidDistance());
    }

    @Test
    public void testClustering() {
        ClusterNode tree = Clustering.doClustering(distanceMatrix, new CompleteLinkage());

        ClusterNode expected = new ClusterBranch(
                new ClusterBranch(new ClusterLeaf(0), new ClusterBranch(new ClusterLeaf(1), new ClusterLeaf(2))),
                new ClusterBranch(new ClusterLeaf(4), new ClusterBranch(new ClusterLeaf(3), new ClusterLeaf(5))));
        Assert.assertEquals(expected, tree);
    }
}
