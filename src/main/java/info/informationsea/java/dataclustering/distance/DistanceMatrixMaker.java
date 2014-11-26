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

package info.informationsea.java.dataclustering.distance;

import info.informationsea.java.dataclustering.matrix.Matrix;
import info.informationsea.java.dataclustering.matrix.TriangleMutableMatrix;

public class DistanceMatrixMaker<T> {
    public DistanceMatrixMaker(){}

    public DistanceMatrix matrixDistance(Matrix<T> matrix, Distance distance) {
        int nrow = matrix.getSize()[0];
        DistanceMatrix m = new DistanceMatrix(nrow, 0.0);

        for (int i = 0; i < nrow; ++i) {
            for (int j = i+1; j < nrow; ++j) {
                m.put(i, j, distance.distance(matrix.getRow(i), matrix.getRow(j)));
            }
        }

        return m;
    }

    public static class DistanceMatrix extends TriangleMutableMatrix<Double> {

        public DistanceMatrix(int size, Double defaultValue) {
            super(size, defaultValue);
        }
    }
}
