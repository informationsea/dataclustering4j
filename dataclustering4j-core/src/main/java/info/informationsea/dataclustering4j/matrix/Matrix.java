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

package info.informationsea.dataclustering4j.matrix;

import info.informationsea.dataclustering4j.matrix.aggregate.Aggregate;

/**
 * A matrix of values.
 * @param <T> type of values
 */
public interface Matrix<T> {
    /**
     * Get size of matrix.
     * @return an array of a number row and a number column
     */
    public int[] getSize();

    /**
     * Get value of matrix
     * @param row row index
     * @param col column index
     * @return a value corresponding to {@code row} and {@code col}
     */
    public T get(int row, int col);

    /**
     * Get array of matrix row values
     * @param row row index
     * @return a object array of values in row
     */
    public Object[] getRow(int row);

    /**
     * Get array of matrix row values in {@code T} array
     * @param array row index
     * @param row a {@code T} array which length is the number of column
     * @return a {@code T} array of values in row
     */
    public T[] getRow(T[] array, int row);

    /**
     * Transpose matrix
     * @return a transposed matrix
     */
    public Matrix<T> transpose();

    /**
     * Aggregate by rows.
     * Calculate row means, or medians.
     *
     * @param aggregate implementation of {@link info.informationsea.dataclustering4j.matrix.aggregate.Aggregate}, such as {@link info.informationsea.dataclustering4j.matrix.aggregate.Mean}
     * @param valueArray an array to keep result
     * @param <V> a type of result
     * @return {@code valueArray}
     */
    public<V> V[] aggregateByRow(Aggregate<T, V> aggregate, V[] valueArray);

    /**
     * Aggregate by columns.
     * Calculate columns means, or medians.
     *
     * @param aggregate implementation of {@link info.informationsea.dataclustering4j.matrix.aggregate.Aggregate}, such as {@link info.informationsea.dataclustering4j.matrix.aggregate.Mean}
     * @param valueArray an array to keep result
     * @param <V> a type of result
     * @return {@code valueArray}
     */
    public<V> V[] aggregateByColumn(Aggregate<T, V> aggregate, V[] valueArray);
}
