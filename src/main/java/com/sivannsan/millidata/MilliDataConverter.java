package com.sivannsan.millidata;

import com.sivannsan.foundation.annotation.Nonnull;

import java.util.Collection;
import java.util.Map;

public final class MilliDataConverter {
    private MilliDataConverter() {
    }

    /**
     * Converts a normal Java object to a MilliData
     */
    @Nonnull
    public static MilliData convert(Object millidata) {
        if (millidata == null) {
            return MilliNull.INSTANCE;
        } else if (millidata instanceof MilliData) {
            return (MilliData) millidata;
        } else if (millidata instanceof Map<?, ?>) {
            MilliMap m = new MilliMap();
            for (Map.Entry<?, ?> e : ((Map<?, ?>) millidata).entrySet()) {
                if (e.getValue() != null) {
                    m.append(convert(e.getKey()).asMilliValue().asString(), convert(e.getValue()));
                }
            }
            return m;
        } else if (millidata instanceof Collection<?>) {
            MilliList l = new MilliList();
            for (Object e : (Collection<?>) millidata) {
                if (e != null) {
                    l.append(convert(e));
                }
            }
            return l;
        } else {
            return new MilliValue(millidata);
        }
    }
}
