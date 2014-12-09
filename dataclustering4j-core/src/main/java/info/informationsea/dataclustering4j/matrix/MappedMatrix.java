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

public class MappedMatrix<T> extends AbstractMatrix<T> {

    private Matrix<T> m_parent;
    private MapValue<T> m_map;

    public MappedMatrix(Matrix<T> parent, MapValue<T> map) {
        m_parent = parent;
        m_map = map;
    }

    @Override
    public int[] getSize() {
        return m_parent.getSize();
    }

    @Override
    public T get(int row, int col) {
        return m_map.map(m_parent.get(row, col));
    }

    @Override
    public Object[] getRow(int row) {
        Object[] rowArray = m_parent.getRow(row);
        Object[] newArray = new Object[rowArray.length];
        for (int i = 0; i < rowArray.length; i++) {
            newArray[i] = m_map.map((T) rowArray[i]);
        }
        return newArray;
    }

    @Override
    public Matrix<T> transpose() {
        return new MappedMatrix<T>(m_parent.transpose(), m_map);
    }

    public static interface MapValue<T> {
        public T map(T obj);
    }
}
