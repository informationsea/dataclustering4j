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

/**
 * A default implementation of mutable matrix
 * @param <T> a type of values
 */
public class DefaultMutableMatrix<T> extends AbstractMatrix<T> implements MutableMatrix<T> {

    int m_nrow;
    int m_ncol;

    Object[][] m_matrix;

    /**
     * New matrix with numbers of row and column, and a default value
     * @param nrow a number of row
     * @param ncol a number of column
     * @param defaultValue a default value of matrix
     */
    public DefaultMutableMatrix(int nrow, int ncol, T defaultValue) {
        initialize(nrow, ncol, defaultValue);
    }

    /**
     * Make copy of original matrix
     * @param original original matrix
     */
    public DefaultMutableMatrix(Matrix<T> original) {
        initialize(original.getSize()[0], original.getSize()[1], null);
        for (int i = 0; i < m_nrow; ++i) {
            for (int j = 0; j < m_ncol; ++j) {
                m_matrix[i][j] = original.get(i, j);
            }
        }
    }

    /**
     * New matrix with default values and a number of column.
     * A number of row is calculated from a length of {@code values} and {@code ncol}
     * @param values a default values
     * @param ncol a number of column
     */
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

    /**
     * Make subset of a supplied matrix
     * @param parent parent matrix
     * @param top a minimum row index of parent matrix to copy.
     * @param bottom a maximum row index+1 of parent matrix to copy.
     * @param left a minimum column index of parent matrix to copy.
     * @param right a maximum column index+1 of parent matrix to copy.
     */
    public DefaultMutableMatrix(Matrix<T> parent, int top, int bottom, int left, int right) {
        initialize(bottom - top, right - left, parent.get(top, left));
        for (int i = 0; i < (bottom - top); ++i) {
            for (int j = 0; j < (right - left); ++j) {
                put(i, j, parent.get(i + top, j + left));
            }
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
        Object[] rowArray = new Object[m_matrix[row].length];
        System.arraycopy(m_matrix[row], 0, rowArray, 0, m_ncol);
        return rowArray;
    }

    @Override
    public Object[] getColumn(int column) {
        Object[] columnArray = new Object[m_matrix.length];
        for (int i = 0; i < m_matrix.length; i++) {
            columnArray[i] = m_matrix[i][column];
        }
        return columnArray;
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
