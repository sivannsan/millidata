package com.sivannsan.millidata;

import com.sivannsan.foundation.annotation.Nonnegative;
import com.sivannsan.foundation.annotation.Nonnull;

import java.util.*;

public final class MilliMap extends AbstractMilliMap {
    public MilliMap() {
        super();
    }

    public MilliMap(@Nonnull String key, @Nonnull MilliData value) {
        super(key, value);
    }

    public MilliMap(@Nonnull String k1, @Nonnull MilliData v1, @Nonnull String k2, @Nonnull MilliData v2) {
        super(k1, v1, k2, v2);
    }

    public MilliMap(@Nonnull String k1, @Nonnull MilliData v1, @Nonnull String k2, @Nonnull MilliData v2, @Nonnull String k3, @Nonnull MilliData v3) {
        super(k1, v1, k2, v2, k3, v3);
    }

    public MilliMap(Map<?, ?> map) {
        super(map);
    }

    @Nonnull
    public MilliMap append(@Nonnull String key, @Nonnull MilliData value) {
        put(key, value);
        return this;
    }

    @Override
    public boolean superOf(@Nonnull MilliMap subMilliMap, int level) {
        return super.superOf(subMilliMap, level);
    }

    @Override
    public boolean superOf(@Nonnull MilliMap subMilliMap) {
        return super.superOf(subMilliMap);
    }

    @Override
    public boolean contains(@Nonnull String key) {
        return super.contains(key);
    }

    @Override
    public boolean contains(@Nonnull MilliData value) {
        return super.contains(value);
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public void put(@Nonnull String key, @Nonnull MilliData value) {
        super.put(key, value);
    }

    @Override
    public void remove(@Nonnull String key) {
        super.remove(key);
    }

    @Nonnull
    @Override
    public MilliData get(@Nonnull String key) {
        return super.get(key);
    }

    @Nonnegative
    @Override
    public int size() {
        return super.size();
    }

    /**
     * @return a view-only list of entries
     */
    @Nonnull
    @Override
    public List<Entry> entries() {
        return super.entries();
    }

    /**
     * @return a view-only list of keys
     */
    @Nonnull
    @Override
    public List<String> keys() {
        return super.keys();
    }

    /**
     * @return a view-only list of values
     */
    @Nonnull
    @Override
    public List<MilliData> values() {
        return super.values();
    }

    @Nonnull
    @Override
    public Map<String, MilliData> asMap() {
        return super.asMap();
    }

    public static final class Entry extends AbstractMilliMap.AbstractEntry {
        public Entry(@Nonnull String key, @Nonnull MilliData value) {
            super(key, value);
        }

        @Nonnull
        @Override
        public String getKey() {
            return super.getKey();
        }

        @Nonnull
        @Override
        public MilliData getValue() {
            return super.getValue();
        }
    }
}
