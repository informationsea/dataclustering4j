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
 * An implementation of common methods in {@link Matrix}
 * @param <T> type of values
 */
abstract public class AbstractMatrix<T> implements Matrix<T>{

    /**
     * Get human readable representation of matrix
     * @return human friendly string
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Matrix[%s]\n", getClass().getCanonicalName()));

        int[] size = getSize();

        for (int i = 0; i < size[0]; i++) {
            sb.append(String.format("[%d] ", i));
            for (int j = 0; j < size[1]; j++) {
                sb.append(get(i, j).toString());
                if (j != size[1] - 1)
                    sb.append(", ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    @Override
    public T[] getRow(T[] array, int row) {
        System.arraycopy(getRow(row), 0, array, 0, getSize()[1]);
        return array;
    }
}
