package info.informationsea.dataclustering4j.matrix;

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
    public void setRowKeys(R[] rowKeys) {
        m_parent.setRowKeys(rowKeys);
    }

    @Override
    public void setColumnKeys(C[] columnKeys) {
        m_parent.setColumnKeys(columnKeys);
    }

    @Override
    public R[] getRowKeys() {
        return m_parent.getRowKeys();
    }

    @Override
    public C[] getColumnKeys() {
        return m_parent.getColumnKeys();
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
