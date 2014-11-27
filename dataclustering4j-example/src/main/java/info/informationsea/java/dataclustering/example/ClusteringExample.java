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

package info.informationsea.java.dataclustering.example;

import info.informationsea.java.dataclustering.clustering.ClusterNode;
import info.informationsea.java.dataclustering.clustering.Clustering;
import info.informationsea.java.dataclustering.csvloader.CSVMatrixLoader;
import info.informationsea.java.dataclustering.distance.DistanceMatrixMaker;
import info.informationsea.java.dataclustering.distance.DoubleEuclidDistance;
import info.informationsea.java.dataclustering.linkage.CompleteLinkage;
import info.informationsea.java.dataclustering.matrix.Matrix;

import java.io.FileReader;
import java.io.IOException;

public class ClusteringExample {
    public static void main(String[] argv) {
        if (argv.length != 1) {
            System.err.println("ClusteringExample CSV-Matrix");
            return;
        }

        try {
            Matrix<Double> m = CSVMatrixLoader.loadDoubleMatrix(new FileReader(argv[0]));
            DistanceMatrixMaker.DistanceMatrix dm = new DistanceMatrixMaker<Double>().matrixDistance(m, new DoubleEuclidDistance());
            ClusterNode node = Clustering.doClustering(dm, new CompleteLinkage());
            System.out.println(node.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
