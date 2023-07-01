package com.sivannsan.millidata;

/**
 * @see com.sivannsan.millidata.MilliNull
 * @see com.sivannsan.millidata.MilliValue
 * @see com.sivannsan.millidata.MilliList
 * @see com.sivannsan.millidata.MilliMap
 */
public abstract class MilliData extends AbstractMilliData {
    @Override
    public abstract boolean equals(Object o);

    @Override
    public final String toString() {
        return super.toString();
    }

    @Override
    public final String toString(int indent) {
        return super.toString(indent);
    }

    @Override
    protected abstract String toString(int indent, int previous);

    /**
     * @param level negative value for infinite deep
     */
    @Override
    public final boolean superOf(MilliData subMilliData, int level) {
        return super.superOf(subMilliData, level);
    }

    @Override
    public final boolean superOf(MilliData subMilliData) {
        return super.superOf(subMilliData);
    }

    @Override
    public final boolean isMilliNull() {
        return super.isMilliNull();
    }

    @Override
    public final boolean isMilliValue() {
        return super.isMilliValue();
    }

    @Override
    public final boolean isMilliList() {
        return super.isMilliList();
    }

    @Override
    public final boolean isMilliMap() {
        return super.isMilliMap();
    }

    @Override
    public final MilliValue asMilliValue() {
        return super.asMilliValue();
    }

    @Override
    public final MilliValue asMilliValue(MilliValue defaultValue) {
        return super.asMilliValue(defaultValue);
    }

    @Override
    public final MilliList asMilliList() {
        return super.asMilliList();
    }

    @Override
    public final MilliList asMilliList(MilliList defaultValue) {
        return super.asMilliList(defaultValue);
    }

    @Override
    public final MilliMap asMilliMap() {
        return super.asMilliMap();
    }

    @Override
    public final MilliMap asMilliMap(MilliMap defaultValue) {
        return super.asMilliMap(defaultValue);
    }
}
