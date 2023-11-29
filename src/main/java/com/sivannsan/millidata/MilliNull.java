package com.sivannsan.millidata;

import com.sivannsan.foundation.annotation.Nonnull;

public class MilliNull extends MilliData {
    @Nonnull
    public static final MilliNull INSTANCE = new MilliNull();

    private MilliNull() {
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof MilliNull;
    }

    @Nonnull
    @Override
    protected String toString(int indent, int previous) {
        return "null";
    }
}
