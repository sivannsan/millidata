package com.sivannsan.millidata;

import java.util.Objects;

public abstract class AbstractMilliData {
    @Override
    public abstract boolean equals(Object o);

    @Override
    public String toString() {
        return toString(0);
    }

    protected String toString(int indent) {
        return toString(indent, 0);
    }

    protected abstract String toString(int indent, int previous);

    protected boolean superOf(MilliData subMilliData, int level) {
        if (isMilliNull() && subMilliData.isMilliNull()) return true;
        else if (isMilliValue() && subMilliData.isMilliValue()) return asMilliValue().superOf(subMilliData.asMilliValue());
        else if (isMilliList() && subMilliData.isMilliList()) return asMilliList().superOf(subMilliData.asMilliList(), level);
        else if (isMilliMap() && subMilliData.isMilliMap()) return asMilliMap().superOf(subMilliData.asMilliMap(), level);
        else return false;
    }

    protected boolean superOf(MilliData subMilliData) {
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

    protected MilliValue asMilliValue() {
        return isMilliValue() ? (MilliValue) this : MilliValue.EMPTY;
    }

    protected MilliValue asMilliValue(MilliValue defaultValue) {
        return isMilliValue() ? (MilliValue) this : Objects.requireNonNull(defaultValue);
    }

    protected MilliList asMilliList() {
        return isMilliList() ? (MilliList) this : new MilliList();
    }

    protected MilliList asMilliList(MilliList defaultValue) {
        return isMilliList() ? (MilliList) this : Objects.requireNonNull(defaultValue);
    }

    protected MilliMap asMilliMap() {
        return isMilliMap() ? (MilliMap) this : new MilliMap();
    }

    protected MilliMap asMilliMap(MilliMap defaultValue) {
        return isMilliMap() ? (MilliMap) this : Objects.requireNonNull(defaultValue);
    }
}
