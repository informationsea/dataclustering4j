package info.informationsea.dataclustering4j.matrix;

import java.util.List;

/**
 * dataclustering4j
 * Copyright (C) 2014 Yasunobu OKAMURA
 * Created at 14/12/09
 */
public class LabeledMappedMatrix<T, R, C> extends MappedMatrix<T> implements LabeledMatrix<T, R, C> {

    private LabeledMatrix<T, R, C> m_parent;
    private MapValue<T> m_map;

    public LabeledMappedMatrix(LabeledMatrix<T, R, C> parent, MapValue<T> map) {
        super(parent, map);
        m_parent = parent;
        m_map = map;
    }

    @Override
    public void setRowKeys(List<R> rowKeys) {
        m_parent.setRowKeys(rowKeys);
    }

    @Override
    public void setColumnKeys(List<C> columnKeys) {
        m_parent.setColumnKeys(columnKeys);
    }

    @Override
    public List<R> getRowKeys() {
        return m_parent.getRowKeys();
    }

    @Override
    public List<C> getColumnKeys() {
        return m_parent.getColumnKeys();
    }

    @Override
    public Object[] getColumn(C column) {
        Object[] columnArray = m_parent.getColumn(column);
        Object[] newArray = new Object[columnArray.length];
        for (int i = 0; i < columnArray.length; i++) {
            newArray[i] = m_map.map((T) columnArray[i]);
        }
        return newArray;
    }

    @Override
    public T[] getColumn(T[] array, C column) {
        Object[] columnArray = m_parent.getColumn(column);
        for (int i = 0; i < columnArray.length; i++) {
            array[i] = m_map.map((T) columnArray[i]);
        }
        return array;
    }

    @Override
    public Object[] getRow(R row) {
        Object[] rowArray = m_parent.getRow(row);
        Object[] newArray = new Object[rowArray.length];
        for (int i = 0; i < rowArray.length; i++) {
            newArray[i] = m_map.map((T) rowArray[i]);
        }
        return newArray;
    }

    @Override
    public T[] getRow(T[] array, R row) {
        Object[] rowArray = m_parent.getRow(row);
        for (int i = 0; i < rowArray.length; i++) {
            array[i] = m_map.map((T) rowArray[i]);
        }
        return array;
    }

    @Override
    public T get(R row, C column) {
        return m_map.map(m_parent.get(row, column));
    }

    @Override
    public void put(R row, C column, T value) {
        m_parent.put(row, column, value);
    }

    @Override
    public void put(int row, int col, T value) {
        m_parent.put(row, col, value);
    }
}
