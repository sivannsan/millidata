package com.sivannsan.millidata;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public final class MilliValue extends MilliData implements Comparable<MilliValue> {
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
    public boolean equals(Object obj) {
        return this == obj || obj instanceof MilliValue && value.equals(((MilliValue) obj).value);
    }

    @Override
    protected String toString(int indent, int previous) {
        return "\"" + value.replace("\\", "\\\\").replace("\"", "\\\"").replace("\t", "\\t").replace("\n", "\\n") + "\"";
    }

    public boolean superOf(MilliValue subMilliValue) {
        return value.contains(subMilliValue.value);
    }

    public String asString() {
        return value;
    }

    public boolean isInteger() {
        boolean result = true;
        try {
            asInteger();
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }

    public boolean isLong() {
        boolean result = true;
        try {
            asLong();
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }

    public boolean isFloat() {
        boolean result = true;
        try {
            asFloat();
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }

    public boolean isDouble() {
        boolean result = true;
        try {
            asDouble();
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }

    public boolean isBoolean() {
        boolean result = true;
        try {
            asBoolean();
        } catch (IllegalStateException e) {
            result = false;
        }
        return result;
    }

    public int asInteger() throws NumberFormatException {
        return Integer.parseInt(value);
    }

    public long asLong() throws NumberFormatException {
        return Long.parseLong(value);
    }

    public float asFloat() throws NumberFormatException {
        return Float.parseFloat(value);
    }

    public double asDouble() throws NumberFormatException {
        return Double.parseDouble(value);
    }

    public boolean asBoolean() throws IllegalStateException {
        if (value.equals("true")) return true;
        if (value.equals("false")) return false;
        throw new IllegalStateException("Not a Boolean");
    }

    @Override
    public int compareTo(MilliValue o) {
        return value.compareTo(o.value);
    }
}
