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

import info.informationsea.dataclustering4j.distance.DoubleEuclidDistance;
import org.junit.Assert;
import org.junit.Test;

public class DoubleEuclidDistanceTest {
    private static final int SIZE = 3;
    private static final Double[] array1 = new Double[]{1.0, 0.0, 0.0};
    private static final Double[] array2 = new Double[]{0.0, 0.0, 0.0};
    private static final Double[] array3 = new Double[]{1.0, 1.0, 1.0};
    private static final Double[] array4 = new Double[]{1.0, 1.0, 1.0, 10.0};

    @Test
    public void testEuclid() {
        DoubleEuclidDistance distance = new DoubleEuclidDistance();
        Assert.assertEquals(0.0, distance.distance(array1, array1), 0.0);
        Assert.assertEquals(1.0, distance.distance(array1, array2), 0.0);
        Assert.assertEquals(Math.sqrt(2.0), distance.distance(array1, array3), 0.0);
        Assert.assertEquals(Math.sqrt(3.0), distance.distance(array2, array3), 0.0);
    }

    @Test
    public void testException() {
        DoubleEuclidDistance distance = new DoubleEuclidDistance();
        boolean exceptionCatched = false;
        try {
            distance.distance(array1, array4);
        } catch (IllegalArgumentException iae) {
            exceptionCatched = true;
        }
        Assert.assertTrue(exceptionCatched);
    }
}
