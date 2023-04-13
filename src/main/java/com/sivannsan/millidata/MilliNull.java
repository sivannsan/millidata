package com.sivannsan.millidata;

import com.sivannsan.foundation.annotation.Nonnegative;
import com.sivannsan.foundation.annotation.Nonnull;

/**
 * MilliNull
 */
public final class MilliNull extends MilliData {
    @Nonnull
    public static final MilliNull INSTANCE = new MilliNull();

    private MilliNull() {
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MilliNull;
    }

    @Override
    @Nonnull
    protected String toString(@Nonnegative int indent, @Nonnegative int previous) {
        return "null";
    }
}
