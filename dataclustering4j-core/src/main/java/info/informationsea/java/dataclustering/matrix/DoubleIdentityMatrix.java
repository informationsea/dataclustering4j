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

public class DoubleIdentityMatrix extends AbstractMatrix<Double> implements Matrix<Double> {

    private int m_size;

    public DoubleIdentityMatrix(int size) {
        m_size = size;
    }

    @Override
    public int[] getSize() {
        return new int[]{m_size, m_size};
    }

    @Override
    public Double get(int row, int col) {
        if (row == col) {
            return 1.0;
        }
        return 0.0;
    }

    @Override
    public Object[] getRow(int row) {
        Double[] result = new Double[m_size];
        for (int i = 0; i < result.length; i++) {
            result[i] = (i == row ? 1.0 : 0.0);
        }
        return result;
    }

    @Override
    public Matrix<Double> transpose() {
        return new DoubleIdentityMatrix(m_size);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DoubleIdentityMatrix) {
            DoubleIdentityMatrix m = (DoubleIdentityMatrix)obj;
            if (m.getSize()[0] == m_size)
                return true;
        }
        return false;
    }
}
