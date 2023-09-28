package com.sivannsan.millidata;

import com.sivannsan.foundation.annotation.Nonnull;

/**
 * @see com.sivannsan.millidata.MilliNull
 * @see com.sivannsan.millidata.MilliValue
 * @see com.sivannsan.millidata.MilliList
 * @see com.sivannsan.millidata.MilliMap
 */
public abstract class MilliData extends AbstractMilliData {
    @Override
    public abstract boolean equals(Object o);

    @Nonnull
    @Override
    public final String toString() {
        return super.toString();
    }

    @Nonnull
    @Override
    public final String toString(int indent) {
        return super.toString(indent);
    }

    @Nonnull
    @Override
    protected abstract String toString(int indent, int previous);

    /**
     * @param level negative value for infinite deep
     */
    @Override
    public final boolean superOf(@Nonnull MilliData subMilliData, int level) {
        return super.superOf(subMilliData, level);
    }

    @Override
    public final boolean superOf(@Nonnull MilliData subMilliData) {
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

    @Nonnull
    @Override
    public final MilliValue asMilliValue() {
        return super.asMilliValue();
    }

    @Nonnull
    @Override
    public final MilliValue asMilliValue(@Nonnull MilliValue defaultValue) {
        return super.asMilliValue(defaultValue);
    }

    @Nonnull
    @Override
    public final MilliList asMilliList() {
        return super.asMilliList();
    }

    @Nonnull
    @Override
    public final MilliList asMilliList(@Nonnull MilliList defaultValue) {
        return super.asMilliList(defaultValue);
    }

    @Nonnull
    @Override
    public final MilliMap asMilliMap() {
        return super.asMilliMap();
    }

    @Nonnull
    @Override
    public final MilliMap asMilliMap(@Nonnull MilliMap defaultValue) {
        return super.asMilliMap(defaultValue);
    }
}
