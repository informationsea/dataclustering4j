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

/**
 * Created by yasu on 14/11/27.
 */
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
