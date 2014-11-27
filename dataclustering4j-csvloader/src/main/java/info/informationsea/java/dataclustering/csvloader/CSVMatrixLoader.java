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

package info.informationsea.java.dataclustering.csvloader;

import au.com.bytecode.opencsv.CSVReader;
import info.informationsea.java.dataclustering.matrix.DefaultMutableMatrix;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class CSVMatrixLoader {
    private CSVMatrixLoader(){};

    public static DefaultMutableMatrix<Double> loadDoubleMatrix(Reader reader) throws IOException{
        CSVReader csvReader = new CSVReader(reader);
        ArrayList<String[]> rows = new ArrayList<String[]>();
        int maximumColmuns = 0;
        String[] line;
        while ((line = csvReader.readNext()) != null) {
            rows.add(line);
            maximumColmuns = Math.max(maximumColmuns, line.length);
        }

        DefaultMutableMatrix<Double> m = new DefaultMutableMatrix<Double>(rows.size(), maximumColmuns, 0.0);

        for (int i = 0; i < rows.size(); ++i) {
            String[] oneRow = rows.get(i);
            for (int j = 0; j < oneRow.length; ++j) {
                m.put(i, j, Double.valueOf(oneRow[j]));
            }
        }

        return m;
    }
}
