package com.sivannsan.millidata;

import com.sivannsan.foundation.annotation.Nonnegative;
import com.sivannsan.foundation.annotation.Nonnull;

/**
 * MilliValue
 */
public final class MilliValue extends MilliData implements Comparable<MilliValue> {
    @Nonnull
    private final String value;

    @SuppressWarnings("unused")
    public MilliValue() {
        this("");
    }

    /**
     * String.valueOf(o)
     */
    public MilliValue(Object o) {
        value = String.valueOf(o);
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj instanceof MilliValue && value.equals(((MilliValue) obj).value);
    }

    @Override
    @Nonnull
    protected String toString(@Nonnegative int indent, @Nonnegative int previous) {
        return "\"" + value.replace("\\", "\\\\").replace("\"", "\\\"").replace("\t", "\\t").replace("\n", "\\n") + "\"";
    }

    public boolean superOf(@Nonnull MilliValue subMilliValue) {
        return value.contains(subMilliValue.value);
    }

    @Nonnull
    public String asString() {
        return value;
    }

    @SuppressWarnings("unused")
    public boolean isInteger32() {
        boolean result = true;
        try {
            asInteger32();
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }

    @SuppressWarnings("unused")
    public boolean isInteger64() {
        boolean result = true;
        try {
            asInteger64();
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }

    @SuppressWarnings("unused")
    public boolean isFloat32() {
        boolean result = true;
        try {
            asFloat32();
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }

    @SuppressWarnings("unused")
    public boolean isFloat64() {
        boolean result = true;
        try {
            asFloat64();
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }

    @SuppressWarnings("unused")
    public boolean isBoolean() {
        boolean result = true;
        try {
            asBoolean();
        } catch (IllegalStateException e) {
            result = false;
        }
        return result;
    }

    @SuppressWarnings("UnusedReturnValue")
    public int asInteger32() throws NumberFormatException {
        return Integer.parseInt(value);
    }

    @SuppressWarnings("UnusedReturnValue")
    public long asInteger64() throws NumberFormatException {
        return Long.parseLong(value);
    }

    @SuppressWarnings("UnusedReturnValue")
    public float asFloat32() throws NumberFormatException {
        return Float.parseFloat(value);
    }

    @SuppressWarnings("UnusedReturnValue")
    public double asFloat64() throws NumberFormatException {
        return Double.parseDouble(value);
    }

    @SuppressWarnings("UnusedReturnValue")
    public boolean asBoolean() throws IllegalStateException {
        if (value.equals("true")) return true;
        if (value.equals("false")) return false;
        throw new IllegalStateException("Not a Boolean");
    }

    @Override
    public int compareTo(@Nonnull MilliValue o) {
        return value.compareTo(o.value);
    }
}
