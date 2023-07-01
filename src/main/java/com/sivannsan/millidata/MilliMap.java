package com.sivannsan.millidata;

import java.util.*;

public final class MilliMap extends AbstractMilliMap {
    public MilliMap() {
        super();
    }

    public MilliMap(String key, MilliData value) {
        super(key, value);
    }

    public MilliMap(String key1, MilliData value1, String key2, MilliData value2) {
        super(key1, value1, key2, value2);
    }

    public MilliMap(String key1, MilliData value1, String key2, MilliData value2, String key3, MilliData value3) {
        super(key1, value1, key2, value2, key3, value3);
    }

    public MilliMap(Map<?, ?> map) {
        super(map);
    }

    public MilliMap append(String key, MilliData value) {
        put(key, value);
        return this;
    }

    @Override
    public boolean superOf(MilliMap subMilliMap, int level) {
        return super.superOf(subMilliMap, level);
    }

    @Override
    public boolean superOf(MilliMap subMilliMap) {
        return super.superOf(subMilliMap);
    }

    @Override
    public boolean contains(String key) {
        return super.contains(key);
    }

    @Override
    public boolean contains(MilliData value) {
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
    public void put(String key, MilliData value) {
        super.put(key, value);
    }

    @Override
    public void remove(String key) {
        super.remove(key);
    }

    @Override
    public MilliData get(String key) {
        return super.get(key);
    }

    @Override
    public int size() {
        return super.size();
    }

    /**
     * @return a view-only list of entries
     */
    @Override
    public List<Entry> entries() {
        return super.entries();
    }

    /**
     * @return a view-only list of keys
     */
    @Override
    public List<String> keys() {
        return super.keys();
    }

    /**
     * @return a view-only list of values
     */
    @Override
    public List<MilliData> values() {
        return super.values();
    }

    @Override
    public Map<String, MilliData> asMap() {
        return super.asMap();
    }

    public static final class Entry extends AbstractMilliMap.AbstractEntry {
        Entry(String key, MilliData value) {
            super(key, value);
        }

        @Override
        public String getKey() {
            return super.getKey();
        }

        @Override
        public MilliData getValue() {
            return super.getValue();
        }
    }
}
