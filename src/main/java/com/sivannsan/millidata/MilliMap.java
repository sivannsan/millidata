package com.sivannsan.millidata;

import com.sivannsan.foundation.annotation.Nonnegative;
import com.sivannsan.foundation.annotation.Nonnull;
import com.sivannsan.foundation.common.Require;
import com.sivannsan.foundation.common.Validate;

import java.util.*;

/**
 * MilliMap
 */
public final class MilliMap extends MilliData {
    @Nonnull
    private final Map<String, MilliData> map = new TreeMap<>();

    public MilliMap() {
    }

    @SuppressWarnings("unused")
    public MilliMap(@Nonnull String key, @Nonnull MilliData value) {
        put(key, value);
    }

    @SuppressWarnings("unused")
    public MilliMap(@Nonnull String key1, @Nonnull MilliData value1, @Nonnull String key2, @Nonnull MilliData value2) {
        put(key1, value1);
        put(key2, value2);
    }

    @SuppressWarnings("unused")
    public MilliMap(@Nonnull String key1, @Nonnull MilliData value1, @Nonnull String key2, @Nonnull MilliData value2, @Nonnull String key3, @Nonnull MilliData value3) {
        put(key1, value1);
        put(key2, value2);
        put(key3, value3);
    }

    @SuppressWarnings("unused")
    public MilliMap(Map<?, ?> map) {
        if (map != null) this.map.putAll(Converter.convert(map).asMilliMap().asMap());
    }

    @SuppressWarnings("UnusedReturnValue")
    @Nonnull
    public MilliMap append(@Nonnull String key, @Nonnull MilliData value) {
        put(key, value);
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj instanceof MilliMap && map.equals(((MilliMap) obj).map);
    }

    /**
     * @param level negative value for infinite deep
     */
    public boolean superOf(@Nonnull MilliMap subMilliMap, int level) {
        for (Entry subEntry : subMilliMap.entries()) {
            boolean found = false;
            if (level == 0) {
                for (Map.Entry<String, MilliData> currentEntry : map.entrySet()) {
                    if (!(currentEntry.getKey().equals(subEntry.getKey()) && currentEntry.getValue().equals(subEntry.getValue()))) continue;
                    found = true;
                    break;
                }
            } else {
                for (Map.Entry<String, MilliData> currentEntry : map.entrySet()) {
                    if (!(currentEntry.getKey().equals(subEntry.getKey()) && currentEntry.getValue().superOf(subEntry.getValue(), level - 1))) continue;
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }
        return true;
    }

    @SuppressWarnings("unused")
    public boolean superOf(@Nonnull MilliMap subMilliMap) {
        return superOf(subMilliMap, 0);
    }

    @SuppressWarnings("unused")
    public boolean contains(@Nonnull String key) {
        return map.containsKey(Require.nonnull(key));
    }

    @SuppressWarnings("unused")
    public boolean contains(@Nonnull MilliData value) {
        return map.containsValue(Require.nonnull(value));
    }

    @SuppressWarnings("unused")
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @SuppressWarnings("unused")
    public void clear() {
        map.clear();
    }

    public void put(@Nonnull String key, @Nonnull MilliData value) {
        map.put(Require.nonnull(key), Require.nonnull(value));
    }

    @SuppressWarnings("unused")
    public void remove(@Nonnull String key) {
        map.remove(Require.nonnull(key));
    }

    @SuppressWarnings("unused")
    @Nonnull
    public MilliData get(@Nonnull String key) {
        MilliData data = map.get(Require.nonnull(key));
        return Validate.ifNull(data, MilliNull.INSTANCE);
    }

    public int size() {
        return map.size();
    }

    /**
     * @return a view only entry list
     */
    @Nonnull
    public List<Entry> entries() {
        Entry[] entries = new Entry[map.size()];
        String[] keys = map.keySet().toArray(new String[0]);
        for (int i = 0; i < map.size(); i++) entries[i] = new Entry(keys[i], map.get(keys[i]));
        return Arrays.asList(entries);
    }

    /**
     * @return a view only key list
     */
    @SuppressWarnings("unused")
    @Nonnull
    public List<String> keys() {
        return Arrays.asList(map.keySet().toArray(new String[0]));
    }

    /**
     * @return a view only value list
     */
    @SuppressWarnings("unused")
    @Nonnull
    public List<MilliData> values() {
        return Arrays.asList(map.values().toArray(new MilliData[0]));
    }

    @Override
    @Nonnull
    protected String toString(@Nonnegative int indent, @Nonnegative int previous) {
        Set<String> s = new TreeSet<>();
        String ss = indent > 0 ? " " : ""; //Space after :
        String tt = indent > 0 ? " ".repeat(indent + previous) : ""; //Space of the body
        String t = indent > 0 ? " ".repeat(previous) : ""; //Space before closing }
        String nn = indent > 0 ? "\n" : ""; //New line or not
        for (Entry entry : entries())
            s.add(new MilliValue(entry.getKey()) + ":" + ss + entry.getValue().toString(indent, previous + indent));
        return "{" + nn + tt + String.join("," + nn + tt, s) + nn + t + "}";
    }

    @Nonnull
    public Map<String, MilliData> asMap() {
        return map;
    }

    public static final class Entry {
        @Nonnull
        private final String key;
        @Nonnull
        private final MilliData value;

        private Entry(@Nonnull String key, @Nonnull MilliData value) {
            this.key = key;
            this.value = value;
        }

        public boolean equals(Object object) {
            return this == object || object instanceof Entry && key.equals(((Entry) object).key) && value.equals(((Entry) object).value);
        }

        @Nonnull
        public String getKey() {
            return key;
        }

        @Nonnull
        public MilliData getValue() {
            return value;
        }
    }
}
