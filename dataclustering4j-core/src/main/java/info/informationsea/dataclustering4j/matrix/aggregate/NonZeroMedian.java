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

package info.informationsea.dataclustering4j.matrix.aggregate;

import java.util.ArrayList;
import java.util.List;

public class NonZeroMedian<T extends Number> implements Aggregate<T, Double>{
    private Median<T> median = new Median<T>();

    @Override
    public Double process(List<T> array) {
        ArrayList<T> nonzero = new ArrayList<T>();
        for (T v: array) {
            if (v.doubleValue() != 0)
                nonzero.add(v);
        }
        return median.process(nonzero);
    }
}
