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

package info.informationsea.dataclustering4j.csvloader;

import au.com.bytecode.opencsv.CSVReader;
import info.informationsea.dataclustering4j.matrix.DefaultLabeledMutableMatrix;
import info.informationsea.dataclustering4j.matrix.DefaultMutableMatrix;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;

public class CSVMatrixLoader {
    private CSVMatrixLoader(){};

    public static DefaultMutableMatrix<Double> loadDoubleMatrix(Reader reader) throws IOException{
        CSVReader csvReader = new CSVReader(reader);
        ArrayList<String[]> rows = new ArrayList<String[]>();
        int maximumColumn = 0;
        String[] line;
        while ((line = csvReader.readNext()) != null) {
            rows.add(line);
            maximumColumn = Math.max(maximumColumn, line.length);
        }

        DefaultMutableMatrix<Double> m = new DefaultMutableMatrix<Double>(rows.size(), maximumColumn, 0.0);

        for (int i = 0; i < rows.size(); ++i) {
            String[] oneRow = rows.get(i);
            for (int j = 0; j < oneRow.length; ++j) {
                m.put(i, j, Double.valueOf(oneRow[j]));
            }
        }

        return m;
    }

    public static DefaultLabeledMutableMatrix<Double, String, String> loadDoubleMatrixWithLabel(Reader reader) throws IOException{
        CSVReader csvReader = new CSVReader(reader);
        ArrayList<String[]> rows = new ArrayList<String[]>();
        int maximumColumn = 0;
        String[] line;
        while ((line = csvReader.readNext()) != null) {
            rows.add(line);
            maximumColumn = Math.max(maximumColumn, line.length);
        }

        DefaultLabeledMutableMatrix<Double, String, String> m = new DefaultLabeledMutableMatrix<Double, String, String>(rows.size()-1, maximumColumn-1, 0.0);

        for (int i = 1; i < rows.size(); ++i) {
            String[] oneRow = rows.get(i);
            for (int j = 1; j < oneRow.length; ++j) {
                m.put(i-1, j-1, Double.valueOf(oneRow[j]));
            }
        }

        if (rows.get(0).length == maximumColumn) {
            String[] header = new String[maximumColumn-1];
            System.arraycopy(rows.get(0), 1, header, 0, maximumColumn-1);
            m.setColumnKeys(Arrays.asList(header));
        } else if (rows.get(0).length == maximumColumn-1) {
            m.setColumnKeys(Arrays.asList(rows.get(0)));
        } else {
            throw new RuntimeException("Illegal number of column names");
        }

        String[] rownames = new String[rows.size()-1];
        {
            int i = 0;
            for (String[] onerow:rows) {
                if (i == 0) {
                    i += 1;
                    continue;
                }
                rownames[i-1] = onerow[0];
                i += 1;
            }
        }
        m.setRowKeys(Arrays.asList(rownames));

        return m;
    }

}
