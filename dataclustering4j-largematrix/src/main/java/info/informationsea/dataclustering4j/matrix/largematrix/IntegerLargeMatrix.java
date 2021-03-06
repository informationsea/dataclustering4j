/*
 *  dataclustering4j
 *  Copyright (C) 2015 Yasunobu OKAMURA
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

package info.informationsea.dataclustering4j.matrix.largematrix;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

public class IntegerLargeMatrix<R, C> extends AbstractLargeMatrix<Integer, R, C> {

    private IntBuffer intBuffer;

    public IntegerLargeMatrix(int ncol, int nrow, int defaultValue) throws IOException {
        super(ncol, nrow);
        intBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, getItemSize()*ncol*nrow).asIntBuffer();
        for (int i = 0; i < ncol*nrow; i++) {
            intBuffer.put(defaultValue);
        }
    }

    @Override
    protected int getItemSize() {
        ByteBuffer buffer = ByteBuffer.allocate(32);
        buffer.putInt(1);
        return buffer.position();
    }

    @Override
    public void put(int row, int col, Integer value) {
        intBuffer.put(calculateArrayPosition(col, row), value);
    }

    @Override
    public Integer get(int row, int col) {
        return intBuffer.get(calculateArrayPosition(col, row));
    }
}
