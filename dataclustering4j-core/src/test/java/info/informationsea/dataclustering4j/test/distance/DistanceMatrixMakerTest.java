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

package info.informationsea.dataclustering4j.test.distance;

import info.informationsea.dataclustering4j.distance.DistanceMatrixMaker;
import info.informationsea.dataclustering4j.distance.DoubleEuclidDistance;
import info.informationsea.dataclustering4j.matrix.DefaultMutableMatrix;
import org.junit.Assert;
import org.junit.Test;

public class DistanceMatrixMakerTest {
    @Test
    public void testDistanceMatrixMaker() {
        DefaultMutableMatrix<Double> m = new DefaultMutableMatrix<Double>(3, 2, 0.0);
        m.put(1, 1, 1.0);
        m.put(2, 0, 1.0);
        //System.err.println(m.toString());
        DistanceMatrixMaker.DistanceMatrix dm = new DistanceMatrixMaker<Double>().
                matrixDistance(m, new DoubleEuclidDistance());

        Assert.assertEquals(Double.valueOf(0.0), dm.get(0, 0));
        Assert.assertEquals(Double.valueOf(1.0), dm.get(0, 1));
        Assert.assertEquals(Double.valueOf(1.0), dm.get(0, 2));
        Assert.assertEquals(Double.valueOf(Math.sqrt(2)), dm.get(1, 2));
    }
}
