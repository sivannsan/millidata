package com.sivannsan.millidata;

public final class MilliDataConverter extends AbstractMilliDataConverter {
    private MilliDataConverter() {
    }

    /**
     * Converts a normal java object to a millidata java object
     */
    public static MilliData convert(Object millidata) {
        return AbstractMilliDataConverter.convert(millidata);
    }
}
