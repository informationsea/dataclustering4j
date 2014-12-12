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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * An implementation of common methods in {@link LabeledMatrix}
 * @param <T> type of values
 * @param <R> type of row keys
 * @param <C> type of column keys
 */
abstract public class AbstractMutableLabeledMatrix<T, R, C> extends AbstractMatrix<T> implements LabeledMatrix<T, R, C>, MutableMatrix<T>  {

    private List<R> m_rowKeys = null;
    private List<C> m_columnKeys = null;
    private HashMap<R, Integer> m_rowKeyMap = new HashMap<R, Integer>();
    private HashMap<C, Integer> m_columnKeyMap = new HashMap<C, Integer>();

    @Override
    public void setRowKeys(List<R> rowKeys) {
        m_rowKeyMap.clear();

        if (rowKeys == null) {
            m_rowKeys = null;
            return;
        }

        if (rowKeys.size() != getSize()[0])
            throw new IllegalArgumentException("A number of row keys should equal to a number of rows");

        m_rowKeys = rowKeys;
        for (int i = 0; i < rowKeys.size(); i++) {
            m_rowKeyMap.put(rowKeys.get(i), i);
        }
    }

    @Override
    public void setColumnKeys(List<C> columnKeys) {
        m_columnKeyMap.clear();

        if (columnKeys == null) {
            m_columnKeys = null;
            return;
        }

        if (columnKeys.size() != getSize()[1])
            throw new IllegalArgumentException("A number of row keys should equal to a number of rows");

        m_columnKeys = columnKeys;

        for (int i = 0; i < columnKeys.size(); i++) {
            m_columnKeyMap.put(columnKeys.get(i), i);
        }
    }

    @Override
    public List<R> getRowKeys() {
        if (m_rowKeys == null)
            return null;
        ArrayList <R> newlist = new ArrayList<R>();
        newlist.addAll(m_rowKeys);
        return newlist;
    }

    @Override
    public List<C> getColumnKeys() {
        if (m_columnKeys == null)
            return null;
        ArrayList<C> newlist = new ArrayList<C>();
        newlist.addAll(m_columnKeys);
        return newlist;
    }

    @Override
    public T[] getRow(T[] array, R row) {
        return getRow(array, m_rowKeyMap.get(row));
    }

    @Override
    public Object[] getRow(R row) {
        return getRow(m_rowKeyMap.get(row));
    }

    @Override
    public T get(R row, C column) {
        return get(m_rowKeyMap.get(row), m_columnKeyMap.get(column));
    }

    @Override
    public void put(R row, C column, T value) {
        put(m_rowKeyMap.get(row), m_columnKeyMap.get(column), value);
    }

    /**
     * A proxy class to mixin LabeledMatrix to a existing matrix class
     * @param <T> type of values
     * @param <R> type of row keys
     * @param <C> type of column keys
     */
    public static class MutableLabeledMatrixProxy<T, R, C> extends AbstractMutableLabeledMatrix<T, R, C>{

        private MutableMatrix<T> m_parent;

        /**
         * Set parent object of a proxy class
         * @param parent parent object
         */
        public MutableLabeledMatrixProxy(MutableMatrix<T> parent) {
            m_parent = parent;
        }

        @Override
        public void put(int row, int col, T value) {
            m_parent.put(row, col, value);
        }

        @Override
        public int[] getSize() {
            return m_parent.getSize();
        }

        @Override
        public T get(int row, int col) {
            return m_parent.get(row, col);
        }

        @Override
        public Object[] getRow(int row) {
            return m_parent.getRow(row);
        }

        @Override
        public Matrix<T> transpose() {
            throw new UnsupportedOperationException();
        }

    }
}
