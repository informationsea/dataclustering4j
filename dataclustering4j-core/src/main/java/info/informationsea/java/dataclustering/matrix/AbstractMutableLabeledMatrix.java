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

import java.util.HashMap;

abstract public class AbstractMutableLabeledMatrix<T, R, C> extends AbstractMatrix<T> implements LabeledMatrix<T, R, C>, MutableMatrix<T>  {

    private R[] m_rowKeys = null;
    private C[] m_columnKeys = null;
    private HashMap<R, Integer> m_rowKeyMap = new HashMap<R, Integer>();
    private HashMap<C, Integer> m_columnKeyMap = new HashMap<C, Integer>();

    @Override
    public void setRowKeys(R[] rowKeys) {
        if (rowKeys == null) {
            m_rowKeys = null;
            m_rowKeyMap.clear();
            return;
        }

        if (rowKeys.length != getSize()[0])
            throw new IllegalArgumentException("A number of row keys should equal to a number of rows");

        m_rowKeys = rowKeys;
        for (int i = 0; i < rowKeys.length; i++) {
            m_rowKeyMap.put(rowKeys[i], i);
        }
    }

    @Override
    public void setColumnKeys(C[] columnKeys) {
        if (columnKeys == null) {
            m_columnKeys = null;
            m_columnKeyMap.clear();
            return;
        }

        if (columnKeys.length != getSize()[1])
            throw new IllegalArgumentException("A number of row keys should equal to a number of rows");

        m_columnKeys = columnKeys;

        for (int i = 0; i < columnKeys.length; i++) {
            m_columnKeyMap.put(columnKeys[i], i);
        }
    }

    @Override
    public R[] getRowKeys() {
        if (m_rowKeys == null)
            return null;
        return m_rowKeys.clone();
    }

    @Override
    public C[] getColumnKeys() {
        if (m_columnKeys == null)
            return null;
        return m_columnKeys.clone();
    }

    @Override
    public T get(R row, C column) {
        return get(m_rowKeyMap.get(row), m_columnKeyMap.get(column));
    }

    @Override
    public void put(R row, C column, T value) {
        put(m_rowKeyMap.get(row), m_columnKeyMap.get(column), value);
    }

    public static class MutableLabeledMatrixProxy<T, R, C> extends AbstractMutableLabeledMatrix<T, R, C>{

        private MutableMatrix<T> m_parent;

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
            throw new UnsupportedOperationException();
        }

        @Override
        public Matrix<T> transpose() {
            throw new UnsupportedOperationException();
        }

    }
}
