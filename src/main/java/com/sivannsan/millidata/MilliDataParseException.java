package com.sivannsan.millidata;

import com.sivannsan.foundation.annotation.Nonnull;
import com.sivannsan.foundation.common.Require;

public class MilliDataParseException extends RuntimeException {
    public MilliDataParseException() {
        super();
    }

    public MilliDataParseException(@Nonnull String message) {
        super(Require.nonnull(message));
    }
}
