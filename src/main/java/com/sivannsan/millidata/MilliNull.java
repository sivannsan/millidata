package com.sivannsan.millidata;

import com.sivannsan.foundation.annotation.Nonnull;

/**
 * MilliNull
 * <p>
 * The two possible values from Java String are "" and "null" //TODO: Really allow "" ?
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
    protected String toString(int indent, int previous) {
        return "null";
    }
}
