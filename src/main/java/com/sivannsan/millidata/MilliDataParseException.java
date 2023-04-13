package com.sivannsan.millidata;

import com.sivannsan.foundation.annotation.Nonnull;
import com.sivannsan.foundation.common.Validate;

public class MilliDataParseException extends RuntimeException {
    public MilliDataParseException() {
        super();
    }

    public MilliDataParseException(@Nonnull String message) {
        super(Validate.nonnull(message));
    }
}
