package com.sivannsan.millidata;

import com.sivannsan.foundation.annotation.Nonnull;

import java.util.Objects;

public abstract class AbstractMilliValue extends MilliData implements Comparable<MilliValue> {
    @Nonnull
    private final String value;

    protected AbstractMilliValue() {
        this("");
    }

    /**
     * String.valueOf(o)
     */
    protected AbstractMilliValue(Object o) {
        value = String.valueOf(o);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractMilliValue that = (AbstractMilliValue) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(value);
    }

    @Override
    protected final String toString(int indent, int previous) {
        return "\"" + value.replace("\\", "\\\\").replace("\"", "\\\"").replace("\t", "\\t").replace("\n", "\\n") + "\"";
    }

    protected boolean superOf(@Nonnull MilliValue subMilliValue) {
        return value.contains(subMilliValue.asString());
    }

    @Nonnull
    protected String asString() {
        return value;
    }

    protected boolean isInt() {
        try {
            Integer.parseInt(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isLong() {
        try {
            Long.parseLong(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isFloat() {
        try {
            Float.parseFloat(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isDouble() {
        try {
            Double.parseDouble(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isBoolean() {
        return value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false");
    }

    protected int asInt() {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return 0;
        }
    }

    protected int asInt(int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    protected long asLong() {
        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            return 0L;
        }
    }

    protected long asLong(long defaultValue) {
        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    protected float asFloat() {
        try {
            return Float.parseFloat(value);
        } catch (Exception e) {
            return 0F;
        }
    }

    protected float asFloat(float defaultValue) {
        try {
            return Float.parseFloat(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    protected double asDouble() {
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            return 0D;
        }
    }

    protected double asDouble(double defaultValue) {
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    protected boolean asBoolean() {
        return Boolean.parseBoolean(value);
    }

    protected boolean asBoolean(boolean defaultValue) {
        return isBoolean() ? Boolean.parseBoolean(value) : defaultValue;
    }

    @Override
    public final int compareTo(@Nonnull MilliValue o) {
        return value.compareTo(o.asString());
    }
}
