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

public class DefaultMutableMatrix<T> extends AbstractMatrix<T> implements MutableMatrix<T> {

    int m_nrow;
    int m_ncol;

    Object[][] m_matrix;

    public DefaultMutableMatrix(int nrow, int ncol, T defaultValue) {
        initialize(nrow, ncol, defaultValue);
    }

    public DefaultMutableMatrix(Matrix<T> original) {
        initialize(original.getSize()[0], original.getSize()[1], null);
        for (int i = 0; i < m_nrow; ++i) {
            for (int j = 0; j < m_ncol; ++j) {
                m_matrix[i][j] = original.get(i, j);
            }
        }
    }

    public DefaultMutableMatrix(T[] values, int ncol) {
        if (values.length % ncol != 0)
            throw new IllegalArgumentException("length of values should be able to divided by ncol");
        initialize(values.length/ncol, ncol, null);

        for (int i = 0; i < values.length; ++i) {
            int row = i/ncol;
            int col = i%ncol;
            m_matrix[row][col] = values[i];
        }
    }

    private void initialize(int nrow, int ncol, T defaultValue) {
        m_nrow = nrow;
        m_ncol = ncol;

        m_matrix = new Object[nrow][];
        for (int i = 0; i < nrow; ++i) {
            m_matrix[i] = new Object[ncol];
            for (int j = 0; j < ncol; ++j) {
                m_matrix[i][j] = defaultValue;
            }
        }
    }

    @Override
    public void put(int row, int col, T value) {
        m_matrix[row][col] = value;
    }

    @Override
    public int[] getSize() {
        return new int[]{m_nrow, m_ncol};
    }

    @Override
    public T get(int row, int col) {
        return (T)m_matrix[row][col];
    }

    @Override
    public Object[] getRow(int row) {
        return m_matrix[row];
    }

    @Override
    public Matrix<T> transpose() {
        DefaultMutableMatrix<T> newmatrix = new DefaultMutableMatrix<T>(m_ncol, m_nrow, null);
        for (int i = 0; i < m_nrow; ++i) {
            for (int j = 0; j < m_ncol; ++j) {
                newmatrix.m_matrix[j][i] = m_matrix[i][j];
            }
        }

        return newmatrix;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DefaultMutableMatrix) {
            DefaultMutableMatrix m = (DefaultMutableMatrix)obj;
            if (m_ncol != m.m_ncol || m_nrow != m.m_nrow)
                return false;

            for (int i = 0; i < m_nrow; ++i) {
                for (int j = 0; j < m_ncol; ++j) {
                    if (!m.m_matrix[i][j].equals(m_matrix[i][j]))
                        return false;
                }
            }
            return true;
        }
        return super.equals(obj);
    }
}
