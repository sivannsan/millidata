package com.sivannsan.millidata;

import com.sivannsan.foundation.annotation.Nonnull;
import com.sivannsan.foundation.Validate;

/**
 * MilliValue
 */
public final class MilliValue extends MilliData implements Comparable<MilliValue> {
    @Nonnull
    private final String value;

    public MilliValue() {
        this("");
    }

    public MilliValue(@Nonnull String str) {
        this.value = Validate.nonnull(str);
    }

    public MilliValue(@Nonnull Number num) {
        this(String.valueOf(num));
    }

    public MilliValue(@Nonnull Boolean bool) {
        this(bool ? "true" : "false");
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj instanceof MilliValue && value.equals(((MilliValue) obj).value);
    }

    @Override
    @Nonnull
    protected String toString(int indent, int previous) {
        return "\"" + value.replace("\\", "\\\\").replace("\"", "\\\"").replace("\t", "\\t").replace("\n", "\\n") + "\"";
    }

    //Should be moved to MilliData?
    public boolean superOf(@Nonnull MilliValue subMilliValue) {
        return value.contains(subMilliValue.value);
    }

    @Nonnull
    public String asString() {
        return value;
    }

    public boolean isInteger32() {
        try {
            asInteger32();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean isInteger64() {
        try {
            asInteger64();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean isFloat32() {
        try {
            asFloat32();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean isFloat64() {
        try {
            asFloat64();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean isBoolean() {
        try {
            asBoolean();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public int asInteger32() throws NumberFormatException {
        return Integer.parseInt(value);
    }

    public long asInteger64() throws NumberFormatException {
        return Long.parseLong(value);
    }

    public float asFloat32() throws NumberFormatException {
        return Float.parseFloat(value);
    }

    public double asFloat64() throws NumberFormatException {
        return Double.parseDouble(value);
    }

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
