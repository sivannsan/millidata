package com.sivannsan.millidata;

import com.sivannsan.foundation.annotation.Nonnull;

import java.util.Objects;

public class MilliValue extends MilliData implements Comparable<MilliValue> {
    @Nonnull
    public static final MilliValue EMPTY = new MilliValue();
    @Nonnull
    private final String value;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MilliValue that = (MilliValue) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    protected String toString(int indent, int previous) {
        return "\"" + value.replace("\\", "\\\\").replace("\"", "\\\"").replace("\t", "\\t").replace("\n", "\\n") + "\"";
    }

    public boolean superOf(@Nonnull MilliValue subMilliValue) {
        return value.contains(subMilliValue.asString());
    }

    @Nonnull
    public String asString() {
        return value;
    }

    public boolean isInt() {
        try {
            Integer.parseInt(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLong() {
        try {
            Long.parseLong(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFloat() {
        try {
            Float.parseFloat(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDouble() {
        try {
            Double.parseDouble(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isBoolean() {
        return value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false");
    }

    public int asInt() {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return 0;
        }
    }

    public int asInt(int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public long asLong() {
        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            return 0L;
        }
    }

    public long asLong(long defaultValue) {
        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public float asFloat() {
        try {
            return Float.parseFloat(value);
        } catch (Exception e) {
            return 0F;
        }
    }

    public float asFloat(float defaultValue) {
        try {
            return Float.parseFloat(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public double asDouble() {
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            return 0D;
        }
    }

    public double asDouble(double defaultValue) {
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public boolean asBoolean() {
        return Boolean.parseBoolean(value);
    }

    public boolean asBoolean(boolean defaultValue) {
        return isBoolean() ? Boolean.parseBoolean(value) : defaultValue;
    }

    @Override
    public int compareTo(@Nonnull MilliValue o) {
        return value.compareTo(o.asString());
    }
}
