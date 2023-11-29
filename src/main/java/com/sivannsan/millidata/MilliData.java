package com.sivannsan.millidata;

import com.sivannsan.foundation.annotation.Nonnull;
import com.sivannsan.foundation.common.Validate;

/**
 * @see com.sivannsan.millidata.MilliNull
 * @see com.sivannsan.millidata.MilliValue
 * @see com.sivannsan.millidata.MilliList
 * @see com.sivannsan.millidata.MilliMap
 */
public abstract class MilliData {
    @Override
    public abstract boolean equals(Object o);

    @Nonnull
    @Override
    public final String toString() {
        return toString(0);
    }

    @Nonnull
    public final String toString(int indent) {
        return toString(indent, 0);
    }

    @Nonnull
    protected abstract String toString(int indent, int previous);

    /**
     * @param level negative value for infinite deep
     */
    public final boolean superOf(@Nonnull MilliData subMilliData, int level) {
        if (isMilliNull() && subMilliData.isMilliNull()) return true;
        else if (isMilliValue() && subMilliData.isMilliValue()) return asMilliValue().superOf(subMilliData.asMilliValue());
        else if (isMilliList() && subMilliData.isMilliList()) return asMilliList().superOf(subMilliData.asMilliList(), level);
        else if (isMilliMap() && subMilliData.isMilliMap()) return asMilliMap().superOf(subMilliData.asMilliMap(), level);
        else return false;
    }

    public final boolean superOf(@Nonnull MilliData subMilliData) {
        return superOf(subMilliData, 0);
    }

    public final boolean isMilliNull() {
        return this instanceof MilliNull;
    }

    public final boolean isMilliValue() {
        return this instanceof MilliValue;
    }

    public final boolean isMilliList() {
        return this instanceof MilliList;
    }

    public final boolean isMilliMap() {
        return this instanceof MilliMap;
    }

    @Nonnull
    public final MilliValue asMilliValue() {
        return isMilliValue() ? (MilliValue) this : MilliValue.EMPTY;
    }

    @Nonnull
    public final MilliValue asMilliValue(@Nonnull MilliValue defaultValue) {
        return isMilliValue() ? (MilliValue) this : Validate.nonnull(defaultValue);
    }

    @Nonnull
    public final MilliList asMilliList() {
        return isMilliList() ? (MilliList) this : new MilliList();
    }

    @Nonnull
    public final MilliList asMilliList(@Nonnull MilliList defaultValue) {
        return isMilliList() ? (MilliList) this : Validate.nonnull(defaultValue);
    }

    @Nonnull
    public final MilliMap asMilliMap() {
        return isMilliMap() ? (MilliMap) this : new MilliMap();
    }

    @Nonnull
    public final MilliMap asMilliMap(@Nonnull MilliMap defaultValue) {
        return isMilliMap() ? (MilliMap) this : Validate.nonnull(defaultValue);
    }
}
