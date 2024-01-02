package com.sivannsan.millidata;

import org.jetbrains.annotations.NotNull;

public class MilliNull extends MilliData {
    @NotNull
    public static final MilliNull INSTANCE = new MilliNull();

    private MilliNull() {
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof MilliNull;
    }

    @NotNull
    @Override
    protected String toString(int indent, int previous) {
        return "null";
    }
}
