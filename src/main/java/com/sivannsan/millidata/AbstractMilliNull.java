package com.sivannsan.millidata;

import com.sivannsan.foundation.annotation.Nonnull;

public abstract class AbstractMilliNull extends MilliData {
    protected AbstractMilliNull() {
    }

    @Override
    public final boolean equals(Object o) {
        return o instanceof MilliNull;
    }

    @Nonnull
    @Override
    protected final String toString(int indent, int previous) {
        return "null";
    }
}
