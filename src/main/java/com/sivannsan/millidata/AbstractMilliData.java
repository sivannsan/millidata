package com.sivannsan.millidata;

import com.sivannsan.foundation.annotation.Nonnull;
import com.sivannsan.foundation.common.Validate;

public abstract class AbstractMilliData {
    @Override
    public abstract boolean equals(Object o);

    @Nonnull
    @Override
    public String toString() {
        return toString(0);
    }

    @Nonnull
    protected String toString(int indent) {
        return toString(indent, 0);
    }

    @Nonnull
    protected abstract String toString(int indent, int previous);

    protected boolean superOf(@Nonnull MilliData subMilliData, int level) {
        if (isMilliNull() && subMilliData.isMilliNull()) return true;
        else if (isMilliValue() && subMilliData.isMilliValue()) return asMilliValue().superOf(subMilliData.asMilliValue());
        else if (isMilliList() && subMilliData.isMilliList()) return asMilliList().superOf(subMilliData.asMilliList(), level);
        else if (isMilliMap() && subMilliData.isMilliMap()) return asMilliMap().superOf(subMilliData.asMilliMap(), level);
        else return false;
    }

    protected boolean superOf(@Nonnull MilliData subMilliData) {
        return superOf(subMilliData, 0);
    }

    protected boolean isMilliNull() {
        return this instanceof MilliNull;
    }

    protected boolean isMilliValue() {
        return this instanceof MilliValue;
    }

    protected boolean isMilliList() {
        return this instanceof MilliList;
    }

    protected boolean isMilliMap() {
        return this instanceof MilliMap;
    }

    @Nonnull
    protected MilliValue asMilliValue() {
        return isMilliValue() ? (MilliValue) this : MilliValue.EMPTY;
    }

    @Nonnull
    protected MilliValue asMilliValue(@Nonnull MilliValue defaultValue) {
        return isMilliValue() ? (MilliValue) this : Validate.nonnull(defaultValue);
    }

    @Nonnull
    protected MilliList asMilliList() {
        return isMilliList() ? (MilliList) this : new MilliList();
    }

    @Nonnull
    protected MilliList asMilliList(@Nonnull MilliList defaultValue) {
        return isMilliList() ? (MilliList) this : Validate.nonnull(defaultValue);
    }

    @Nonnull
    protected MilliMap asMilliMap() {
        return isMilliMap() ? (MilliMap) this : new MilliMap();
    }

    @Nonnull
    protected MilliMap asMilliMap(@Nonnull MilliMap defaultValue) {
        return isMilliMap() ? (MilliMap) this : Validate.nonnull(defaultValue);
    }
}
