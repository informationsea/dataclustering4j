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
 * Labeled Triangle Matrix implementation.
 * Please refer {@link info.informationsea.dataclustering4j.matrix.TriangleMutableMatrix} to learn more.
 * @param <T> a type of values
 * @param <K> a key type of rows and columns
 */
public class LabeledTriangleMutableMatrix<T, K> extends TriangleMutableMatrix<T> implements LabeledMatrix<T, K, K> {

    AbstractMutableLabeledMatrix.MutableLabeledMatrixProxy<T, K, K> m_proxy =
            new AbstractMutableLabeledMatrix.MutableLabeledMatrixProxy<T, K, K>(this);

    public LabeledTriangleMutableMatrix(int size, T defaultValue) {
        super(size, defaultValue);
    }

    @Override
    public void setRowKeys(List<K> rowKeys) {
        m_proxy.setRowKeys(rowKeys);
        m_proxy.setColumnKeys(rowKeys);
    }

    @Override
    public void setColumnKeys(List<K> columnKeys) {
        m_proxy.setRowKeys(columnKeys);
        m_proxy.setColumnKeys(columnKeys);
    }

    public void setKeys(List<K> keys) {
        m_proxy.setRowKeys(keys);
        m_proxy.setColumnKeys(keys);
    }

    @Override
    public List<K> getRowKeys() {
        return m_proxy.getRowKeys();
    }

    @Override
    public List<K> getColumnKeys() {
        return m_proxy.getColumnKeys();
    }

    public List<K> getKeys() {
        return m_proxy.getRowKeys();
    }

    @Override
    public T get(K row, K column) {
        return m_proxy.get(row, column);
    }

    @Override
    public void put(K row, K column, T value) {
        m_proxy.put(row, column, value);
    }
}
