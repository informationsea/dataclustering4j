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

import info.informationsea.dataclustering4j.matrix.aggregate.Median;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MedianTest {
    @Test
    public void testMedian() {
        List<Number> list = new ArrayList<Number>();
        for (int i = 0; i < 10; i++)
            list.add(i);
        Assert.assertEquals(4.5, new Median().process(list), 0);
    }

    @Test
    public void testMedian2() {
        List<Number> list = new ArrayList<Number>();
        list.add(1);
        list.add(10);
        list.add(12);
        list.add(20);
        list.add(16);

        Assert.assertEquals(12, new Median().process(list), 0);
    }
}
