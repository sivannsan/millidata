package com.sivannsan.millidata;

import com.sivannsan.foundation.annotation.Nonnull;

public final class MilliDataParser extends AbstractMilliDataParser {
    private MilliDataParser() {
    }

    @Nonnull
    public static MilliData parse(String millidata) {
        return AbstractMilliDataParser.parse(millidata);
    }

    @Nonnull
    public static MilliData checkedParse(String millidata) throws NullPointerException, MilliDataException {
        return AbstractMilliDataParser.checkedParse(millidata);
    }
}
