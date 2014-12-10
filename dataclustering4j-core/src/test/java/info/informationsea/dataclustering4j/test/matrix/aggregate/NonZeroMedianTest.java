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

package info.informationsea.dataclustering4j.test.matrix.aggregate;

import info.informationsea.dataclustering4j.matrix.aggregate.NonZeroMedian;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class NonZeroMedianTest {
    @Test
    public void testNonZeroMedian() {
        Assert.assertEquals(3., new NonZeroMedian<Double>().process(Arrays.asList(1., 2., 3., 6., 8., 0., 0.)), 0);
        Assert.assertEquals(1., new NonZeroMedian<Double>().process(Arrays.asList(1., 0., 0.)), 0);
    }
}
