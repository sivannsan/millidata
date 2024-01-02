package com.sivannsan.millidata;

import org.jetbrains.annotations.NotNull;

/**
 * @see com.sivannsan.millidata.MilliNull
 * @see com.sivannsan.millidata.MilliValue
 * @see com.sivannsan.millidata.MilliList
 * @see com.sivannsan.millidata.MilliMap
 */
public abstract class MilliData {
    @Override
    public abstract boolean equals(Object o);

    @NotNull
    @Override
    public final String toString() {
        return toString(0);
    }

    @NotNull
    public final String toString(int indent) {
        return toString(indent, 0);
    }

    @NotNull
    protected abstract String toString(int indent, int previous);

    /**
     * @param level negative value for infinite deep
     */
    public final boolean superOf(@NotNull MilliData subMilliData, int level) {
        if (isMilliNull() && subMilliData.isMilliNull()) return true;
        else if (isMilliValue() && subMilliData.isMilliValue()) return asMilliValue().superOf(subMilliData.asMilliValue());
        else if (isMilliList() && subMilliData.isMilliList()) return asMilliList().superOf(subMilliData.asMilliList(), level);
        else if (isMilliMap() && subMilliData.isMilliMap()) return asMilliMap().superOf(subMilliData.asMilliMap(), level);
        else return false;
    }

    public final boolean superOf(@NotNull MilliData subMilliData) {
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

    @NotNull
    public final MilliValue asMilliValue() {
        return isMilliValue() ? (MilliValue) this : MilliValue.EMPTY;
    }

    @NotNull
    public final MilliValue asMilliValue(@NotNull MilliValue defaultValue) {
        return isMilliValue() ? (MilliValue) this : defaultValue;
    }

    @NotNull
    public final MilliList asMilliList() {
        return isMilliList() ? (MilliList) this : new MilliList();
    }

    @NotNull
    public final MilliList asMilliList(@NotNull MilliList defaultValue) {
        return isMilliList() ? (MilliList) this : defaultValue;
    }

    @NotNull
    public final MilliMap asMilliMap() {
        return isMilliMap() ? (MilliMap) this : new MilliMap();
    }

    @NotNull
    public final MilliMap asMilliMap(@NotNull MilliMap defaultValue) {
        return isMilliMap() ? (MilliMap) this : defaultValue;
    }

    @NotNull
    protected final String repeat(@NotNull String s, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(s);
        }
        return sb.toString();
    }
}
