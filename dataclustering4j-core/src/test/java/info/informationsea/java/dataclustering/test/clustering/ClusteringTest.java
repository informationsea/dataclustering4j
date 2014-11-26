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

package info.informationsea.java.dataclustering.test.clustering;

import info.informationsea.java.dataclustering.clustering.ClusterBranch;
import info.informationsea.java.dataclustering.clustering.ClusterLeaf;
import info.informationsea.java.dataclustering.clustering.ClusterNode;
import info.informationsea.java.dataclustering.clustering.Clustering;
import info.informationsea.java.dataclustering.distance.DistanceMatrixMaker;
import info.informationsea.java.dataclustering.distance.DoubleEuclidDistance;
import info.informationsea.java.dataclustering.linkage.CompleteLinkage;
import info.informationsea.java.dataclustering.matrix.DefaultMutableMatrix;
import info.informationsea.java.dataclustering.test.matrix.CSVMatrixLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;

public class ClusteringTest {
    private DefaultMutableMatrix<Double> original;
    private DistanceMatrixMaker.DistanceMatrix distanceMatrix;

    @Before
    public void setUp() throws IOException {
        original = CSVMatrixLoader.loadDoubleMatrix(new InputStreamReader(CSVMatrixLoader.class.getResourceAsStream("../matrix1.csv")));
        distanceMatrix = new DistanceMatrixMaker<Double>().matrixDistance(original, new DoubleEuclidDistance());
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
