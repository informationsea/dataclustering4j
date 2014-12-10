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
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Median implements Aggregate<Number, Double> {
    @Override
    public Double process(List<Number> array) {
        Number[] numbers = new Number[array.size()];
        array.toArray(numbers);
        Arrays.sort(numbers);
        if (numbers.length % 2 == 0) {
            return (numbers[(array.size()/2)].doubleValue() + numbers[array.size()/2-1].doubleValue())/2;
        } else {
            return numbers[array.size()/2].doubleValue();
        }
    }
}
