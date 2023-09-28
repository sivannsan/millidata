package com.sivannsan.millidata;

import com.sivannsan.foundation.annotation.Nonnull;

public final class MilliDataConverter extends AbstractMilliDataConverter {
    private MilliDataConverter() {
    }

    /**
     * Converts a normal java object to a millidata java object
     */
    @Nonnull
    public static MilliData convert(Object millidata) {
        return AbstractMilliDataConverter.convert(millidata);
    }
}
