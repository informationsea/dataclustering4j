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

public class DefaultLabeledMutableMatrix<T, R, C> extends DefaultMutableMatrix<T> implements LabeledMatrix<T, R, C> {

    private AbstractMutableLabeledMatrix.MutableLabeledMatrixProxy<T, R, C> m_proxy =
            new AbstractMutableLabeledMatrix.MutableLabeledMatrixProxy<T, R, C>(this);

    public DefaultLabeledMutableMatrix(int nrow, int ncol, T defaultValue) {
        super(nrow, ncol, defaultValue);
    }

    public DefaultLabeledMutableMatrix(Matrix<T> original) {
        super(original);
    }

    public DefaultLabeledMutableMatrix(LabeledMatrix<T, R, C> original) {
        super(original);
        setColumnKeys(original.getColumnKeys());
        setRowKeys(original.getRowKeys());
    }

    public DefaultLabeledMutableMatrix(LabeledMatrix<T, R, C> original, int top, int bottom, int left, int right) {
        super(original, top, bottom, left, right);
        if (original.getRowKeys() != null) {
            setRowKeys(original.getRowKeys().subList(top, bottom));
        }
        if (original.getColumnKeys() != null) {
            setColumnKeys(original.getColumnKeys().subList(left, right));
        }
    }

    public DefaultLabeledMutableMatrix(T[] values, int ncol) {
        super(values, ncol);
    }

    @Override
    public void setRowKeys(List<R> rowKeys) {
        m_proxy.setRowKeys(rowKeys);
    }

    @Override
    public void setColumnKeys(List<C> columnKeys) {
        m_proxy.setColumnKeys(columnKeys);
    }

    @Override
    public List<R> getRowKeys() {
        return m_proxy.getRowKeys();
    }

    @Override
    public List<C> getColumnKeys() {
        return m_proxy.getColumnKeys();
    }

    @Override
    public Object[] getRow(R row) {
        return m_proxy.getRow(row);
    }

    @Override
    public T[] getRow(T[] array, R row) {
        return m_proxy.getRow(array, row);
    }

    @Override
    public T get(R row, C column) {
        return m_proxy.get(row, column);
    }

    @Override
    public void put(R row, C column, T value) {
        m_proxy.put(row, column, value);
    }

    @Override
    public Matrix<T> transpose() {
        DefaultLabeledMutableMatrix<T, C, R> newmatrix = new DefaultLabeledMutableMatrix<T, C, R>(m_ncol, m_nrow, null);
        for (int i = 0; i < m_nrow; ++i) {
            for (int j = 0; j < m_ncol; ++j) {
                newmatrix.m_matrix[j][i] = m_matrix[i][j];
            }
        }
        newmatrix.setRowKeys(getColumnKeys());
        newmatrix.setColumnKeys(getRowKeys());

        return newmatrix;

    }
}
