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

package info.informationsea.java.dataclustering.test.linkage;

import info.informationsea.java.dataclustering.clustering.ClusterNode;
import info.informationsea.java.dataclustering.distance.DistanceMatrixMaker;
import info.informationsea.java.dataclustering.distance.DoubleEuclidDistance;
import info.informationsea.java.dataclustering.linkage.CompleteLinkage;
import info.informationsea.java.dataclustering.linkage.Linkage;
import info.informationsea.java.dataclustering.matrix.DefaultMutableMatrix;
import info.informationsea.java.dataclustering.test.matrix.CSVMatrixLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LinkageTest {
    private DefaultMutableMatrix<Double> original;
    private DistanceMatrixMaker.DistanceMatrix distanceMatrix;

    @Before
    public void setUp() throws IOException {
        original = CSVMatrixLoader.loadDoubleMatrix(new InputStreamReader(CSVMatrixLoader.class.getResourceAsStream("../matrix1.csv")));
        distanceMatrix = new DistanceMatrixMaker<Double>().matrixDistance(original, new DoubleEuclidDistance());
    }

    @Test
    public void testCompleteLinkage() throws IOException {
        Linkage l = new CompleteLinkage();
        Assert.assertEquals(0, l.distance(distanceMatrix, new TestNode(new int[]{0}), new TestNode(new int[]{0})), 0);
        Assert.assertEquals(1, l.distance(distanceMatrix, new TestNode(new int[]{0}), new TestNode(new int[]{1})), 0);
        Assert.assertEquals(Math.sqrt(1+Math.pow(0.5, 2)),
                l.distance(distanceMatrix, new TestNode(new int[]{0}), new TestNode(new int[]{1, 2})), 0);
        Assert.assertEquals(4,
                l.distance(distanceMatrix, new TestNode(new int[]{3,4}), new TestNode(new int[]{1, 2})), 0);
    }

    private static class TestNode implements ClusterNode {
        Set<Integer> m_leafIndexes = new HashSet<Integer>();

        public TestNode(int[] leafIndexes) {
            for (int v: leafIndexes) {
                m_leafIndexes.add(v);
            }
        }

        @Override
        public Set<Integer> leafIndexes() {
            return m_leafIndexes;
        }

        @Override
        public ClusterNode[] getChildren() {
            return new ClusterNode[0];
        }
    }
}
