package info.informationsea.dataclustering4j.test.graphics;

import info.informationsea.dataclustering4j.clustering.ClusterNode;
import info.informationsea.dataclustering4j.clustering.Clustering;
import info.informationsea.dataclustering4j.distance.DistanceMatrixMaker;
import info.informationsea.dataclustering4j.distance.DoubleEuclidDistance;
import info.informationsea.dataclustering4j.graphics.DendrogramDraw;
import info.informationsea.dataclustering4j.linkage.CompleteLinkage;
import info.informationsea.dataclustering4j.matrix.DefaultMutableMatrix;
import info.informationsea.dataclustering4j.matrix.Matrix;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * dataclustering4j
 * Copyright (C) 2014 OKAMURA Yasunobu
 * Created on 14/12/11.
 */
public class DendrogramDrawTest {

    ClusterNode node;

    @Before
    public void prepare() {
        Matrix<Double> original = new DefaultMutableMatrix<Double>(new Double[]{
                0., 0.,
                1., 0.,
                1., 0.5,
                1., 3.,
                1.0, 4.0,
                1.5, 3.0}, 2);
        DistanceMatrixMaker.DistanceMatrix distanceMatrix = DistanceMatrixMaker.matrixDistance(original, new DoubleEuclidDistance());
        node = Clustering.doClustering(distanceMatrix, new CompleteLinkage());
    }

    @Test
    public void testDendrogramDraw() throws IOException {
        BufferedImage image = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 500, 500);
        new DendrogramDraw(image).setTitle("TEST Dendrogram").draw(node);

        if (System.getProperty("user.home") == null) return;
        File buildDir = new File(System.getProperty("user.dir"), "build");
        ImageIO.write(image, "png", new File(buildDir, "dendrogram.png"));
    }
}
