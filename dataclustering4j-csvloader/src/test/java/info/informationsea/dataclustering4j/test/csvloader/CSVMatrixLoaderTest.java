package info.informationsea.dataclustering4j.test.csvloader;

import info.informationsea.dataclustering4j.csvloader.CSVMatrixLoader;
import info.informationsea.dataclustering4j.matrix.DefaultLabeledMutableMatrix;
import info.informationsea.dataclustering4j.matrix.DefaultMutableMatrix;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by yasu on 14/11/26.
 */
public class CSVMatrixLoaderTest {

    @Test
    public void testDoubleLoader() throws IOException {
        InputStream is = CSVMatrixLoaderTest.class.getResourceAsStream("../matrix1.csv");
        Assert.assertNotNull(is);
        DefaultMutableMatrix<Double> m = CSVMatrixLoader.loadDoubleMatrix(new InputStreamReader(is));
        Assert.assertEquals(Double.valueOf(0), m.get(0, 0));
        Assert.assertEquals(Double.valueOf(1), m.get(1, 0));
        Assert.assertEquals(Double.valueOf(0.5), m.get(2, 1));
    }

    @Test
    public void testLabeledDoubleLoader() throws IOException {
        InputStream is = CSVMatrixLoaderTest.class.getResourceAsStream("../matrix2.csv");
        Assert.assertNotNull(is);
        DefaultLabeledMutableMatrix<Double, String, String> m = CSVMatrixLoader.loadDoubleMatrixWithLabel(new InputStreamReader(is));
        Assert.assertEquals(Double.valueOf(0), m.get(0, 0));
        Assert.assertEquals(Double.valueOf(1), m.get(1, 0));
        Assert.assertEquals(Double.valueOf(0.5), m.get(2, 1));
        Assert.assertArrayEquals(new String[]{"X", "Y"}, m.getColumnKeys());
        Assert.assertArrayEquals(new String[]{"A", "B", "C", "D", "E", "F"}, m.getRowKeys());
    }

    @Test
    public void testLabeledDoubleLoader2() throws IOException {
        InputStream is = CSVMatrixLoaderTest.class.getResourceAsStream("../matrix3.csv");
        Assert.assertNotNull(is);
        DefaultLabeledMutableMatrix<Double, String, String> m = CSVMatrixLoader.loadDoubleMatrixWithLabel(new InputStreamReader(is));
        Assert.assertEquals(Double.valueOf(0), m.get(0, 0));
        Assert.assertEquals(Double.valueOf(1), m.get(1, 0));
        Assert.assertEquals(Double.valueOf(0.5), m.get(2, 1));
        Assert.assertArrayEquals(new String[]{"X", "Y"}, m.getColumnKeys());
        Assert.assertArrayEquals(new String[]{"A", "B", "C", "D", "E", "F"}, m.getRowKeys());
    }

    @Test
    public void testLabeledDoubleLoader3() throws IOException {
        InputStream is = CSVMatrixLoaderTest.class.getResourceAsStream("../matrix4.csv");
        Assert.assertNotNull(is);
        boolean exceptionRaised = false;
        try {
            DefaultLabeledMutableMatrix<Double, String, String> m = CSVMatrixLoader.loadDoubleMatrixWithLabel(new InputStreamReader(is));
        } catch (RuntimeException re) {
            exceptionRaised = true;
        }
        Assert.assertTrue(exceptionRaised);
    }
}
