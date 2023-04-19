package com.sivannsan.millidata;

public final class MilliNull extends MilliData {
    public static final MilliNull INSTANCE = new MilliNull();

    private MilliNull() {
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MilliNull;
    }

    @Override
    protected String toString(int indent, int previous) {
        return "null";
    }
}
