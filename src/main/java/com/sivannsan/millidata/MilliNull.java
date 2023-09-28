package com.sivannsan.millidata;

import com.sivannsan.foundation.annotation.Nonnull;

public final class MilliNull extends AbstractMilliNull {
    @Nonnull
    public static final MilliNull INSTANCE = new MilliNull();

    private MilliNull() {
    }
}
