package com.sivannsan.millidata;

public abstract class AbstractMilliNull extends MilliData {
    protected AbstractMilliNull() {
    }

    @Override
    public final boolean equals(Object o) {
        return o instanceof MilliNull;
    }

    @Override
    protected final String toString(int indent, int previous) {
        return "null";
    }

}
