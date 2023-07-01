package com.sivannsan.millidata;

public final class MilliDataParser extends AbstractMilliDataParser {
    private MilliDataParser() {
    }

    public static MilliData parse(String millidata) {
        return AbstractMilliDataParser.parse(millidata);
    }

    public static MilliData checkedParse(String millidata) throws NullPointerException, MilliDataException {
        return AbstractMilliDataParser.checkedParse(millidata);
    }
}
