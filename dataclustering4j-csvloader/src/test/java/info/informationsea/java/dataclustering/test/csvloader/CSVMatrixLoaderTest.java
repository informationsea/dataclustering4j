package info.informationsea.java.dataclustering.test.csvloader;

import info.informationsea.java.dataclustering.csvloader.CSVMatrixLoader;
import info.informationsea.java.dataclustering.matrix.DefaultMutableMatrix;
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
}
