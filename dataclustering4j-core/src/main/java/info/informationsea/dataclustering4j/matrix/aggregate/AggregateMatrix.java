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

import info.informationsea.dataclustering4j.matrix.Matrix;

import java.util.ArrayList;

public class AggregateMatrix<T, K, V> {
    public V[] aggregateByRow(Matrix<T> matrix, Aggregate<K, V> aggregate, V[] array) {
        if (array.length != matrix.getSize()[0])
            throw new IllegalArgumentException("a length of array should be equal to a number of row of matrix");

        for (int i = 0; i < array.length; i++) {
            int ncol = matrix.getSize()[1];
            ArrayList<K> values = new ArrayList<K>();
            for (int j = 0; j < ncol; j++) {
                values.add((K)matrix.get(i, j));
            }
            array[i] = aggregate.process(values);
        }
        return array;
    }

    public V[] aggregateByColumn(Matrix<T> matrix, Aggregate<K, V> aggregate, V[] array) {
        if (array.length != matrix.getSize()[1])
            throw new IllegalArgumentException("a length of array should be equal to a number of row of matrix");

        for (int i = 0; i < array.length; i++) {
            int nrow = matrix.getSize()[0];
            ArrayList<K> values = new ArrayList<K>();
            for (int j = 0; j < nrow; j++) {
                values.add((K)matrix.get(j, i));
            }
            array[i] = aggregate.process(values);
        }
        return array;
    }
}
