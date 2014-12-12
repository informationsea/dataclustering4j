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

import java.util.List;

/**
 * A matrix with row and column labels.
 * @param <T> type of values
 * @param <R> type of row keys
 * @param <C> type of column keys
 */
public interface LabeledMatrix<T, R, C> extends MutableMatrix<T> {
    /**
     * set new keys for the row
     * @param rowKeys array of row names
     */
    public void setRowKeys(List<R> rowKeys);

    /**
     * set new keys for the column
     * @param columnKeys array of column names
     */
    public void setColumnKeys(List<C> columnKeys);

    /**
     * get keys for the row
     * @return a row key array
     */
    public List<R> getRowKeys();

    /**
     * get keys for the columnn
     * @return a column key array
     */
    public List<C> getColumnKeys();

    /**
     * get a value corresponding to {@code row} and {@code column}
     * @param row a key of the row
     * @param column a key of the column
     * @return a corresponding value
     */
    public T get(R row, C column);

    /**
     * put a new value for {@code row} and {@code column}
     * @param row a key of the row
     * @param column a key of the column
     * @param value a new value
     */
    public void put(R row, C column, T value);
}
