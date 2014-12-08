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

public class TriangleMutableMatrix<T> extends AbstractMatrix<T> implements MutableMatrix<T> {
    int m_size;

    Object[][] m_matrix;

    public TriangleMutableMatrix(int size, T defaultValue) {
        initialize(size, defaultValue);
    }

    private void initialize(int size, T defaultValue) {
        m_size = size;

        m_matrix = new Object[m_size][];
        for (int i = 0; i < m_size; ++i) {
            m_matrix[i] = new Object[i+1];
            for (int j = 0; j < i+1; ++j) {
                m_matrix[i][j] = defaultValue;
            }
        }
    }

    @Override
    public void put(int row, int col, T value) {
        if (row < col) {
            m_matrix[col][row] = value;
        } else {
            m_matrix[row][col] = value;
        }
    }

    @Override
    public int[] getSize() {
        return new int[]{m_size, m_size};
    }

    @Override
    public T get(int row, int col) {
        if (row < col) {
            return (T)m_matrix[col][row];
        } else {
            return (T)m_matrix[row][col];
        }
    }

    @Override
    public Object[] getRow(int row) {
        Object[] rowarray = new Object[m_size];
        System.arraycopy(m_matrix[row], 0, rowarray, 0, m_matrix[row].length);
        for (int i = m_matrix[row].length; i < m_size; ++i) {
            rowarray[i] = m_matrix[i][row];
        }

        return rowarray;
    }

    @Override
    public Matrix<T> transpose() {
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TriangleMutableMatrix) {
            TriangleMutableMatrix m = (TriangleMutableMatrix)obj;
            if (m_size != m.m_size)
                return false;

            for (int i = 0; i < m_size; ++i) {
                for (int j = 0; j < i+1; ++j) {
                    if (!m.m_matrix[i][j].equals(m_matrix[i][j]))
                        return false;
                }
            }
            return true;
        }
        return super.equals(obj);
    }
}
