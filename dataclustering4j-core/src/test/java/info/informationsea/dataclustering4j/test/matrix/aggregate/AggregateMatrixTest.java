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

package info.informationsea.dataclustering4j.test.matrix.aggregate;

import info.informationsea.dataclustering4j.matrix.DefaultMutableMatrix;
import info.informationsea.dataclustering4j.matrix.Matrix;
import info.informationsea.dataclustering4j.matrix.aggregate.AggregateMatrix;
import info.informationsea.dataclustering4j.matrix.aggregate.Mean;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AggregateMatrixTest {

    Matrix<Double> matrix;
    @Before
    public void initialize() {
        matrix = new DefaultMutableMatrix<Double>(new Double[]{1., 2., 3., 4.}, 2);
    }

    @Test
    public void testAggregateMatrixByRow() {
        Double[] rowMeans = new Double[2];
        new AggregateMatrix<Double, Number, Double>().aggregateByRow(matrix, new Mean(), rowMeans);
        Assert.assertArrayEquals(new Double[]{1.5, 3.5}, rowMeans);
    }

    @Test
    public void testAggregateMatrixByColumn() {
        Double[] rowMeans = new Double[2];
        new AggregateMatrix<Double, Number, Double>().aggregateByColumn(matrix, new Mean(), rowMeans);
        Assert.assertArrayEquals(new Double[]{2., 3.}, rowMeans);
    }

    @Test
    public void testAggregateMatrixByRow2() {
        boolean exceptionThrowed = false;
        try {
            new AggregateMatrix<Double, Number, Double>().aggregateByRow(matrix, new Mean(), new Double[3]);
        } catch (IllegalArgumentException e) {
            exceptionThrowed = true;
        }
        Assert.assertTrue(exceptionThrowed);
    }

    @Test
    public void testAggregateMatrixByColumn2() {
        boolean exceptionThrowed = false;
        try {
            new AggregateMatrix<Double, Number, Double>().aggregateByColumn(matrix, new Mean(), new Double[3]);
        } catch (IllegalArgumentException e) {
            exceptionThrowed = true;
        }
        Assert.assertTrue(exceptionThrowed);
    }
}
