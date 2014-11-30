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

package info.informationsea.java.dataclustering.matrix;

public interface LabeledMatrix<T, R, C> extends Matrix<T> {
    /**
     *
     * @param rowKeys array of row names
     */
    public void setRowKeys(R[] rowKeys);
    public void setColumnKeys(C[] columnKeys);


    public R[] getRowKeys();
    public C[] getColumnKeys();

    public T get(R row, C column);
    public void put(R row, C column, T value);
}
