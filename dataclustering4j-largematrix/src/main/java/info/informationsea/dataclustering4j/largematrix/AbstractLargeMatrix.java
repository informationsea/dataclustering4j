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

package info.informationsea.dataclustering4j.largematrix;

import info.informationsea.dataclustering4j.matrix.AbstractMatrix;
import info.informationsea.dataclustering4j.matrix.AbstractMutableLabeledMatrix;
import info.informationsea.dataclustering4j.matrix.Matrix;
import info.informationsea.dataclustering4j.matrix.MutableMatrix;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * dataclustering4j
 * Copyright (C) 2015 OKAMURA Yasunobu
 * Created on 15/03/13.
 */
abstract public class AbstractLargeMatrix<T, R, C> extends AbstractMutableLabeledMatrix<T, R, C> implements MutableMatrix<T> {
    protected File dataFile;
    protected RandomAccessFile randomAccessFile;
    protected FileChannel fileChannel;
    private int numberOfColumn;
    private int numberOfRow;
    private int itemSize;

    public AbstractLargeMatrix(int ncol, int nrow) throws IOException {
        numberOfColumn = ncol;
        numberOfRow = nrow;
        itemSize = getItemSize();
        initializeFileWithSize(File.createTempFile("largematrix", ".bin"), itemSize*numberOfColumn*numberOfRow);
    }

    /**
     * Calculate the byte size of one item and return it
     * @return the byte size of one item
     */
    protected abstract int getItemSize();

    private void initializeFileWithSize(File file, long length) throws IOException {
        dataFile = file;
        randomAccessFile = new RandomAccessFile(file, "rw");
        fileChannel = randomAccessFile.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024*10);
        while (file.length() < length) {
            fileChannel.write(buffer);
        }

        fileChannel.truncate(length);
    }

    protected int calculateArrayPosition(int col, int row) {
        if (col >= numberOfColumn)
            throw new ArrayIndexOutOfBoundsException(col);
        if (row >= numberOfRow)
            throw new ArrayIndexOutOfBoundsException(row);
        return numberOfRow*col + row;
    }

    @Override
    public int[] getSize() {
        return new int[]{numberOfRow, numberOfColumn};
    }

    @Override
    public Object[] getRow(int row) {
        int columnSize = getSize()[1];
        Object[] array = new Object[columnSize];
        for (int i = 0; i < columnSize; i++) {
            array[i] = get(row, i);
        }
        return array;
    }

    @Override
    public Object[] getColumn(int column) {
        int rowSize = getSize()[0];
        Object[] array = new Object[rowSize];
        for (int i = 0; i < rowSize; i++) {
            array[i] = get(i, column);
        }
        return array;
    }

    @Override
    public Matrix<T> transpose() {
        return new TransposedMatrix<T>(this);
    }

    public static class TransposedMatrix<T> extends AbstractMatrix<T> {

        private Matrix<T> parentMatrix;

        public TransposedMatrix(Matrix<T> parentMatrix) {
            this.parentMatrix = parentMatrix;
        }

        @Override
        public int[] getSize() {
            int[] newSize = new int[2];
            newSize[0] = parentMatrix.getSize()[1];
            newSize[1] = parentMatrix.getSize()[0];
            return newSize;
        }

        @Override
        public T get(int row, int col) {
            return parentMatrix.get(col, row);
        }

        @Override
        public Object[] getRow(int row) {
            return parentMatrix.getColumn(row);
        }

        @Override
        public Object[] getColumn(int column) {
            return parentMatrix.getRow(column);
        }

        @Override
        public Matrix<T> transpose() {
            return parentMatrix;
        }
    }
}
