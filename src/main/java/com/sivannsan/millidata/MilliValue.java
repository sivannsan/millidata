package com.sivannsan.millidata;

import com.sivannsan.foundation.annotation.Nonnull;

public final class MilliValue extends AbstractMilliValue {
    @Nonnull
    public static final MilliValue EMPTY = new MilliValue();

    public MilliValue() {
        super();
    }

    public MilliValue(Object o) {
        super(o);
    }

    @Override
    public boolean superOf(@Nonnull MilliValue subMilliValue) {
        return super.superOf(subMilliValue);
    }

    @Nonnull
    @Override
    public String asString() {
        return super.asString();
    }

    @Override
    public boolean isInt() {
        return super.isInt();
    }

    @Override
    public boolean isLong() {
        return super.isLong();
    }

    @Override
    public boolean isFloat() {
        return super.isFloat();
    }

    @Override
    public boolean isDouble() {
        return super.isDouble();
    }

    @Override
    public boolean isBoolean() {
        return super.isBoolean();
    }

    @Override
    public int asInt() {
        return super.asInt();
    }

    @Override
    public int asInt(int defaultValue) {
        return super.asInt(defaultValue);
    }

    @Override
    public long asLong() {
        return super.asLong();
    }

    @Override
    public long asLong(long defaultValue) {
        return super.asLong(defaultValue);
    }

    @Override
    public float asFloat() {
        return super.asFloat();
    }

    @Override
    public float asFloat(float defaultValue) {
        return super.asFloat(defaultValue);
    }

    @Override
    public double asDouble() {
        return super.asDouble();
    }

    @Override
    public double asDouble(double defaultValue) {
        return super.asDouble(defaultValue);
    }

    @Override
    public boolean asBoolean() {
        return super.asBoolean();
    }

    @Override
    public boolean asBoolean(boolean defaultValue) {
        return super.asBoolean(defaultValue);
    }
}
